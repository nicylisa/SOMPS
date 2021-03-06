// Generated from E:/STL/src/main/resources\STL.g4 by ANTLR 4.8
package com.ecnu.stldemo.gen;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class STLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, AND=2, OR=3, IMPLY=4, NOT=5, F=6, G=7, U=8, LT=9, GT=10, LE=11, 
		GE=12, SpaceOrTab=13, Comment=14, Letter=15, LPAREN=16, LINEJUMP=17, RPAREN=18, 
		LB=19, RB=20, COMMA=21, NUMBER=22, NNUMBER=23, NAME=24, Signal=25, SignalPert=26;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "AND", "OR", "IMPLY", "NOT", "F", "G", "U", "LT", "GT", "LE", 
			"GE", "SpaceOrTab", "Comment", "Letter", "LPAREN", "LINEJUMP", "RPAREN", 
			"LB", "RB", "COMMA", "NUMBER", "NNUMBER", "NAME", "Signal", "SignalPert"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'AND'", "'OR'", "'-->'", "'NOT'", "'F_'", "'G_'", "'U_'", 
			"'<'", "'>'", "'<='", "'>='", null, null, null, "'('", "'\n'", "')'", 
			"'['", "']'", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "AND", "OR", "IMPLY", "NOT", "F", "G", "U", "LT", "GT", "LE", 
			"GE", "SpaceOrTab", "Comment", "Letter", "LPAREN", "LINEJUMP", "RPAREN", 
			"LB", "RB", "COMMA", "NUMBER", "NNUMBER", "NAME", "Signal", "SignalPert"
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


	public STLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "STL.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\34\u00af\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3"+
		"\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\16\6\16]\n\16\r\16\16\16^\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\7\17g\n\17\f\17\16\17j\13\17\3\17\3\17\3\20\3\20"+
		"\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\6\27"+
		"}\n\27\r\27\16\27~\3\27\3\27\6\27\u0083\n\27\r\27\16\27\u0084\5\27\u0087"+
		"\n\27\3\30\3\30\6\30\u008b\n\30\r\30\16\30\u008c\3\30\3\30\6\30\u0091"+
		"\n\30\r\30\16\30\u0092\5\30\u0095\n\30\3\31\3\31\3\31\6\31\u009a\n\31"+
		"\r\31\16\31\u009b\3\31\7\31\u009f\n\31\f\31\16\31\u00a2\13\31\3\32\3\32"+
		"\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\2\2\34\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\3\2\5\4\2\13\f\"\"\4\2\f\f"+
		"\17\17\4\2C\\c|\2\u00ba\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\3\67\3"+
		"\2\2\2\59\3\2\2\2\7=\3\2\2\2\t@\3\2\2\2\13D\3\2\2\2\rH\3\2\2\2\17K\3\2"+
		"\2\2\21N\3\2\2\2\23Q\3\2\2\2\25S\3\2\2\2\27U\3\2\2\2\31X\3\2\2\2\33\\"+
		"\3\2\2\2\35b\3\2\2\2\37m\3\2\2\2!o\3\2\2\2#q\3\2\2\2%s\3\2\2\2\'u\3\2"+
		"\2\2)w\3\2\2\2+y\3\2\2\2-|\3\2\2\2/\u0088\3\2\2\2\61\u0096\3\2\2\2\63"+
		"\u00a3\3\2\2\2\65\u00a8\3\2\2\2\678\7?\2\28\4\3\2\2\29:\7C\2\2:;\7P\2"+
		"\2;<\7F\2\2<\6\3\2\2\2=>\7Q\2\2>?\7T\2\2?\b\3\2\2\2@A\7/\2\2AB\7/\2\2"+
		"BC\7@\2\2C\n\3\2\2\2DE\7P\2\2EF\7Q\2\2FG\7V\2\2G\f\3\2\2\2HI\7H\2\2IJ"+
		"\7a\2\2J\16\3\2\2\2KL\7I\2\2LM\7a\2\2M\20\3\2\2\2NO\7W\2\2OP\7a\2\2P\22"+
		"\3\2\2\2QR\7>\2\2R\24\3\2\2\2ST\7@\2\2T\26\3\2\2\2UV\7>\2\2VW\7?\2\2W"+
		"\30\3\2\2\2XY\7@\2\2YZ\7?\2\2Z\32\3\2\2\2[]\t\2\2\2\\[\3\2\2\2]^\3\2\2"+
		"\2^\\\3\2\2\2^_\3\2\2\2_`\3\2\2\2`a\b\16\2\2a\34\3\2\2\2bc\7/\2\2cd\7"+
		"/\2\2dh\3\2\2\2eg\n\3\2\2fe\3\2\2\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2ik\3"+
		"\2\2\2jh\3\2\2\2kl\b\17\2\2l\36\3\2\2\2mn\t\4\2\2n \3\2\2\2op\7*\2\2p"+
		"\"\3\2\2\2qr\7\f\2\2r$\3\2\2\2st\7+\2\2t&\3\2\2\2uv\7]\2\2v(\3\2\2\2w"+
		"x\7_\2\2x*\3\2\2\2yz\7.\2\2z,\3\2\2\2{}\4\62;\2|{\3\2\2\2}~\3\2\2\2~|"+
		"\3\2\2\2~\177\3\2\2\2\177\u0086\3\2\2\2\u0080\u0082\7\60\2\2\u0081\u0083"+
		"\4\62;\2\u0082\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0082\3\2\2\2\u0084"+
		"\u0085\3\2\2\2\u0085\u0087\3\2\2\2\u0086\u0080\3\2\2\2\u0086\u0087\3\2"+
		"\2\2\u0087.\3\2\2\2\u0088\u008a\7/\2\2\u0089\u008b\4\62;\2\u008a\u0089"+
		"\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d"+
		"\u0094\3\2\2\2\u008e\u0090\7\60\2\2\u008f\u0091\4\62;\2\u0090\u008f\3"+
		"\2\2\2\u0091\u0092\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093"+
		"\u0095\3\2\2\2\u0094\u008e\3\2\2\2\u0094\u0095\3\2\2\2\u0095\60\3\2\2"+
		"\2\u0096\u00a0\5\37\20\2\u0097\u009f\5\37\20\2\u0098\u009a\4\62;\2\u0099"+
		"\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2"+
		"\2\2\u009c\u009f\3\2\2\2\u009d\u009f\7a\2\2\u009e\u0097\3\2\2\2\u009e"+
		"\u0099\3\2\2\2\u009e\u009d\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2"+
		"\2\2\u00a0\u00a1\3\2\2\2\u00a1\62\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4"+
		"\5\61\31\2\u00a4\u00a5\7*\2\2\u00a5\u00a6\7v\2\2\u00a6\u00a7\7+\2\2\u00a7"+
		"\64\3\2\2\2\u00a8\u00a9\5\63\32\2\u00a9\u00aa\7a\2\2\u00aa\u00ab\7r\2"+
		"\2\u00ab\u00ac\7g\2\2\u00ac\u00ad\7t\2\2\u00ad\u00ae\7v\2\2\u00ae\66\3"+
		"\2\2\2\16\2^h~\u0084\u0086\u008c\u0092\u0094\u009b\u009e\u00a0\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}