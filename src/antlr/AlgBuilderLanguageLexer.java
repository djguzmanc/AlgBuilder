// Generated from AlgBuilderLanguage.g by ANTLR 4.5.3
package antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AlgBuilderLanguageLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, LINE_COMMENT=9, 
		BLOCK_COMMENT=10, WS=11, ASSP=12, OROP=13, ANDOP=14, EQOP=15, RELOP=16, 
		SUMOP=17, MULOP=18, POTOP=19, NOOP=20, MIN=21, DOT=22, SMCOLON=23, COMA=24, 
		TYPE=25, DOUBLE=26, STRING=27, INTEGER=28, BOOLEAN=29, COMI=30, PIZQ=31, 
		PDER=32, LLIZ=33, LLDE=34, BIZQ=35, BDER=36, ID=37;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "LINE_COMMENT", 
		"BLOCK_COMMENT", "WS", "ASSP", "OROP", "ANDOP", "EQOP", "RELOP", "SUMOP", 
		"MULOP", "POTOP", "NOOP", "MIN", "DOT", "SMCOLON", "COMA", "TYPE", "DOUBLE", 
		"STRING", "INTEGER", "BOOLEAN", "COMI", "PIZQ", "PDER", "LLIZ", "LLDE", 
		"BIZQ", "BDER", "ID"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'func'", "'main'", "'return'", "'print'", "'for'", "'while'", "'if'", 
		"'else'", null, null, null, "'='", null, null, null, null, null, null, 
		"'^'", null, null, "'.'", "';'", "','", null, null, null, null, null, 
		null, "'('", "')'", "'{'", "'}'", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, "LINE_COMMENT", 
		"BLOCK_COMMENT", "WS", "ASSP", "OROP", "ANDOP", "EQOP", "RELOP", "SUMOP", 
		"MULOP", "POTOP", "NOOP", "MIN", "DOT", "SMCOLON", "COMA", "TYPE", "DOUBLE", 
		"STRING", "INTEGER", "BOOLEAN", "COMI", "PIZQ", "PDER", "LLIZ", "LLDE", 
		"BIZQ", "BDER", "ID"
	};
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


	public AlgBuilderLanguageLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "AlgBuilderLanguage.g"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\'\u0135\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3"+
		"\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\7\n{\n\n\f\n\16\n~\13\n\3\n\3\n\3\13\3\13\3\13\3\13\7\13\u0086"+
		"\n\13\f\13\16\13\u0089\13\13\3\13\3\13\3\13\3\13\3\13\3\f\6\f\u0091\n"+
		"\f\r\f\16\f\u0092\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\20\5\20\u00a3\n\20\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00ab"+
		"\n\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30"+
		"\3\30\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5\32\u00fb\n\32\3\33\6\33"+
		"\u00fe\n\33\r\33\16\33\u00ff\3\33\3\33\6\33\u0104\n\33\r\33\16\33\u0105"+
		"\3\34\3\34\7\34\u010a\n\34\f\34\16\34\u010d\13\34\3\34\3\34\3\35\6\35"+
		"\u0112\n\35\r\35\16\35\u0113\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3"+
		"\36\5\36\u011f\n\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3"+
		"&\3&\7&\u0131\n&\f&\16&\u0134\13&\3\u0087\2\'\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26"+
		"+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'\3\2\f\4"+
		"\2\f\f\17\17\5\2\13\f\17\17\"\"\4\2--//\5\2\'\',,\61\61\3\2\62;\3\2\60"+
		"\60\5\2\f\f\17\17$$\4\2$$))\4\2C\\c|\6\2\62;C\\aac|\u014b\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"+
		"\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2"+
		"\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2"+
		"\2K\3\2\2\2\3M\3\2\2\2\5R\3\2\2\2\7W\3\2\2\2\t^\3\2\2\2\13d\3\2\2\2\r"+
		"h\3\2\2\2\17n\3\2\2\2\21q\3\2\2\2\23v\3\2\2\2\25\u0081\3\2\2\2\27\u0090"+
		"\3\2\2\2\31\u0096\3\2\2\2\33\u0098\3\2\2\2\35\u009b\3\2\2\2\37\u00a2\3"+
		"\2\2\2!\u00aa\3\2\2\2#\u00ac\3\2\2\2%\u00ae\3\2\2\2\'\u00b0\3\2\2\2)\u00b2"+
		"\3\2\2\2+\u00b4\3\2\2\2-\u00b6\3\2\2\2/\u00b8\3\2\2\2\61\u00ba\3\2\2\2"+
		"\63\u00fa\3\2\2\2\65\u00fd\3\2\2\2\67\u0107\3\2\2\29\u0111\3\2\2\2;\u011e"+
		"\3\2\2\2=\u0120\3\2\2\2?\u0122\3\2\2\2A\u0124\3\2\2\2C\u0126\3\2\2\2E"+
		"\u0128\3\2\2\2G\u012a\3\2\2\2I\u012c\3\2\2\2K\u012e\3\2\2\2MN\7h\2\2N"+
		"O\7w\2\2OP\7p\2\2PQ\7e\2\2Q\4\3\2\2\2RS\7o\2\2ST\7c\2\2TU\7k\2\2UV\7p"+
		"\2\2V\6\3\2\2\2WX\7t\2\2XY\7g\2\2YZ\7v\2\2Z[\7w\2\2[\\\7t\2\2\\]\7p\2"+
		"\2]\b\3\2\2\2^_\7r\2\2_`\7t\2\2`a\7k\2\2ab\7p\2\2bc\7v\2\2c\n\3\2\2\2"+
		"de\7h\2\2ef\7q\2\2fg\7t\2\2g\f\3\2\2\2hi\7y\2\2ij\7j\2\2jk\7k\2\2kl\7"+
		"n\2\2lm\7g\2\2m\16\3\2\2\2no\7k\2\2op\7h\2\2p\20\3\2\2\2qr\7g\2\2rs\7"+
		"n\2\2st\7u\2\2tu\7g\2\2u\22\3\2\2\2vw\7\61\2\2wx\7\61\2\2x|\3\2\2\2y{"+
		"\n\2\2\2zy\3\2\2\2{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\177\3\2\2\2~|\3\2\2"+
		"\2\177\u0080\b\n\2\2\u0080\24\3\2\2\2\u0081\u0082\7\61\2\2\u0082\u0083"+
		"\7,\2\2\u0083\u0087\3\2\2\2\u0084\u0086\13\2\2\2\u0085\u0084\3\2\2\2\u0086"+
		"\u0089\3\2\2\2\u0087\u0088\3\2\2\2\u0087\u0085\3\2\2\2\u0088\u008a\3\2"+
		"\2\2\u0089\u0087\3\2\2\2\u008a\u008b\7,\2\2\u008b\u008c\7\61\2\2\u008c"+
		"\u008d\3\2\2\2\u008d\u008e\b\13\2\2\u008e\26\3\2\2\2\u008f\u0091\t\3\2"+
		"\2\u0090\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093"+
		"\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\b\f\2\2\u0095\30\3\2\2\2\u0096"+
		"\u0097\7?\2\2\u0097\32\3\2\2\2\u0098\u0099\7~\2\2\u0099\u009a\7~\2\2\u009a"+
		"\34\3\2\2\2\u009b\u009c\7(\2\2\u009c\u009d\7(\2\2\u009d\36\3\2\2\2\u009e"+
		"\u009f\7?\2\2\u009f\u00a3\7?\2\2\u00a0\u00a1\7#\2\2\u00a1\u00a3\7?\2\2"+
		"\u00a2\u009e\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3 \3\2\2\2\u00a4\u00ab\7"+
		">\2\2\u00a5\u00a6\7>\2\2\u00a6\u00ab\7?\2\2\u00a7\u00ab\7@\2\2\u00a8\u00a9"+
		"\7@\2\2\u00a9\u00ab\7?\2\2\u00aa\u00a4\3\2\2\2\u00aa\u00a5\3\2\2\2\u00aa"+
		"\u00a7\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\"\3\2\2\2\u00ac\u00ad\t\4\2\2"+
		"\u00ad$\3\2\2\2\u00ae\u00af\t\5\2\2\u00af&\3\2\2\2\u00b0\u00b1\7`\2\2"+
		"\u00b1(\3\2\2\2\u00b2\u00b3\7#\2\2\u00b3*\3\2\2\2\u00b4\u00b5\7/\2\2\u00b5"+
		",\3\2\2\2\u00b6\u00b7\7\60\2\2\u00b7.\3\2\2\2\u00b8\u00b9\7=\2\2\u00b9"+
		"\60\3\2\2\2\u00ba\u00bb\7.\2\2\u00bb\62\3\2\2\2\u00bc\u00bd\7k\2\2\u00bd"+
		"\u00be\7p\2\2\u00be\u00fb\7v\2\2\u00bf\u00c0\7c\2\2\u00c0\u00c1\7p\2\2"+
		"\u00c1\u00fb\7{\2\2\u00c2\u00c3\7f\2\2\u00c3\u00c4\7q\2\2\u00c4\u00c5"+
		"\7w\2\2\u00c5\u00c6\7d\2\2\u00c6\u00c7\7n\2\2\u00c7\u00fb\7g\2\2\u00c8"+
		"\u00c9\7u\2\2\u00c9\u00ca\7v\2\2\u00ca\u00cb\7t\2\2\u00cb\u00cc\7k\2\2"+
		"\u00cc\u00cd\7p\2\2\u00cd\u00fb\7i\2\2\u00ce\u00cf\7d\2\2\u00cf\u00d0"+
		"\7q\2\2\u00d0\u00d1\7q\2\2\u00d1\u00fb\7n\2\2\u00d2\u00d3\7d\2\2\u00d3"+
		"\u00d4\7V\2\2\u00d4\u00d5\7t\2\2\u00d5\u00d6\7g\2\2\u00d6\u00fb\7g\2\2"+
		"\u00d7\u00d8\7i\2\2\u00d8\u00d9\7t\2\2\u00d9\u00da\7c\2\2\u00da\u00db"+
		"\7r\2\2\u00db\u00fb\7j\2\2\u00dc\u00dd\7c\2\2\u00dd\u00de\7t\2\2\u00de"+
		"\u00df\7t\2\2\u00df\u00e0\7c\2\2\u00e0\u00e1\7{\2\2\u00e1\u00e2\7N\2\2"+
		"\u00e2\u00e3\7k\2\2\u00e3\u00e4\7u\2\2\u00e4\u00fb\7v\2\2\u00e5\u00e6"+
		"\7n\2\2\u00e6\u00e7\7k\2\2\u00e7\u00e8\7p\2\2\u00e8\u00e9\7m\2\2\u00e9"+
		"\u00ea\7g\2\2\u00ea\u00eb\7f\2\2\u00eb\u00ec\7N\2\2\u00ec\u00ed\7k\2\2"+
		"\u00ed\u00ee\7u\2\2\u00ee\u00fb\7v\2\2\u00ef\u00f0\7i\2\2\u00f0\u00f1"+
		"\7P\2\2\u00f1\u00f2\7q\2\2\u00f2\u00f3\7f\2\2\u00f3\u00fb\7g\2\2\u00f4"+
		"\u00f5\7d\2\2\u00f5\u00f6\7v\2\2\u00f6\u00f7\7P\2\2\u00f7\u00f8\7q\2\2"+
		"\u00f8\u00f9\7f\2\2\u00f9\u00fb\7g\2\2\u00fa\u00bc\3\2\2\2\u00fa\u00bf"+
		"\3\2\2\2\u00fa\u00c2\3\2\2\2\u00fa\u00c8\3\2\2\2\u00fa\u00ce\3\2\2\2\u00fa"+
		"\u00d2\3\2\2\2\u00fa\u00d7\3\2\2\2\u00fa\u00dc\3\2\2\2\u00fa\u00e5\3\2"+
		"\2\2\u00fa\u00ef\3\2\2\2\u00fa\u00f4\3\2\2\2\u00fb\64\3\2\2\2\u00fc\u00fe"+
		"\t\6\2\2\u00fd\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff"+
		"\u0100\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0103\t\7\2\2\u0102\u0104\t\6"+
		"\2\2\u0103\u0102\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0103\3\2\2\2\u0105"+
		"\u0106\3\2\2\2\u0106\66\3\2\2\2\u0107\u010b\5=\37\2\u0108\u010a\n\b\2"+
		"\2\u0109\u0108\3\2\2\2\u010a\u010d\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c"+
		"\3\2\2\2\u010c\u010e\3\2\2\2\u010d\u010b\3\2\2\2\u010e\u010f\5=\37\2\u010f"+
		"8\3\2\2\2\u0110\u0112\t\6\2\2\u0111\u0110\3\2\2\2\u0112\u0113\3\2\2\2"+
		"\u0113\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114:\3\2\2\2\u0115\u0116\7"+
		"v\2\2\u0116\u0117\7t\2\2\u0117\u0118\7w\2\2\u0118\u011f\7g\2\2\u0119\u011a"+
		"\7h\2\2\u011a\u011b\7c\2\2\u011b\u011c\7n\2\2\u011c\u011d\7u\2\2\u011d"+
		"\u011f\7g\2\2\u011e\u0115\3\2\2\2\u011e\u0119\3\2\2\2\u011f<\3\2\2\2\u0120"+
		"\u0121\t\t\2\2\u0121>\3\2\2\2\u0122\u0123\7*\2\2\u0123@\3\2\2\2\u0124"+
		"\u0125\7+\2\2\u0125B\3\2\2\2\u0126\u0127\7}\2\2\u0127D\3\2\2\2\u0128\u0129"+
		"\7\177\2\2\u0129F\3\2\2\2\u012a\u012b\7]\2\2\u012bH\3\2\2\2\u012c\u012d"+
		"\7_\2\2\u012dJ\3\2\2\2\u012e\u0132\t\n\2\2\u012f\u0131\t\13\2\2\u0130"+
		"\u012f\3\2\2\2\u0131\u0134\3\2\2\2\u0132\u0130\3\2\2\2\u0132\u0133\3\2"+
		"\2\2\u0133L\3\2\2\2\u0134\u0132\3\2\2\2\17\2|\u0087\u0092\u00a2\u00aa"+
		"\u00fa\u00ff\u0105\u010b\u0113\u011e\u0132\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}