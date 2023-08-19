grammar IsiLang;

@header {
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
	import br.com.professorisidro.isilanguage.datastructures.IsiVariable;
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
	import br.com.professorisidro.isilanguage.exceptions.SemanticException;
	import br.com.professorisidro.isilanguage.ast.*;
	import java.util.ArrayList;
	import java.util.Stack;
  import java.util.HashMap;
}

@members {
	private int _type;
	private String _varName;
	private String _varValue;
	private IsiSymbolTable symbolTable = new IsiSymbolTable();
	private IsiSymbol symbol;
	private IsiProgram program = new IsiProgram();
	private ArrayList<AbstractCommand> currentThread;
	private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
	private String _readID;
	private String _writeID;
	private String _exprID;
	private String _exprContent;
	private String _conditionalExpr;
  private String _whileExpr;
  private String _caseExpr;
  private ArrayList<AbstractCommand> _caseDefault;
  private HashMap<String, ArrayList<AbstractCommand>> _caseCommands = new HashMap<String, ArrayList<AbstractCommand>>();
  private String _caseCondition;
	private ArrayList<AbstractCommand> trueList;
	private ArrayList<AbstractCommand> falseList;
  private ArrayList<AbstractCommand> whileList;
  private ArrayList<AbstractCommand> caseList;
  private static final String ANSI_YELLOW = "\u001B[33m";
  private static final String ANSI_RED = "\u001B[31m	";
  private static final String ANSI_RESET = "\u001B[0m";

	public void checkID(String id){
		if (!symbolTable.exists(id)){
			throw new SemanticException("Symbol " + id + " not declared");
		}
	}

	public void showCommands(){
		for (AbstractCommand c: program.getCommands()){
			System.out.println(c);
		}
	}

	public void generateJavaCode(){
		program.generateJavaTarget();
	}

  public void generateRubyCode() {
    program.generateRubyTarget();
  }

  public void checkTypeComp(String id, int targetType) {
    IsiVariable var = (IsiVariable) symbolTable.get(id);

    if (var.getType() != targetType) {
      throw new SemanticException("TypeMismatchError for " + id + ". Expected: " + typeMapper(var.getType()) + ", " + "get: " + typeMapper(targetType));
    }
  }

  public String typeMapper(int target) {
    if (target == 0) {
      return "int";
    } else if(target == 1) {
      return "double";
    } else {
      return "string";
    }
  }

  public void checkSwitchType(String id) {
    IsiVariable var = (IsiVariable) symbolTable.get(id);

    if (var.getType() == IsiVariable.DOUBLE) {
      throw new SemanticException("Cannot switch with double.");
    }
  }

  public void checkCaseType(int targetType, String condition) {
    boolean isInt = condition.matches("[0-9]*");
    boolean isString = condition.matches(".*[a-zA-Z].");

    if(!isInt && targetType == IsiVariable.INT) {
      throw new SemanticException("Case type must be equals to switch.");
    } else if(!isString && targetType == IsiVariable.STRING) {
      throw new SemanticException("Case type must be equals to switch.");
    }
  }

  public void checkUnused() {
    ArrayList<IsiSymbol> unnused = symbolTable.getAllNotUsed();
    for(IsiSymbol symbol: unnused) {
      System.out.println(ANSI_YELLOW + "WARNING: Variable " + symbol.getName() + " declared but never used!" + ANSI_RESET);
    }
  }
}

prog:
	'programa' (varDeclaration)+ block 'fimprog.' {
    checkUnused();
    program.setVarTable(symbolTable);
    program.setCommands(stack.pop());
};

varDeclaration:
	type ID {
    _varName = _input.LT(-1).getText();
    _varValue = null;
    symbol = new IsiVariable(_varName, _type, _varValue);
    if (!symbolTable.exists(_varName)){
      symbolTable.add(symbol);
    }
    else{
      throw new SemanticException("Symbol " + _varName + " already declared");
    }
  } (
		COMMA ID {
      _varName = _input.LT(-1).getText();
      _varValue = null;
      symbol = new IsiVariable(_varName, _type, _varValue);
      if (!symbolTable.exists(_varName)){
          symbolTable.add(symbol);
      }
      else{
        throw new SemanticException("Symbol " + _varName + " already declared");
      }
    }
	)* SC;

type:
	'int' { _type = IsiVariable.INT;  }
	| 'double' { _type = IsiVariable.DOUBLE; }
	| 'string' { _type = IsiVariable.STRING;  };

block:
	{
    currentThread = new ArrayList<AbstractCommand>();
	  stack.push(currentThread);
  } (cmd)+;

cmd:
	readCommand
	| writeCommand
	| attribCommand
	| conditionalCommand
	| whileCommand
	| caseCommand;

readCommand:
  'leia' OPA ID {
    checkID(_input.LT(-1).getText());
    _readID = _input.LT(-1).getText();
  } CPA SC {
    IsiVariable var = (IsiVariable)symbolTable.get(_readID);
    ReadCommand cmd = new ReadCommand(_readID, var);
    stack.peek().add(cmd);
  };

