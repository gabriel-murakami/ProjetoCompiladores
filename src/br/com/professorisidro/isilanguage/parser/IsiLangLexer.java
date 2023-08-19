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

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, SC=15, OPSUM=16, OPSUB=17, 
		OPMULT=18, OPDIV=19, OPA=20, CPA=21, COLON=22, OP=23, ATTR=24, COMMA=25, 
		OBR=26, CBR=27, OPREL=28, ID=29, DOUBLE=30, INT=31, STRING=32, WS=33;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "SC", "OPSUM", "OPSUB", "OPMULT", 
			"OPDIV", "OPA", "CPA", "COLON", "OP", "ATTR", "COMMA", "OBR", "CBR", 
			"OPREL", "ID", "DOUBLE", "INT", "STRING", "WS"
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


	public IsiLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2#\u00ee\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27"+
		"\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\5\35\u00c7\n\35\3\36\3\36\7\36\u00cb\n\36\f"+
		"\36\16\36\u00ce\13\36\3\37\6\37\u00d1\n\37\r\37\16\37\u00d2\3\37\3\37"+
		"\6\37\u00d7\n\37\r\37\16\37\u00d8\3 \6 \u00dc\n \r \16 \u00dd\3!\3!\3"+
		"!\3!\7!\u00e4\n!\f!\16!\u00e7\13!\3!\3!\3\"\3\"\3\"\3\"\3\u00e5\2#\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#\3\2\b\5\2,-//\61\61\4\2>>@@\3\2c|\5\2\62;C\\c|\3\2\62;\5\2\13\f"+
		"\17\17\"\"\2\u00f7\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\3E"+
		"\3\2\2\2\5N\3\2\2\2\7W\3\2\2\2\t[\3\2\2\2\13b\3\2\2\2\ri\3\2\2\2\17n\3"+
		"\2\2\2\21v\3\2\2\2\23y\3\2\2\2\25\177\3\2\2\2\27\u0088\3\2\2\2\31\u0090"+
		"\3\2\2\2\33\u0097\3\2\2\2\35\u009c\3\2\2\2\37\u00a3\3\2\2\2!\u00a5\3\2"+
		"\2\2#\u00a7\3\2\2\2%\u00a9\3\2\2\2\'\u00ab\3\2\2\2)\u00ad\3\2\2\2+\u00af"+
		"\3\2\2\2-\u00b1\3\2\2\2/\u00b3\3\2\2\2\61\u00b5\3\2\2\2\63\u00b7\3\2\2"+
		"\2\65\u00b9\3\2\2\2\67\u00bb\3\2\2\29\u00c6\3\2\2\2;\u00c8\3\2\2\2=\u00d0"+
		"\3\2\2\2?\u00db\3\2\2\2A\u00df\3\2\2\2C\u00ea\3\2\2\2EF\7r\2\2FG\7t\2"+
		"\2GH\7q\2\2HI\7i\2\2IJ\7t\2\2JK\7c\2\2KL\7o\2\2LM\7c\2\2M\4\3\2\2\2NO"+
		"\7h\2\2OP\7k\2\2PQ\7o\2\2QR\7r\2\2RS\7t\2\2ST\7q\2\2TU\7i\2\2UV\7\60\2"+
		"\2V\6\3\2\2\2WX\7k\2\2XY\7p\2\2YZ\7v\2\2Z\b\3\2\2\2[\\\7f\2\2\\]\7q\2"+
		"\2]^\7w\2\2^_\7d\2\2_`\7n\2\2`a\7g\2\2a\n\3\2\2\2bc\7u\2\2cd\7v\2\2de"+
		"\7t\2\2ef\7k\2\2fg\7p\2\2gh\7i\2\2h\f\3\2\2\2ij\7n\2\2jk\7g\2\2kl\7k\2"+
		"\2lm\7c\2\2m\16\3\2\2\2no\7g\2\2op\7u\2\2pq\7e\2\2qr\7t\2\2rs\7g\2\2s"+
		"t\7x\2\2tu\7c\2\2u\20\3\2\2\2vw\7u\2\2wx\7g\2\2x\22\3\2\2\2yz\7u\2\2z"+
		"{\7g\2\2{|\7p\2\2|}\7c\2\2}~\7q\2\2~\24\3\2\2\2\177\u0080\7g\2\2\u0080"+
		"\u0081\7p\2\2\u0081\u0082\7s\2\2\u0082\u0083\7w\2\2\u0083\u0084\7c\2\2"+
		"\u0084\u0085\7p\2\2\u0085\u0086\7v\2\2\u0086\u0087\7q\2\2\u0087\26\3\2"+
		"\2\2\u0088\u0089\7g\2\2\u0089\u008a\7u\2\2\u008a\u008b\7e\2\2\u008b\u008c"+
		"\7q\2\2\u008c\u008d\7n\2\2\u008d\u008e\7j\2\2\u008e\u008f\7c\2\2\u008f"+
		"\30\3\2\2\2\u0090\u0091\7s\2\2\u0091\u0092\7w\2\2\u0092\u0093\7c\2\2\u0093"+
		"\u0094\7p\2\2\u0094\u0095\7f\2\2\u0095\u0096\7q\2\2\u0096\32\3\2\2\2\u0097"+
		"\u0098\7r\2\2\u0098\u0099\7c\2\2\u0099\u009a\7t\2\2\u009a\u009b\7g\2\2"+
		"\u009b\34\3\2\2\2\u009c\u009d\7r\2\2\u009d\u009e\7c\2\2\u009e\u009f\7"+
		"f\2\2\u009f\u00a0\7t\2\2\u00a0\u00a1\7c\2\2\u00a1\u00a2\7q\2\2\u00a2\36"+
		"\3\2\2\2\u00a3\u00a4\7=\2\2\u00a4 \3\2\2\2\u00a5\u00a6\7-\2\2\u00a6\""+
		"\3\2\2\2\u00a7\u00a8\7/\2\2\u00a8$\3\2\2\2\u00a9\u00aa\7,\2\2\u00aa&\3"+
		"\2\2\2\u00ab\u00ac\7\61\2\2\u00ac(\3\2\2\2\u00ad\u00ae\7*\2\2\u00ae*\3"+
		"\2\2\2\u00af\u00b0\7+\2\2\u00b0,\3\2\2\2\u00b1\u00b2\7<\2\2\u00b2.\3\2"+
		"\2\2\u00b3\u00b4\t\2\2\2\u00b4\60\3\2\2\2\u00b5\u00b6\7?\2\2\u00b6\62"+
		"\3\2\2\2\u00b7\u00b8\7.\2\2\u00b8\64\3\2\2\2\u00b9\u00ba\7}\2\2\u00ba"+
		"\66\3\2\2\2\u00bb\u00bc\7\177\2\2\u00bc8\3\2\2\2\u00bd\u00c7\t\3\2\2\u00be"+
		"\u00bf\7@\2\2\u00bf\u00c7\7?\2\2\u00c0\u00c1\7>\2\2\u00c1\u00c7\7?\2\2"+
		"\u00c2\u00c3\7?\2\2\u00c3\u00c7\7?\2\2\u00c4\u00c5\7#\2\2\u00c5\u00c7"+
		"\7?\2\2\u00c6\u00bd\3\2\2\2\u00c6\u00be\3\2\2\2\u00c6\u00c0\3\2\2\2\u00c6"+
		"\u00c2\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7:\3\2\2\2\u00c8\u00cc\t\4\2\2"+
		"\u00c9\u00cb\t\5\2\2\u00ca\u00c9\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca"+
		"\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd<\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf"+
		"\u00d1\t\6\2\2\u00d0\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d0\3\2"+
		"\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d6\7\60\2\2\u00d5"+
		"\u00d7\t\6\2\2\u00d6\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d6\3\2"+
		"\2\2\u00d8\u00d9\3\2\2\2\u00d9>\3\2\2\2\u00da\u00dc\t\6\2\2\u00db\u00da"+
		"\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de"+
		"@\3\2\2\2\u00df\u00e5\7$\2\2\u00e0\u00e1\7^\2\2\u00e1\u00e4\7$\2\2\u00e2"+
		"\u00e4\13\2\2\2\u00e3\u00e0\3\2\2\2\u00e3\u00e2\3\2\2\2\u00e4\u00e7\3"+
		"\2\2\2\u00e5\u00e6\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e8\3\2\2\2\u00e7"+
		"\u00e5\3\2\2\2\u00e8\u00e9\7$\2\2\u00e9B\3\2\2\2\u00ea\u00eb\t\7\2\2\u00eb"+
		"\u00ec\3\2\2\2\u00ec\u00ed\b\"\2\2\u00edD\3\2\2\2\13\2\u00c6\u00ca\u00cc"+
		"\u00d2\u00d8\u00dd\u00e3\u00e5\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}