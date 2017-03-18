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
public class AlgBuilderLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, LINE_COMMENT=13, BLOCK_COMMENT=14, WS=15, 
		ASSP=16, OROP=17, ANDOP=18, EQOP=19, RELOP=20, SUMOP=21, MULOP=22, POTOP=23, 
		NOOP=24, SMCOLON=25, COMA=26, TYPE=27, DOUBLE=28, STRING=29, INTEGER=30, 
		BOOLEAN=31, COMI=32, PIZQ=33, PDER=34, LLIZ=35, LLDE=36, BIZQ=37, BDER=38, 
		ID=39;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "LINE_COMMENT", "BLOCK_COMMENT", "WS", "ASSP", 
		"OROP", "ANDOP", "EQOP", "RELOP", "SUMOP", "MULOP", "POTOP", "NOOP", "SMCOLON", 
		"COMA", "TYPE", "DOUBLE", "STRING", "INTEGER", "BOOLEAN", "COMI", "PIZQ", 
		"PDER", "LLIZ", "LLDE", "BIZQ", "BDER", "ID"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'main'", "'drawRandomEllipse'", "'print'", "'int'", "'double'", 
		"'bool'", "'string'", "'array'", "'list'", "'tree'", "'graph'", "'.'", 
		null, null, null, "'='", null, null, null, null, null, null, "'^'", null, 
		"';'", "','", null, null, null, null, null, null, "'('", "')'", "'{'", 
		"'}'", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "LINE_COMMENT", "BLOCK_COMMENT", "WS", "ASSP", "OROP", "ANDOP", 
		"EQOP", "RELOP", "SUMOP", "MULOP", "POTOP", "NOOP", "SMCOLON", "COMA", 
		"TYPE", "DOUBLE", "STRING", "INTEGER", "BOOLEAN", "COMI", "PIZQ", "PDER", 
		"LLIZ", "LLDE", "BIZQ", "BDER", "ID"
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


	public AlgBuilderLexer(CharStream input) {
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2)\u0136\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\r\3\r\3\16\3\16\3\16\3\16\7\16\u00a2\n\16\f\16\16\16\u00a5\13\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\7\17\u00ad\n\17\f\17\16\17\u00b0\13\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\20\6\20\u00b8\n\20\r\20\16\20\u00b9\3\20\3"+
		"\20\3\21\3\21\3\22\3\22\3\22\5\22\u00c3\n\22\3\23\3\23\3\23\3\23\5\23"+
		"\u00c9\n\23\3\24\3\24\3\24\3\24\5\24\u00cf\n\24\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\5\25\u00d7\n\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\5\31\u00e3\n\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34"+
		"\u00fc\n\34\3\35\6\35\u00ff\n\35\r\35\16\35\u0100\3\35\3\35\6\35\u0105"+
		"\n\35\r\35\16\35\u0106\3\36\3\36\7\36\u010b\n\36\f\36\16\36\u010e\13\36"+
		"\3\36\3\36\3\37\6\37\u0113\n\37\r\37\16\37\u0114\3 \3 \3 \3 \3 \3 \3 "+
		"\3 \3 \5 \u0120\n \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3"+
		"(\7(\u0132\n(\f(\16(\u0135\13(\3\u00ae\2)\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)\3\2"+
		"\f\4\2\f\f\17\17\5\2\13\f\17\17\"\"\4\2--//\5\2\'\',,\61\61\3\2\62;\3"+
		"\2\60\60\5\2\f\f\17\17$$\4\2$$))\4\2C\\c|\6\2\62;C\\aac|\u0148\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3"+
		"\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2"+
		"=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3"+
		"\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\3Q\3\2\2\2\5V\3\2\2\2\7h\3\2\2"+
		"\2\tn\3\2\2\2\13r\3\2\2\2\ry\3\2\2\2\17~\3\2\2\2\21\u0085\3\2\2\2\23\u008b"+
		"\3\2\2\2\25\u0090\3\2\2\2\27\u0095\3\2\2\2\31\u009b\3\2\2\2\33\u009d\3"+
		"\2\2\2\35\u00a8\3\2\2\2\37\u00b7\3\2\2\2!\u00bd\3\2\2\2#\u00c2\3\2\2\2"+
		"%\u00c8\3\2\2\2\'\u00ce\3\2\2\2)\u00d6\3\2\2\2+\u00d8\3\2\2\2-\u00da\3"+
		"\2\2\2/\u00dc\3\2\2\2\61\u00e2\3\2\2\2\63\u00e4\3\2\2\2\65\u00e6\3\2\2"+
		"\2\67\u00fb\3\2\2\29\u00fe\3\2\2\2;\u0108\3\2\2\2=\u0112\3\2\2\2?\u011f"+
		"\3\2\2\2A\u0121\3\2\2\2C\u0123\3\2\2\2E\u0125\3\2\2\2G\u0127\3\2\2\2I"+
		"\u0129\3\2\2\2K\u012b\3\2\2\2M\u012d\3\2\2\2O\u012f\3\2\2\2QR\7o\2\2R"+
		"S\7c\2\2ST\7k\2\2TU\7p\2\2U\4\3\2\2\2VW\7f\2\2WX\7t\2\2XY\7c\2\2YZ\7y"+
		"\2\2Z[\7T\2\2[\\\7c\2\2\\]\7p\2\2]^\7f\2\2^_\7q\2\2_`\7o\2\2`a\7G\2\2"+
		"ab\7n\2\2bc\7n\2\2cd\7k\2\2de\7r\2\2ef\7u\2\2fg\7g\2\2g\6\3\2\2\2hi\7"+
		"r\2\2ij\7t\2\2jk\7k\2\2kl\7p\2\2lm\7v\2\2m\b\3\2\2\2no\7k\2\2op\7p\2\2"+
		"pq\7v\2\2q\n\3\2\2\2rs\7f\2\2st\7q\2\2tu\7w\2\2uv\7d\2\2vw\7n\2\2wx\7"+
		"g\2\2x\f\3\2\2\2yz\7d\2\2z{\7q\2\2{|\7q\2\2|}\7n\2\2}\16\3\2\2\2~\177"+
		"\7u\2\2\177\u0080\7v\2\2\u0080\u0081\7t\2\2\u0081\u0082\7k\2\2\u0082\u0083"+
		"\7p\2\2\u0083\u0084\7i\2\2\u0084\20\3\2\2\2\u0085\u0086\7c\2\2\u0086\u0087"+
		"\7t\2\2\u0087\u0088\7t\2\2\u0088\u0089\7c\2\2\u0089\u008a\7{\2\2\u008a"+
		"\22\3\2\2\2\u008b\u008c\7n\2\2\u008c\u008d\7k\2\2\u008d\u008e\7u\2\2\u008e"+
		"\u008f\7v\2\2\u008f\24\3\2\2\2\u0090\u0091\7v\2\2\u0091\u0092\7t\2\2\u0092"+
		"\u0093\7g\2\2\u0093\u0094\7g\2\2\u0094\26\3\2\2\2\u0095\u0096\7i\2\2\u0096"+
		"\u0097\7t\2\2\u0097\u0098\7c\2\2\u0098\u0099\7r\2\2\u0099\u009a\7j\2\2"+
		"\u009a\30\3\2\2\2\u009b\u009c\7\60\2\2\u009c\32\3\2\2\2\u009d\u009e\7"+
		"\61\2\2\u009e\u009f\7\61\2\2\u009f\u00a3\3\2\2\2\u00a0\u00a2\n\2\2\2\u00a1"+
		"\u00a0\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2"+
		"\2\2\u00a4\u00a6\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7\b\16\2\2\u00a7"+
		"\34\3\2\2\2\u00a8\u00a9\7\61\2\2\u00a9\u00aa\7,\2\2\u00aa\u00ae\3\2\2"+
		"\2\u00ab\u00ad\13\2\2\2\u00ac\u00ab\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b1\3\2\2\2\u00b0\u00ae\3\2"+
		"\2\2\u00b1\u00b2\7,\2\2\u00b2\u00b3\7\61\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b5\b\17\2\2\u00b5\36\3\2\2\2\u00b6\u00b8\t\3\2\2\u00b7\u00b6\3\2\2"+
		"\2\u00b8\u00b9\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb"+
		"\3\2\2\2\u00bb\u00bc\b\20\2\2\u00bc \3\2\2\2\u00bd\u00be\7?\2\2\u00be"+
		"\"\3\2\2\2\u00bf\u00c3\7~\2\2\u00c0\u00c1\7q\2\2\u00c1\u00c3\7t\2\2\u00c2"+
		"\u00bf\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c3$\3\2\2\2\u00c4\u00c9\7(\2\2\u00c5"+
		"\u00c6\7c\2\2\u00c6\u00c7\7p\2\2\u00c7\u00c9\7f\2\2\u00c8\u00c4\3\2\2"+
		"\2\u00c8\u00c5\3\2\2\2\u00c9&\3\2\2\2\u00ca\u00cb\7?\2\2\u00cb\u00cf\7"+
		"?\2\2\u00cc\u00cd\7>\2\2\u00cd\u00cf\7@\2\2\u00ce\u00ca\3\2\2\2\u00ce"+
		"\u00cc\3\2\2\2\u00cf(\3\2\2\2\u00d0\u00d7\7>\2\2\u00d1\u00d2\7>\2\2\u00d2"+
		"\u00d7\7?\2\2\u00d3\u00d7\7@\2\2\u00d4\u00d5\7@\2\2\u00d5\u00d7\7?\2\2"+
		"\u00d6\u00d0\3\2\2\2\u00d6\u00d1\3\2\2\2\u00d6\u00d3\3\2\2\2\u00d6\u00d4"+
		"\3\2\2\2\u00d7*\3\2\2\2\u00d8\u00d9\t\4\2\2\u00d9,\3\2\2\2\u00da\u00db"+
		"\t\5\2\2\u00db.\3\2\2\2\u00dc\u00dd\7`\2\2\u00dd\60\3\2\2\2\u00de\u00e3"+
		"\7\u0080\2\2\u00df\u00e0\7p\2\2\u00e0\u00e1\7q\2\2\u00e1\u00e3\7v\2\2"+
		"\u00e2\u00de\3\2\2\2\u00e2\u00df\3\2\2\2\u00e3\62\3\2\2\2\u00e4\u00e5"+
		"\7=\2\2\u00e5\64\3\2\2\2\u00e6\u00e7\7.\2\2\u00e7\66\3\2\2\2\u00e8\u00e9"+
		"\7k\2\2\u00e9\u00ea\7p\2\2\u00ea\u00fc\7v\2\2\u00eb\u00ec\7f\2\2\u00ec"+
		"\u00ed\7q\2\2\u00ed\u00ee\7w\2\2\u00ee\u00ef\7d\2\2\u00ef\u00f0\7n\2\2"+
		"\u00f0\u00fc\7g\2\2\u00f1\u00f2\7u\2\2\u00f2\u00f3\7v\2\2\u00f3\u00f4"+
		"\7t\2\2\u00f4\u00f5\7k\2\2\u00f5\u00f6\7p\2\2\u00f6\u00fc\7i\2\2\u00f7"+
		"\u00f8\7d\2\2\u00f8\u00f9\7q\2\2\u00f9\u00fa\7q\2\2\u00fa\u00fc\7n\2\2"+
		"\u00fb\u00e8\3\2\2\2\u00fb\u00eb\3\2\2\2\u00fb\u00f1\3\2\2\2\u00fb\u00f7"+
		"\3\2\2\2\u00fc8\3\2\2\2\u00fd\u00ff\t\6\2\2\u00fe\u00fd\3\2\2\2\u00ff"+
		"\u0100\3\2\2\2\u0100\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0102\3\2"+
		"\2\2\u0102\u0104\t\7\2\2\u0103\u0105\t\6\2\2\u0104\u0103\3\2\2\2\u0105"+
		"\u0106\3\2\2\2\u0106\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107:\3\2\2\2"+
		"\u0108\u010c\5A!\2\u0109\u010b\n\b\2\2\u010a\u0109\3\2\2\2\u010b\u010e"+
		"\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010f\3\2\2\2\u010e"+
		"\u010c\3\2\2\2\u010f\u0110\5A!\2\u0110<\3\2\2\2\u0111\u0113\t\6\2\2\u0112"+
		"\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2"+
		"\2\2\u0115>\3\2\2\2\u0116\u0117\7v\2\2\u0117\u0118\7t\2\2\u0118\u0119"+
		"\7w\2\2\u0119\u0120\7g\2\2\u011a\u011b\7h\2\2\u011b\u011c\7c\2\2\u011c"+
		"\u011d\7n\2\2\u011d\u011e\7u\2\2\u011e\u0120\7g\2\2\u011f\u0116\3\2\2"+
		"\2\u011f\u011a\3\2\2\2\u0120@\3\2\2\2\u0121\u0122\t\t\2\2\u0122B\3\2\2"+
		"\2\u0123\u0124\7*\2\2\u0124D\3\2\2\2\u0125\u0126\7+\2\2\u0126F\3\2\2\2"+
		"\u0127\u0128\7}\2\2\u0128H\3\2\2\2\u0129\u012a\7\177\2\2\u012aJ\3\2\2"+
		"\2\u012b\u012c\7]\2\2\u012cL\3\2\2\2\u012d\u012e\7_\2\2\u012eN\3\2\2\2"+
		"\u012f\u0133\t\n\2\2\u0130\u0132\t\13\2\2\u0131\u0130\3\2\2\2\u0132\u0135"+
		"\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134P\3\2\2\2\u0135"+
		"\u0133\3\2\2\2\22\2\u00a3\u00ae\u00b9\u00c2\u00c8\u00ce\u00d6\u00e2\u00fb"+
		"\u0100\u0106\u010c\u0114\u011f\u0133\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}