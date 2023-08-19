// Generated from IsiLang.g4 by ANTLR 4.9.3
package br.com.professorisidro.isilanguage.parser;

	import br.com.professorisidro.isilanguage.datastructures.IsiSymbol;
	import br.com.professorisidro.isilanguage.datastructures.IsiVariable;
	import br.com.professorisidro.isilanguage.datastructures.IsiSymbolTable;
	import br.com.professorisidro.isilanguage.exceptions.SemanticException;
	import br.com.professorisidro.isilanguage.ast.*;
	import java.util.ArrayList;
	import java.util.Stack;
  import java.util.HashMap;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, SC=15, OPSUM=16, OPSUB=17, 
		OPMULT=18, OPDIV=19, OPA=20, CPA=21, COLON=22, OP=23, ATTR=24, COMMA=25, 
		OBR=26, CBR=27, OPREL=28, ID=29, DOUBLE=30, INT=31, STRING=32, WS=33;
	public static final int
		RULE_prog = 0, RULE_varDeclaration = 1, RULE_type = 2, RULE_block = 3, 
		RULE_cmd = 4, RULE_readCommand = 5, RULE_writeCommand = 6, RULE_attribCommand = 7, 
		RULE_conditionalCommand = 8, RULE_whileCommand = 9, RULE_caseCommand = 10, 
		RULE_expr = 11, RULE_expr_ = 12, RULE_term = 13, RULE_term_ = 14, RULE_factor = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "varDeclaration", "type", "block", "cmd", "readCommand", "writeCommand", 
			"attribCommand", "conditionalCommand", "whileCommand", "caseCommand", 
			"expr", "expr_", "term", "term_", "factor"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'programa'", "'fimprog.'", "'int'", "'double'", "'string'", "'leia'", 
			"'escreva'", "'se'", "'senao'", "'enquanto'", "'escolha'", "'quando'", 
			"'pare'", "'padrao'", "';'", "'+'", "'-'", "'*'", "'/'", "'('", "')'", 
			"':'", null, "'='", "','", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, "SC", "OPSUM", "OPSUB", "OPMULT", "OPDIV", "OPA", "CPA", 
			"COLON", "OP", "ATTR", "COMMA", "OBR", "CBR", "OPREL", "ID", "DOUBLE", 
			"INT", "STRING", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public IsiLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(T__0);
			setState(34); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(33);
				varDeclaration();
				}
				}
				setState(36); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4))) != 0) );
			setState(38);
			block();
			setState(39);
			match(T__1);

			    checkUnused();
			    program.setVarTable(symbolTable);
			    program.setCommands(stack.pop());

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public List<TerminalNode> COMMA() { return getTokens(IsiLangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(IsiLangParser.COMMA, i);
		}
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitVarDeclaration(this);
		}
	}

	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_varDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			type();
			setState(43);
			match(ID);

			    _varName = _input.LT(-1).getText();
			    _varValue = null;
			    symbol = new IsiVariable(_varName, _type, _varValue);
			    if (!symbolTable.exists(_varName)){
			      symbolTable.add(symbol);
			    }
			    else{
			      throw new SemanticException("Symbol " + _varName + " already declared");
			    }
			  
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(45);
				match(COMMA);
				setState(46);
				match(ID);

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
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53);
			match(SC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type);
		try {
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				match(T__2);
				 _type = IsiVariable.INT;  
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				match(T__3);
				 _type = IsiVariable.DOUBLE; 
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(59);
				match(T__4);
				 _type = IsiVariable.STRING;  
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{

			    currentThread = new ArrayList<AbstractCommand>();
				  stack.push(currentThread);
			  
			setState(65); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(64);
				cmd();
				}
				}
				setState(67); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public ReadCommandContext readCommand() {
			return getRuleContext(ReadCommandContext.class,0);
		}
		public WriteCommandContext writeCommand() {
			return getRuleContext(WriteCommandContext.class,0);
		}
		public AttribCommandContext attribCommand() {
			return getRuleContext(AttribCommandContext.class,0);
		}
		public ConditionalCommandContext conditionalCommand() {
			return getRuleContext(ConditionalCommandContext.class,0);
		}
		public WhileCommandContext whileCommand() {
			return getRuleContext(WhileCommandContext.class,0);
		}
		public CaseCommandContext caseCommand() {
			return getRuleContext(CaseCommandContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_cmd);
		try {
			setState(75);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				readCommand();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				writeCommand();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				attribCommand();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 4);
				{
				setState(72);
				conditionalCommand();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 5);
				{
				setState(73);
				whileCommand();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 6);
				{
				setState(74);
				caseCommand();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReadCommandContext extends ParserRuleContext {
		public TerminalNode OPA() { return getToken(IsiLangParser.OPA, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode CPA() { return getToken(IsiLangParser.CPA, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public ReadCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_readCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterReadCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitReadCommand(this);
		}
	}

	public final ReadCommandContext readCommand() throws RecognitionException {
		ReadCommandContext _localctx = new ReadCommandContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_readCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(T__5);
			setState(78);
			match(OPA);
			setState(79);
			match(ID);

			    checkID(_input.LT(-1).getText());
			    _readID = _input.LT(-1).getText();
			  
			setState(81);
			match(CPA);
			setState(82);
			match(SC);

			    IsiVariable var = (IsiVariable)symbolTable.get(_readID);
			    ReadCommand cmd = new ReadCommand(_readID, var);
			    stack.peek().add(cmd);
			  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WriteCommandContext extends ParserRuleContext {
		public TerminalNode OPA() { return getToken(IsiLangParser.OPA, 0); }
		public TerminalNode CPA() { return getToken(IsiLangParser.CPA, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode INT() { return getToken(IsiLangParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(IsiLangParser.DOUBLE, 0); }
		public TerminalNode STRING() { return getToken(IsiLangParser.STRING, 0); }
		public WriteCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_writeCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterWriteCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitWriteCommand(this);
		}
	}

	public final WriteCommandContext writeCommand() throws RecognitionException {
		WriteCommandContext _localctx = new WriteCommandContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_writeCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(T__6);
			setState(86);
			match(OPA);
			setState(95);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				{
				setState(87);
				match(ID);

				      checkID(_input.LT(-1).getText());
					     _writeID = _input.LT(-1).getText();
				    
				}
				break;
			case INT:
				{
				setState(89);
				match(INT);
				_writeID = _input.LT(-1).getText();
				}
				break;
			case DOUBLE:
				{
				setState(91);
				match(DOUBLE);
				_writeID = _input.LT(-1).getText();
				}
				break;
			case STRING:
				{
				setState(93);
				match(STRING);
				_writeID = _input.LT(-1).getText();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(97);
			match(CPA);
			setState(98);
			match(SC);

			    WriteCommand cmd = new WriteCommand(_writeID);
			    stack.peek().add(cmd);
			  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttribCommandContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public AttribCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterAttribCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitAttribCommand(this);
		}
	}

	public final AttribCommandContext attribCommand() throws RecognitionException {
		AttribCommandContext _localctx = new AttribCommandContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_attribCommand);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(ID);

			    checkID(_input.LT(-1).getText());
			    _exprID = _input.LT(-1).getText();
			  
			setState(103);
			match(ATTR);

			    _exprContent = "";
			  
			setState(105);
			expr();
			setState(106);
			match(SC);

			    AssignmentCommand cmd = new AssignmentCommand(_exprID, _exprContent);
			    stack.peek().add(cmd);
			    IsiVariable var = (IsiVariable) symbolTable.get(_exprID);
			    var.setValue(_exprContent);
			    symbolTable.get(_exprID).setUsed();
			  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionalCommandContext extends ParserRuleContext {
		public TerminalNode OPA() { return getToken(IsiLangParser.OPA, 0); }
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode CPA() { return getToken(IsiLangParser.CPA, 0); }
		public List<TerminalNode> OBR() { return getTokens(IsiLangParser.OBR); }
		public TerminalNode OBR(int i) {
			return getToken(IsiLangParser.OBR, i);
		}
		public List<TerminalNode> CBR() { return getTokens(IsiLangParser.CBR); }
		public TerminalNode CBR(int i) {
			return getToken(IsiLangParser.CBR, i);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public ConditionalCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterConditionalCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitConditionalCommand(this);
		}
	}

	public final ConditionalCommandContext conditionalCommand() throws RecognitionException {
		ConditionalCommandContext _localctx = new ConditionalCommandContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_conditionalCommand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(T__7);
			setState(110);
			match(OPA);
			{
			setState(111);
			match(ID);
			}

			    _conditionalExpr = _input.LT(-1).getText();
			  
			setState(113);
			match(OPREL);

			    _conditionalExpr += _input.LT(-1).getText();
				
			setState(117);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(115);
				match(ID);
				}
				break;
			case 2:
				{
				setState(116);
				expr();
				}
				break;
			}

			    _conditionalExpr += _input.LT(-1).getText();
			  
			setState(120);
			match(CPA);
			setState(121);
			match(OBR);

			    currentThread = new ArrayList<AbstractCommand>();
			    stack.push(currentThread);
			  
			setState(124); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(123);
				cmd();
				}
				}
				setState(126); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			setState(128);
			match(CBR);

			    trueList = stack.pop();
			  
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(130);
				match(T__8);
				setState(131);
				match(OBR);

				      currentThread = new ArrayList<AbstractCommand>();
				      stack.push(currentThread);
				      
				{
				setState(134); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(133);
					cmd();
					}
					}
					setState(136); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				}
				setState(138);
				match(CBR);

				      falseList = stack.pop();
				      ConditionalCommand cmd = new ConditionalCommand(_conditionalExpr, trueList, falseList);
				      stack.peek().add(cmd);
				    
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileCommandContext extends ParserRuleContext {
		public TerminalNode OPA() { return getToken(IsiLangParser.OPA, 0); }
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode CPA() { return getToken(IsiLangParser.CPA, 0); }
		public TerminalNode OBR() { return getToken(IsiLangParser.OBR, 0); }
		public TerminalNode CBR() { return getToken(IsiLangParser.CBR, 0); }
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public WhileCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterWhileCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitWhileCommand(this);
		}
	}

	public final WhileCommandContext whileCommand() throws RecognitionException {
		WhileCommandContext _localctx = new WhileCommandContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_whileCommand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			match(T__9);
			setState(144);
			match(OPA);
			setState(147);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(145);
				match(ID);
				}
				break;
			case 2:
				{
				setState(146);
				expr();
				}
				break;
			}

			    _whileExpr = _input.LT(-1).getText();
			  
			setState(150);
			match(OPREL);

			    _whileExpr += _input.LT(-1).getText();
				
			setState(154);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				setState(152);
				match(ID);
				}
				break;
			case 2:
				{
				setState(153);
				expr();
				}
				break;
			}

			    _whileExpr += _input.LT(-1).getText();
			  
			setState(157);
			match(CPA);
			setState(158);
			match(OBR);

			    currentThread = new ArrayList<AbstractCommand>();
			    stack.push(currentThread);
			  
			setState(161); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(160);
				cmd();
				}
				}
				setState(163); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			setState(165);
			match(CBR);

			    whileList = stack.pop();
			    WhileCommand cmd = new WhileCommand(_whileExpr, whileList);
			    stack.peek().add(cmd);
			  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CaseCommandContext extends ParserRuleContext {
		public TerminalNode OPA() { return getToken(IsiLangParser.OPA, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode CPA() { return getToken(IsiLangParser.CPA, 0); }
		public TerminalNode OBR() { return getToken(IsiLangParser.OBR, 0); }
		public TerminalNode CBR() { return getToken(IsiLangParser.CBR, 0); }
		public List<TerminalNode> COLON() { return getTokens(IsiLangParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(IsiLangParser.COLON, i);
		}
		public List<TerminalNode> SC() { return getTokens(IsiLangParser.SC); }
		public TerminalNode SC(int i) {
			return getToken(IsiLangParser.SC, i);
		}
		public List<TerminalNode> INT() { return getTokens(IsiLangParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(IsiLangParser.INT, i);
		}
		public List<TerminalNode> STRING() { return getTokens(IsiLangParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(IsiLangParser.STRING, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CaseCommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseCommand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCaseCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCaseCommand(this);
		}
	}

	public final CaseCommandContext caseCommand() throws RecognitionException {
		CaseCommandContext _localctx = new CaseCommandContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_caseCommand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(T__10);
			setState(169);
			match(OPA);
			setState(170);
			match(ID);

			    checkSwitchType(_input.LT(-1).getText());
			    _caseExpr = _input.LT(-1).getText();
			  
			setState(172);
			match(CPA);
			setState(173);
			match(OBR);
			setState(188); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(174);
				match(T__11);
				setState(175);
				_la = _input.LA(1);
				if ( !(_la==INT || _la==STRING) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}

				      _caseCondition = _input.LT(-1).getText();
				      IsiVariable var = (IsiVariable) symbolTable.get(_caseExpr);
				      checkCaseType(var.getType(), _caseCondition);
				    
				setState(177);
				match(COLON);

				      currentThread = new ArrayList<AbstractCommand>();
				      stack.push(currentThread);
				    
				setState(180); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(179);
					cmd();
					}
					}
					setState(182); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				setState(184);
				match(T__12);
				setState(185);
				match(SC);

				      caseList = stack.pop();
				      _caseCommands.put(_caseCondition, caseList);
				    
				}
				}
				setState(190); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__11 );
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(192);
				match(T__13);
				setState(193);
				match(COLON);

				      currentThread = new ArrayList<AbstractCommand>();
				      stack.push(currentThread);
				    
				setState(196); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(195);
					cmd();
					}
					}
					setState(198); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				setState(200);
				match(T__12);
				setState(201);
				match(SC);

				      _caseDefault = stack.pop();
				      CaseCommand cmd = new CaseCommand(_caseExpr, _caseCommands, _caseDefault);
				      stack.peek().add(cmd);
				    
				}
			}

			setState(206);
			match(CBR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public Expr_Context expr_() {
			return getRuleContext(Expr_Context.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			term();
			setState(209);
			expr_();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expr_Context extends ParserRuleContext {
		public TerminalNode OPSUM() { return getToken(IsiLangParser.OPSUM, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public Expr_Context expr_() {
			return getRuleContext(Expr_Context.class,0);
		}
		public TerminalNode OPSUB() { return getToken(IsiLangParser.OPSUB, 0); }
		public Expr_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr_(this);
		}
	}

	public final Expr_Context expr_() throws RecognitionException {
		Expr_Context _localctx = new Expr_Context(_ctx, getState());
		enterRule(_localctx, 24, RULE_expr_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPSUM:
				{
				setState(211);
				match(OPSUM);
				_exprContent += '+';
				setState(213);
				term();
				setState(214);
				expr_();
				}
				break;
			case OPSUB:
				{
				setState(216);
				match(OPSUB);
				_exprContent += '-';
				setState(218);
				term();
				setState(219);
				expr_();
				}
				break;
			case SC:
			case CPA:
			case OPREL:
				break;
			default:
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public Term_Context term_() {
			return getRuleContext(Term_Context.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTerm(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_term);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			factor();
			setState(224);
			term_();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Term_Context extends ParserRuleContext {
		public TerminalNode OPMULT() { return getToken(IsiLangParser.OPMULT, 0); }
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode OPDIV() { return getToken(IsiLangParser.OPDIV, 0); }
		public Term_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTerm_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTerm_(this);
		}
	}

	public final Term_Context term_() throws RecognitionException {
		Term_Context _localctx = new Term_Context(_ctx, getState());
		enterRule(_localctx, 28, RULE_term_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPMULT:
				{
				setState(226);
				match(OPMULT);
				_exprContent += '*';
				setState(228);
				term();
				}
				break;
			case OPDIV:
				{
				setState(229);
				match(OPDIV);
				_exprContent += '/';
				setState(231);
				term();
				}
				break;
			case SC:
			case OPSUM:
			case OPSUB:
			case CPA:
			case OPREL:
				break;
			default:
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(IsiLangParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(IsiLangParser.DOUBLE, 0); }
		public TerminalNode STRING() { return getToken(IsiLangParser.STRING, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode OPA() { return getToken(IsiLangParser.OPA, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode CPA() { return getToken(IsiLangParser.CPA, 0); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitFactor(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_factor);
		try {
			setState(248);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(234);
				match(INT);

				    _exprContent += _input.LT(-1).getText();
				    checkTypeComp(_exprID, IsiVariable.INT);
				  
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 2);
				{
				setState(236);
				match(DOUBLE);

				    _exprContent += _input.LT(-1).getText();
				    checkTypeComp(_exprID, IsiVariable.DOUBLE);
				  
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(238);
				match(STRING);

				    _exprContent += _input.LT(-1).getText();
				    checkTypeComp(_exprID, IsiVariable.STRING);
				  
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(240);
				match(ID);

				    checkID(_input.LT(-1).getText());
				    _exprContent += _input.LT(-1).getText();
				    IsiVariable var = (IsiVariable) symbolTable.get(_exprID);
				    checkTypeComp(_exprID, var.getType());
				  
				}
				break;
			case OPA:
				enterOuterAlt(_localctx, 5);
				{
				setState(242);
				match(OPA);

				    _exprContent += _input.LT(-1).getText();
				  
				setState(244);
				expr();
				setState(245);
				match(CPA);

				    _exprContent += _input.LT(-1).getText();
					
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3#\u00fd\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\6"+
		"\2%\n\2\r\2\16\2&\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3\63\n\3\f"+
		"\3\16\3\66\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4@\n\4\3\5\3\5\6\5D"+
		"\n\5\r\5\16\5E\3\6\3\6\3\6\3\6\3\6\3\6\5\6N\n\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bb\n\b\3\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\5\nx\n\n\3\n\3\n\3\n\3\n\3\n\6\n\177\n\n\r\n\16\n\u0080\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\6\n\u0089\n\n\r\n\16\n\u008a\3\n\3\n\3\n\5\n\u0090\n\n\3\13"+
		"\3\13\3\13\3\13\5\13\u0096\n\13\3\13\3\13\3\13\3\13\3\13\5\13\u009d\n"+
		"\13\3\13\3\13\3\13\3\13\3\13\6\13\u00a4\n\13\r\13\16\13\u00a5\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u00b7\n\f\r"+
		"\f\16\f\u00b8\3\f\3\f\3\f\3\f\6\f\u00bf\n\f\r\f\16\f\u00c0\3\f\3\f\3\f"+
		"\3\f\6\f\u00c7\n\f\r\f\16\f\u00c8\3\f\3\f\3\f\3\f\5\f\u00cf\n\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16"+
		"\u00e0\n\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00eb\n"+
		"\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\5\21\u00fb\n\21\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \2\3\3\2!\"\2\u010c\2\"\3\2\2\2\4,\3\2\2\2\6?\3\2\2\2\bA\3\2\2\2\nM\3"+
		"\2\2\2\fO\3\2\2\2\16W\3\2\2\2\20g\3\2\2\2\22o\3\2\2\2\24\u0091\3\2\2\2"+
		"\26\u00aa\3\2\2\2\30\u00d2\3\2\2\2\32\u00df\3\2\2\2\34\u00e1\3\2\2\2\36"+
		"\u00ea\3\2\2\2 \u00fa\3\2\2\2\"$\7\3\2\2#%\5\4\3\2$#\3\2\2\2%&\3\2\2\2"+
		"&$\3\2\2\2&\'\3\2\2\2\'(\3\2\2\2()\5\b\5\2)*\7\4\2\2*+\b\2\1\2+\3\3\2"+
		"\2\2,-\5\6\4\2-.\7\37\2\2.\64\b\3\1\2/\60\7\33\2\2\60\61\7\37\2\2\61\63"+
		"\b\3\1\2\62/\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\67\3"+
		"\2\2\2\66\64\3\2\2\2\678\7\21\2\28\5\3\2\2\29:\7\5\2\2:@\b\4\1\2;<\7\6"+
		"\2\2<@\b\4\1\2=>\7\7\2\2>@\b\4\1\2?9\3\2\2\2?;\3\2\2\2?=\3\2\2\2@\7\3"+
		"\2\2\2AC\b\5\1\2BD\5\n\6\2CB\3\2\2\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\t"+
		"\3\2\2\2GN\5\f\7\2HN\5\16\b\2IN\5\20\t\2JN\5\22\n\2KN\5\24\13\2LN\5\26"+
		"\f\2MG\3\2\2\2MH\3\2\2\2MI\3\2\2\2MJ\3\2\2\2MK\3\2\2\2ML\3\2\2\2N\13\3"+
		"\2\2\2OP\7\b\2\2PQ\7\26\2\2QR\7\37\2\2RS\b\7\1\2ST\7\27\2\2TU\7\21\2\2"+
		"UV\b\7\1\2V\r\3\2\2\2WX\7\t\2\2Xa\7\26\2\2YZ\7\37\2\2Zb\b\b\1\2[\\\7!"+
		"\2\2\\b\b\b\1\2]^\7 \2\2^b\b\b\1\2_`\7\"\2\2`b\b\b\1\2aY\3\2\2\2a[\3\2"+
		"\2\2a]\3\2\2\2a_\3\2\2\2bc\3\2\2\2cd\7\27\2\2de\7\21\2\2ef\b\b\1\2f\17"+
		"\3\2\2\2gh\7\37\2\2hi\b\t\1\2ij\7\32\2\2jk\b\t\1\2kl\5\30\r\2lm\7\21\2"+
		"\2mn\b\t\1\2n\21\3\2\2\2op\7\n\2\2pq\7\26\2\2qr\7\37\2\2rs\b\n\1\2st\7"+
		"\36\2\2tw\b\n\1\2ux\7\37\2\2vx\5\30\r\2wu\3\2\2\2wv\3\2\2\2xy\3\2\2\2"+
		"yz\b\n\1\2z{\7\27\2\2{|\7\34\2\2|~\b\n\1\2}\177\5\n\6\2~}\3\2\2\2\177"+
		"\u0080\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\3\2\2\2"+
		"\u0082\u0083\7\35\2\2\u0083\u008f\b\n\1\2\u0084\u0085\7\13\2\2\u0085\u0086"+
		"\7\34\2\2\u0086\u0088\b\n\1\2\u0087\u0089\5\n\6\2\u0088\u0087\3\2\2\2"+
		"\u0089\u008a\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c"+
		"\3\2\2\2\u008c\u008d\7\35\2\2\u008d\u008e\b\n\1\2\u008e\u0090\3\2\2\2"+
		"\u008f\u0084\3\2\2\2\u008f\u0090\3\2\2\2\u0090\23\3\2\2\2\u0091\u0092"+
		"\7\f\2\2\u0092\u0095\7\26\2\2\u0093\u0096\7\37\2\2\u0094\u0096\5\30\r"+
		"\2\u0095\u0093\3\2\2\2\u0095\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098"+
		"\b\13\1\2\u0098\u0099\7\36\2\2\u0099\u009c\b\13\1\2\u009a\u009d\7\37\2"+
		"\2\u009b\u009d\5\30\r\2\u009c\u009a\3\2\2\2\u009c\u009b\3\2\2\2\u009d"+
		"\u009e\3\2\2\2\u009e\u009f\b\13\1\2\u009f\u00a0\7\27\2\2\u00a0\u00a1\7"+
		"\34\2\2\u00a1\u00a3\b\13\1\2\u00a2\u00a4\5\n\6\2\u00a3\u00a2\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\3\2"+
		"\2\2\u00a7\u00a8\7\35\2\2\u00a8\u00a9\b\13\1\2\u00a9\25\3\2\2\2\u00aa"+
		"\u00ab\7\r\2\2\u00ab\u00ac\7\26\2\2\u00ac\u00ad\7\37\2\2\u00ad\u00ae\b"+
		"\f\1\2\u00ae\u00af\7\27\2\2\u00af\u00be\7\34\2\2\u00b0\u00b1\7\16\2\2"+
		"\u00b1\u00b2\t\2\2\2\u00b2\u00b3\b\f\1\2\u00b3\u00b4\7\30\2\2\u00b4\u00b6"+
		"\b\f\1\2\u00b5\u00b7\5\n\6\2\u00b6\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8"+
		"\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\7\17"+
		"\2\2\u00bb\u00bc\7\21\2\2\u00bc\u00bd\b\f\1\2\u00bd\u00bf\3\2\2\2\u00be"+
		"\u00b0\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2"+
		"\2\2\u00c1\u00ce\3\2\2\2\u00c2\u00c3\7\20\2\2\u00c3\u00c4\7\30\2\2\u00c4"+
		"\u00c6\b\f\1\2\u00c5\u00c7\5\n\6\2\u00c6\u00c5\3\2\2\2\u00c7\u00c8\3\2"+
		"\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca"+
		"\u00cb\7\17\2\2\u00cb\u00cc\7\21\2\2\u00cc\u00cd\b\f\1\2\u00cd\u00cf\3"+
		"\2\2\2\u00ce\u00c2\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0"+
		"\u00d1\7\35\2\2\u00d1\27\3\2\2\2\u00d2\u00d3\5\34\17\2\u00d3\u00d4\5\32"+
		"\16\2\u00d4\31\3\2\2\2\u00d5\u00d6\7\22\2\2\u00d6\u00d7\b\16\1\2\u00d7"+
		"\u00d8\5\34\17\2\u00d8\u00d9\5\32\16\2\u00d9\u00e0\3\2\2\2\u00da\u00db"+
		"\7\23\2\2\u00db\u00dc\b\16\1\2\u00dc\u00dd\5\34\17\2\u00dd\u00de\5\32"+
		"\16\2\u00de\u00e0\3\2\2\2\u00df\u00d5\3\2\2\2\u00df\u00da\3\2\2\2\u00df"+
		"\u00e0\3\2\2\2\u00e0\33\3\2\2\2\u00e1\u00e2\5 \21\2\u00e2\u00e3\5\36\20"+
		"\2\u00e3\35\3\2\2\2\u00e4\u00e5\7\24\2\2\u00e5\u00e6\b\20\1\2\u00e6\u00eb"+
		"\5\34\17\2\u00e7\u00e8\7\25\2\2\u00e8\u00e9\b\20\1\2\u00e9\u00eb\5\34"+
		"\17\2\u00ea\u00e4\3\2\2\2\u00ea\u00e7\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb"+
		"\37\3\2\2\2\u00ec\u00ed\7!\2\2\u00ed\u00fb\b\21\1\2\u00ee\u00ef\7 \2\2"+
		"\u00ef\u00fb\b\21\1\2\u00f0\u00f1\7\"\2\2\u00f1\u00fb\b\21\1\2\u00f2\u00f3"+
		"\7\37\2\2\u00f3\u00fb\b\21\1\2\u00f4\u00f5\7\26\2\2\u00f5\u00f6\b\21\1"+
		"\2\u00f6\u00f7\5\30\r\2\u00f7\u00f8\7\27\2\2\u00f8\u00f9\b\21\1\2\u00f9"+
		"\u00fb\3\2\2\2\u00fa\u00ec\3\2\2\2\u00fa\u00ee\3\2\2\2\u00fa\u00f0\3\2"+
		"\2\2\u00fa\u00f2\3\2\2\2\u00fa\u00f4\3\2\2\2\u00fb!\3\2\2\2\26&\64?EM"+
		"aw\u0080\u008a\u008f\u0095\u009c\u00a5\u00b8\u00c0\u00c8\u00ce\u00df\u00ea"+
		"\u00fa";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}