writeCommand:
	'escreva' OPA (
		ID {
      checkID(_input.LT(-1).getText());
	     _writeID = _input.LT(-1).getText();
    }
    | INT {_writeID = _input.LT(-1).getText();}
		| DOUBLE {_writeID = _input.LT(-1).getText();}
		| STRING {_writeID = _input.LT(-1).getText();}
	) CPA SC {
    WriteCommand cmd = new WriteCommand(_writeID);
    stack.peek().add(cmd);
  };

attribCommand:
	ID {
    checkID(_input.LT(-1).getText());
    _exprID = _input.LT(-1).getText();
  } ATTR {
    _exprContent = "";
  } expr SC {
    AssignmentCommand cmd = new AssignmentCommand(_exprID, _exprContent);
    stack.peek().add(cmd);
    IsiVariable var = (IsiVariable) symbolTable.get(_exprID);
    var.setValue(_exprContent);
    symbolTable.get(_exprID).setUsed();
  };

conditionalCommand:
	'se' OPA (ID) {
    _conditionalExpr = _input.LT(-1).getText();
  }
  OPREL {
    _conditionalExpr += _input.LT(-1).getText();
	}
  (ID | expr) {
    _conditionalExpr += _input.LT(-1).getText();
  }
  CPA OBR {
    currentThread = new ArrayList<AbstractCommand>();
    stack.push(currentThread);
  }
  (cmd)+ CBR {
    trueList = stack.pop();
  }
  ('senao' OBR {
      currentThread = new ArrayList<AbstractCommand>();
      stack.push(currentThread);
      } (cmd+) CBR {
      falseList = stack.pop();
      ConditionalCommand cmd = new ConditionalCommand(_conditionalExpr, trueList, falseList);
      stack.peek().add(cmd);
    }
	)?;

whileCommand:
	'enquanto' OPA (ID | expr) {
    _whileExpr = _input.LT(-1).getText();
  }
  OPREL {
    _whileExpr += _input.LT(-1).getText();
	}
  (ID | expr) {
    _whileExpr += _input.LT(-1).getText();
  } CPA OBR {
    currentThread = new ArrayList<AbstractCommand>();
    stack.push(currentThread);
  }
  (cmd)+ CBR {
    whileList = stack.pop();
    WhileCommand cmd = new WhileCommand(_whileExpr, whileList);
    stack.peek().add(cmd);
  };

caseCommand:
	'escolha' OPA ID {
    checkSwitchType(_input.LT(-1).getText());
    _caseExpr = _input.LT(-1).getText();
  }
  CPA OBR (
		'quando' (INT | STRING) {
      _caseCondition = _input.LT(-1).getText();
      IsiVariable var = (IsiVariable) symbolTable.get(_caseExpr);
      checkCaseType(var.getType(), _caseCondition);
    }
    COLON {
      currentThread = new ArrayList<AbstractCommand>();
      stack.push(currentThread);
    }
    (cmd)+ 'pare' SC {
      caseList = stack.pop();
      _caseCommands.put(_caseCondition, caseList);
    }
	) + (
		'padrao' COLON {
      currentThread = new ArrayList<AbstractCommand>();
      stack.push(currentThread);
    }
    (cmd)+ 'pare' SC {
      _caseDefault = stack.pop();
      CaseCommand cmd = new CaseCommand(_caseExpr, _caseCommands, _caseDefault);
      stack.peek().add(cmd);
    }
	)? CBR;

expr: term expr_;

expr_: (
		OPSUM {_exprContent += '+';} term expr_
		| OPSUB {_exprContent += '-';} term expr_
	)?;

term: factor term_;

term_: (
		OPMULT {_exprContent += '*';} term
		| OPDIV {_exprContent += '/';} term
	)?;

factor:
	INT {
    _exprContent += _input.LT(-1).getText();
    checkTypeComp(_exprID, IsiVariable.INT);
  }
	| DOUBLE {
    _exprContent += _input.LT(-1).getText();
    checkTypeComp(_exprID, IsiVariable.DOUBLE);
  }
	| STRING {
    _exprContent += _input.LT(-1).getText();
    checkTypeComp(_exprID, IsiVariable.STRING);
  }
	| ID {
    checkID(_input.LT(-1).getText());
    _exprContent += _input.LT(-1).getText();
    IsiVariable var = (IsiVariable) symbolTable.get(_exprID);
    checkTypeComp(_exprID, var.getType());
  }
	| OPA {
    _exprContent += _input.LT(-1).getText();
  }
  expr CPA {
    _exprContent += _input.LT(-1).getText();
	};

SC: ';';
OPSUM: '+';
OPSUB: '-';
OPMULT: '*';
OPDIV: '/';
OPA: '(';
CPA: ')';
COLON: ':';
OP: '+' | '-' | '*' | '/';
ATTR: '=';
COMMA: ',';
OBR: '{';
CBR: '}';
OPREL: '>' | '<' | '>=' | '<=' | '==' | '!=';
ID: [a-z] ([a-z] | [A-Z] | [0-9])*;
DOUBLE: [0-9]+ ('.' [0-9]+);
INT: [0-9]+;
STRING: '"' ( '\\"' | .)*? '"';
WS: (' ' | '\t' | '\n' | '\r') -> skip;
