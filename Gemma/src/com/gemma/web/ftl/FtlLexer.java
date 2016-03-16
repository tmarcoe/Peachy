// Generated from Ftl.g4 by ANTLR 4.5.2

package com.gemma.web.ftl;
import com.gemma.web.ftl_helper.Transaction;
import com.gemma.web.ftl_helper.Variable;
import com.gemma.web.ftl_helper.ObjectMath;
import java.util.List;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FtlLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, Bind=6, Credit=7, Debit=8, Ledger=9, 
		GetAmount=10, GetTax=11, GetDescription=12, BOOLEAN=13, CHAR=14, CONTINUE=15, 
		DO=16, DOUBLE=17, ELSE=18, ENUM=19, FLOAT=20, STRING=21, FOR=22, IF=23, 
		INT=24, LONG=25, WHILE=26, IntegerLiteral=27, FloatOrDoubleLiteral=28, 
		FloatingPointLiteral=29, BooleanLiteral=30, CharacterLiteral=31, StringLiteral=32, 
		NullLiteral=33, LPAREN=34, RPAREN=35, LBRACE=36, RBRACE=37, LBRACK=38, 
		RBRACK=39, SEMI=40, COMMA=41, DOT=42, ASSIGN=43, GT=44, LT=45, BANG=46, 
		TILDE=47, QUESTION=48, COLON=49, EQUAL=50, LE=51, GE=52, NOTEQUAL=53, 
		AND=54, OR=55, INC=56, DEC=57, ADD=58, SUB=59, MUL=60, DIV=61, BITAND=62, 
		BITOR=63, CARET=64, MOD=65, ADD_ASSIGN=66, SUB_ASSIGN=67, MUL_ASSIGN=68, 
		DIV_ASSIGN=69, AND_ASSIGN=70, OR_ASSIGN=71, XOR_ASSIGN=72, MOD_ASSIGN=73, 
		LSHIFT_ASSIGN=74, RSHIFT_ASSIGN=75, URSHIFT_ASSIGN=76, Identifier=77, 
		AT=78, WS=79, COMMENT=80, LINE_COMMENT=81;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "Bind", "Credit", "Debit", "Ledger", 
		"GetAmount", "GetTax", "GetDescription", "BOOLEAN", "CHAR", "CONTINUE", 
		"DO", "DOUBLE", "ELSE", "ENUM", "FLOAT", "STRING", "FOR", "IF", "INT", 
		"LONG", "WHILE", "IntegerLiteral", "FloatOrDoubleLiteral", "DecimalIntegerLiteral", 
		"HexIntegerLiteral", "OctalIntegerLiteral", "BinaryIntegerLiteral", "IntegerTypeSuffix", 
		"DecimalNumeral", "Digits", "Digit", "NonZeroDigit", "DigitOrUnderscore", 
		"Underscores", "HexNumeral", "HexDigits", "HexDigit", "HexDigitOrUnderscore", 
		"OctalNumeral", "OctalDigits", "OctalDigit", "OctalDigitOrUnderscore", 
		"BinaryNumeral", "BinaryDigits", "BinaryDigit", "BinaryDigitOrUnderscore", 
		"FloatingPointLiteral", "DecimalFloatingPointLiteral", "ExponentPart", 
		"ExponentIndicator", "SignedInteger", "Sign", "FloatTypeSuffix", "HexadecimalFloatingPointLiteral", 
		"HexSignificand", "BinaryExponent", "BinaryExponentIndicator", "BooleanLiteral", 
		"CharacterLiteral", "SingleCharacter", "StringLiteral", "StringCharacters", 
		"StringCharacter", "EscapeSequence", "OctalEscape", "UnicodeEscape", "ZeroToThree", 
		"NullLiteral", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", 
		"SEMI", "COMMA", "DOT", "ASSIGN", "GT", "LT", "BANG", "TILDE", "QUESTION", 
		"COLON", "EQUAL", "LE", "GE", "NOTEQUAL", "AND", "OR", "INC", "DEC", "ADD", 
		"SUB", "MUL", "DIV", "BITAND", "BITOR", "CARET", "MOD", "ADD_ASSIGN", 
		"SUB_ASSIGN", "MUL_ASSIGN", "DIV_ASSIGN", "AND_ASSIGN", "OR_ASSIGN", "XOR_ASSIGN", 
		"MOD_ASSIGN", "LSHIFT_ASSIGN", "RSHIFT_ASSIGN", "URSHIFT_ASSIGN", "Identifier", 
		"JavaLetter", "JavaLetterOrDigit", "AT", "WS", "COMMENT", "LINE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'begin'", "'end'", "'**'", "'C'", "'D'", "'bind'", "'credit'", 
		"'debit'", "'ledger'", "'getAmount'", "'getTax'", "'getDescription'", 
		"'boolean'", "'char'", "'continue'", "'do'", "'double'", "'else'", "'enum'", 
		"'float'", "'string'", "'for'", "'if'", "'int'", "'long'", "'while'", 
		null, null, null, null, null, null, "'null'", "'('", "')'", "'{'", "'}'", 
		"'['", "']'", "';'", "','", "'.'", "'='", "'>'", "'<'", "'!'", "'~'", 
		"'?'", "':'", "'=='", "'<='", "'>='", "'!='", "'&&'", "'||'", "'++'", 
		"'--'", "'+'", "'-'", "'*'", "'/'", "'&'", "'|'", "'^'", "'%'", "'+='", 
		"'-='", "'*='", "'/='", "'&='", "'|='", "'^='", "'%='", "'<<='", "'>>='", 
		"'>>>='", null, "'@'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "Bind", "Credit", "Debit", "Ledger", 
		"GetAmount", "GetTax", "GetDescription", "BOOLEAN", "CHAR", "CONTINUE", 
		"DO", "DOUBLE", "ELSE", "ENUM", "FLOAT", "STRING", "FOR", "IF", "INT", 
		"LONG", "WHILE", "IntegerLiteral", "FloatOrDoubleLiteral", "FloatingPointLiteral", 
		"BooleanLiteral", "CharacterLiteral", "StringLiteral", "NullLiteral", 
		"LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", 
		"DOT", "ASSIGN", "GT", "LT", "BANG", "TILDE", "QUESTION", "COLON", "EQUAL", 
		"LE", "GE", "NOTEQUAL", "AND", "OR", "INC", "DEC", "ADD", "SUB", "MUL", 
		"DIV", "BITAND", "BITOR", "CARET", "MOD", "ADD_ASSIGN", "SUB_ASSIGN", 
		"MUL_ASSIGN", "DIV_ASSIGN", "AND_ASSIGN", "OR_ASSIGN", "XOR_ASSIGN", "MOD_ASSIGN", 
		"LSHIFT_ASSIGN", "RSHIFT_ASSIGN", "URSHIFT_ASSIGN", "Identifier", "AT", 
		"WS", "COMMENT", "LINE_COMMENT"
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


		public Transaction trans;
		public ObjectMath om = new ObjectMath();


	public FtlLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Ftl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 117:
			return JavaLetter_sempred((RuleContext)_localctx, predIndex);
		case 118:
			return JavaLetterOrDigit_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean JavaLetter_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return Character.isJavaIdentifierStart(_input.LA(-1));
		case 1:
			return Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)));
		}
		return true;
	}
	private boolean JavaLetterOrDigit_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return Character.isJavaIdentifierPart(_input.LA(-1));
		case 3:
			return Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)));
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2S\u034a\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t"+
		"k\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\4s\ts\4t\tt\4u\tu\4v\tv\4"+
		"w\tw\4x\tx\4y\ty\4z\tz\4{\t{\4|\t|\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3"+
		"\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\5\34\u0195\n\34\3\35"+
		"\6\35\u0198\n\35\r\35\16\35\u0199\3\35\3\35\7\35\u019e\n\35\f\35\16\35"+
		"\u01a1\13\35\3\36\3\36\5\36\u01a5\n\36\3\37\3\37\5\37\u01a9\n\37\3 \3"+
		" \5 \u01ad\n \3!\3!\5!\u01b1\n!\3\"\3\"\3#\3#\3#\5#\u01b8\n#\3#\3#\3#"+
		"\5#\u01bd\n#\5#\u01bf\n#\3$\3$\7$\u01c3\n$\f$\16$\u01c6\13$\3$\5$\u01c9"+
		"\n$\3%\3%\5%\u01cd\n%\3&\3&\3\'\3\'\5\'\u01d3\n\'\3(\6(\u01d6\n(\r(\16"+
		"(\u01d7\3)\3)\3)\3)\3*\3*\7*\u01e0\n*\f*\16*\u01e3\13*\3*\5*\u01e6\n*"+
		"\3+\3+\3,\3,\5,\u01ec\n,\3-\3-\5-\u01f0\n-\3-\3-\3.\3.\7.\u01f6\n.\f."+
		"\16.\u01f9\13.\3.\5.\u01fc\n.\3/\3/\3\60\3\60\5\60\u0202\n\60\3\61\3\61"+
		"\3\61\3\61\3\62\3\62\7\62\u020a\n\62\f\62\16\62\u020d\13\62\3\62\5\62"+
		"\u0210\n\62\3\63\3\63\3\64\3\64\5\64\u0216\n\64\3\65\3\65\5\65\u021a\n"+
		"\65\3\66\3\66\3\66\5\66\u021f\n\66\3\66\5\66\u0222\n\66\3\66\5\66\u0225"+
		"\n\66\3\66\3\66\3\66\5\66\u022a\n\66\3\66\5\66\u022d\n\66\3\66\3\66\3"+
		"\66\5\66\u0232\n\66\3\66\3\66\3\66\5\66\u0237\n\66\3\67\3\67\3\67\38\3"+
		"8\39\59\u023f\n9\39\39\3:\3:\3;\3;\3<\3<\3<\5<\u024a\n<\3=\3=\5=\u024e"+
		"\n=\3=\3=\3=\5=\u0253\n=\3=\3=\5=\u0257\n=\3>\3>\3>\3?\3?\3@\3@\3@\3@"+
		"\3@\3@\3@\3@\3@\5@\u0267\n@\3A\3A\3A\3A\3A\3A\3A\3A\5A\u0271\nA\3B\3B"+
		"\3C\3C\5C\u0277\nC\3C\3C\3D\6D\u027c\nD\rD\16D\u027d\3E\3E\5E\u0282\n"+
		"E\3F\3F\3F\3F\5F\u0288\nF\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\3G\5G\u0295\n"+
		"G\3H\3H\3H\3H\3H\3H\3H\3I\3I\3J\3J\3J\3J\3J\3K\3K\3L\3L\3M\3M\3N\3N\3"+
		"O\3O\3P\3P\3Q\3Q\3R\3R\3S\3S\3T\3T\3U\3U\3V\3V\3W\3W\3X\3X\3Y\3Y\3Z\3"+
		"Z\3[\3[\3[\3\\\3\\\3\\\3]\3]\3]\3^\3^\3^\3_\3_\3_\3`\3`\3`\3a\3a\3a\3"+
		"b\3b\3b\3c\3c\3d\3d\3e\3e\3f\3f\3g\3g\3h\3h\3i\3i\3j\3j\3k\3k\3k\3l\3"+
		"l\3l\3m\3m\3m\3n\3n\3n\3o\3o\3o\3p\3p\3p\3q\3q\3q\3r\3r\3r\3s\3s\3s\3"+
		"s\3t\3t\3t\3t\3u\3u\3u\3u\3u\3v\3v\7v\u0314\nv\fv\16v\u0317\13v\3w\3w"+
		"\3w\3w\3w\3w\5w\u031f\nw\3x\3x\3x\3x\3x\3x\5x\u0327\nx\3y\3y\3z\6z\u032c"+
		"\nz\rz\16z\u032d\3z\3z\3{\3{\3{\3{\7{\u0336\n{\f{\16{\u0339\13{\3{\3{"+
		"\3{\3{\3{\3|\3|\3|\3|\7|\u0344\n|\f|\16|\u0347\13|\3|\3|\3\u0337\2}\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37"+
		"\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\2=\2"+
		"?\2A\2C\2E\2G\2I\2K\2M\2O\2Q\2S\2U\2W\2Y\2[\2]\2_\2a\2c\2e\2g\2i\37k\2"+
		"m\2o\2q\2s\2u\2w\2y\2{\2}\2\177 \u0081!\u0083\2\u0085\"\u0087\2\u0089"+
		"\2\u008b\2\u008d\2\u008f\2\u0091\2\u0093#\u0095$\u0097%\u0099&\u009b\'"+
		"\u009d(\u009f)\u00a1*\u00a3+\u00a5,\u00a7-\u00a9.\u00ab/\u00ad\60\u00af"+
		"\61\u00b1\62\u00b3\63\u00b5\64\u00b7\65\u00b9\66\u00bb\67\u00bd8\u00bf"+
		"9\u00c1:\u00c3;\u00c5<\u00c7=\u00c9>\u00cb?\u00cd@\u00cfA\u00d1B\u00d3"+
		"C\u00d5D\u00d7E\u00d9F\u00dbG\u00ddH\u00dfI\u00e1J\u00e3K\u00e5L\u00e7"+
		"M\u00e9N\u00ebO\u00ed\2\u00ef\2\u00f1P\u00f3Q\u00f5R\u00f7S\3\2\30\4\2"+
		"NNnn\3\2\63;\4\2ZZzz\5\2\62;CHch\3\2\629\4\2DDdd\3\2\62\63\4\2GGgg\4\2"+
		"--//\6\2FFHHffhh\4\2RRrr\4\2))^^\4\2$$^^\n\2$$))^^ddhhppttvv\3\2\62\65"+
		"\6\2&&C\\aac|\4\2\2\u0081\ud802\udc01\3\2\ud802\udc01\3\2\udc02\ue001"+
		"\7\2&&\62;C\\aac|\5\2\13\f\16\17\"\"\4\2\f\f\17\17\u035a\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2"+
		"\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2i\3\2\2\2\2\177\3"+
		"\2\2\2\2\u0081\3\2\2\2\2\u0085\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2"+
		"\2\u0097\3\2\2\2\2\u0099\3\2\2\2\2\u009b\3\2\2\2\2\u009d\3\2\2\2\2\u009f"+
		"\3\2\2\2\2\u00a1\3\2\2\2\2\u00a3\3\2\2\2\2\u00a5\3\2\2\2\2\u00a7\3\2\2"+
		"\2\2\u00a9\3\2\2\2\2\u00ab\3\2\2\2\2\u00ad\3\2\2\2\2\u00af\3\2\2\2\2\u00b1"+
		"\3\2\2\2\2\u00b3\3\2\2\2\2\u00b5\3\2\2\2\2\u00b7\3\2\2\2\2\u00b9\3\2\2"+
		"\2\2\u00bb\3\2\2\2\2\u00bd\3\2\2\2\2\u00bf\3\2\2\2\2\u00c1\3\2\2\2\2\u00c3"+
		"\3\2\2\2\2\u00c5\3\2\2\2\2\u00c7\3\2\2\2\2\u00c9\3\2\2\2\2\u00cb\3\2\2"+
		"\2\2\u00cd\3\2\2\2\2\u00cf\3\2\2\2\2\u00d1\3\2\2\2\2\u00d3\3\2\2\2\2\u00d5"+
		"\3\2\2\2\2\u00d7\3\2\2\2\2\u00d9\3\2\2\2\2\u00db\3\2\2\2\2\u00dd\3\2\2"+
		"\2\2\u00df\3\2\2\2\2\u00e1\3\2\2\2\2\u00e3\3\2\2\2\2\u00e5\3\2\2\2\2\u00e7"+
		"\3\2\2\2\2\u00e9\3\2\2\2\2\u00eb\3\2\2\2\2\u00f1\3\2\2\2\2\u00f3\3\2\2"+
		"\2\2\u00f5\3\2\2\2\2\u00f7\3\2\2\2\3\u00f9\3\2\2\2\5\u00ff\3\2\2\2\7\u0103"+
		"\3\2\2\2\t\u0106\3\2\2\2\13\u0108\3\2\2\2\r\u010a\3\2\2\2\17\u010f\3\2"+
		"\2\2\21\u0116\3\2\2\2\23\u011c\3\2\2\2\25\u0123\3\2\2\2\27\u012d\3\2\2"+
		"\2\31\u0134\3\2\2\2\33\u0143\3\2\2\2\35\u014b\3\2\2\2\37\u0150\3\2\2\2"+
		"!\u0159\3\2\2\2#\u015c\3\2\2\2%\u0163\3\2\2\2\'\u0168\3\2\2\2)\u016d\3"+
		"\2\2\2+\u0173\3\2\2\2-\u017a\3\2\2\2/\u017e\3\2\2\2\61\u0181\3\2\2\2\63"+
		"\u0185\3\2\2\2\65\u018a\3\2\2\2\67\u0194\3\2\2\29\u0197\3\2\2\2;\u01a2"+
		"\3\2\2\2=\u01a6\3\2\2\2?\u01aa\3\2\2\2A\u01ae\3\2\2\2C\u01b2\3\2\2\2E"+
		"\u01be\3\2\2\2G\u01c0\3\2\2\2I\u01cc\3\2\2\2K\u01ce\3\2\2\2M\u01d2\3\2"+
		"\2\2O\u01d5\3\2\2\2Q\u01d9\3\2\2\2S\u01dd\3\2\2\2U\u01e7\3\2\2\2W\u01eb"+
		"\3\2\2\2Y\u01ed\3\2\2\2[\u01f3\3\2\2\2]\u01fd\3\2\2\2_\u0201\3\2\2\2a"+
		"\u0203\3\2\2\2c\u0207\3\2\2\2e\u0211\3\2\2\2g\u0215\3\2\2\2i\u0219\3\2"+
		"\2\2k\u0236\3\2\2\2m\u0238\3\2\2\2o\u023b\3\2\2\2q\u023e\3\2\2\2s\u0242"+
		"\3\2\2\2u\u0244\3\2\2\2w\u0246\3\2\2\2y\u0256\3\2\2\2{\u0258\3\2\2\2}"+
		"\u025b\3\2\2\2\177\u0266\3\2\2\2\u0081\u0270\3\2\2\2\u0083\u0272\3\2\2"+
		"\2\u0085\u0274\3\2\2\2\u0087\u027b\3\2\2\2\u0089\u0281\3\2\2\2\u008b\u0287"+
		"\3\2\2\2\u008d\u0294\3\2\2\2\u008f\u0296\3\2\2\2\u0091\u029d\3\2\2\2\u0093"+
		"\u029f\3\2\2\2\u0095\u02a4\3\2\2\2\u0097\u02a6\3\2\2\2\u0099\u02a8\3\2"+
		"\2\2\u009b\u02aa\3\2\2\2\u009d\u02ac\3\2\2\2\u009f\u02ae\3\2\2\2\u00a1"+
		"\u02b0\3\2\2\2\u00a3\u02b2\3\2\2\2\u00a5\u02b4\3\2\2\2\u00a7\u02b6\3\2"+
		"\2\2\u00a9\u02b8\3\2\2\2\u00ab\u02ba\3\2\2\2\u00ad\u02bc\3\2\2\2\u00af"+
		"\u02be\3\2\2\2\u00b1\u02c0\3\2\2\2\u00b3\u02c2\3\2\2\2\u00b5\u02c4\3\2"+
		"\2\2\u00b7\u02c7\3\2\2\2\u00b9\u02ca\3\2\2\2\u00bb\u02cd\3\2\2\2\u00bd"+
		"\u02d0\3\2\2\2\u00bf\u02d3\3\2\2\2\u00c1\u02d6\3\2\2\2\u00c3\u02d9\3\2"+
		"\2\2\u00c5\u02dc\3\2\2\2\u00c7\u02de\3\2\2\2\u00c9\u02e0\3\2\2\2\u00cb"+
		"\u02e2\3\2\2\2\u00cd\u02e4\3\2\2\2\u00cf\u02e6\3\2\2\2\u00d1\u02e8\3\2"+
		"\2\2\u00d3\u02ea\3\2\2\2\u00d5\u02ec\3\2\2\2\u00d7\u02ef\3\2\2\2\u00d9"+
		"\u02f2\3\2\2\2\u00db\u02f5\3\2\2\2\u00dd\u02f8\3\2\2\2\u00df\u02fb\3\2"+
		"\2\2\u00e1\u02fe\3\2\2\2\u00e3\u0301\3\2\2\2\u00e5\u0304\3\2\2\2\u00e7"+
		"\u0308\3\2\2\2\u00e9\u030c\3\2\2\2\u00eb\u0311\3\2\2\2\u00ed\u031e\3\2"+
		"\2\2\u00ef\u0326\3\2\2\2\u00f1\u0328\3\2\2\2\u00f3\u032b\3\2\2\2\u00f5"+
		"\u0331\3\2\2\2\u00f7\u033f\3\2\2\2\u00f9\u00fa\7d\2\2\u00fa\u00fb\7g\2"+
		"\2\u00fb\u00fc\7i\2\2\u00fc\u00fd\7k\2\2\u00fd\u00fe\7p\2\2\u00fe\4\3"+
		"\2\2\2\u00ff\u0100\7g\2\2\u0100\u0101\7p\2\2\u0101\u0102\7f\2\2\u0102"+
		"\6\3\2\2\2\u0103\u0104\7,\2\2\u0104\u0105\7,\2\2\u0105\b\3\2\2\2\u0106"+
		"\u0107\7E\2\2\u0107\n\3\2\2\2\u0108\u0109\7F\2\2\u0109\f\3\2\2\2\u010a"+
		"\u010b\7d\2\2\u010b\u010c\7k\2\2\u010c\u010d\7p\2\2\u010d\u010e\7f\2\2"+
		"\u010e\16\3\2\2\2\u010f\u0110\7e\2\2\u0110\u0111\7t\2\2\u0111\u0112\7"+
		"g\2\2\u0112\u0113\7f\2\2\u0113\u0114\7k\2\2\u0114\u0115\7v\2\2\u0115\20"+
		"\3\2\2\2\u0116\u0117\7f\2\2\u0117\u0118\7g\2\2\u0118\u0119\7d\2\2\u0119"+
		"\u011a\7k\2\2\u011a\u011b\7v\2\2\u011b\22\3\2\2\2\u011c\u011d\7n\2\2\u011d"+
		"\u011e\7g\2\2\u011e\u011f\7f\2\2\u011f\u0120\7i\2\2\u0120\u0121\7g\2\2"+
		"\u0121\u0122\7t\2\2\u0122\24\3\2\2\2\u0123\u0124\7i\2\2\u0124\u0125\7"+
		"g\2\2\u0125\u0126\7v\2\2\u0126\u0127\7C\2\2\u0127\u0128\7o\2\2\u0128\u0129"+
		"\7q\2\2\u0129\u012a\7w\2\2\u012a\u012b\7p\2\2\u012b\u012c\7v\2\2\u012c"+
		"\26\3\2\2\2\u012d\u012e\7i\2\2\u012e\u012f\7g\2\2\u012f\u0130\7v\2\2\u0130"+
		"\u0131\7V\2\2\u0131\u0132\7c\2\2\u0132\u0133\7z\2\2\u0133\30\3\2\2\2\u0134"+
		"\u0135\7i\2\2\u0135\u0136\7g\2\2\u0136\u0137\7v\2\2\u0137\u0138\7F\2\2"+
		"\u0138\u0139\7g\2\2\u0139\u013a\7u\2\2\u013a\u013b\7e\2\2\u013b\u013c"+
		"\7t\2\2\u013c\u013d\7k\2\2\u013d\u013e\7r\2\2\u013e\u013f\7v\2\2\u013f"+
		"\u0140\7k\2\2\u0140\u0141\7q\2\2\u0141\u0142\7p\2\2\u0142\32\3\2\2\2\u0143"+
		"\u0144\7d\2\2\u0144\u0145\7q\2\2\u0145\u0146\7q\2\2\u0146\u0147\7n\2\2"+
		"\u0147\u0148\7g\2\2\u0148\u0149\7c\2\2\u0149\u014a\7p\2\2\u014a\34\3\2"+
		"\2\2\u014b\u014c\7e\2\2\u014c\u014d\7j\2\2\u014d\u014e\7c\2\2\u014e\u014f"+
		"\7t\2\2\u014f\36\3\2\2\2\u0150\u0151\7e\2\2\u0151\u0152\7q\2\2\u0152\u0153"+
		"\7p\2\2\u0153\u0154\7v\2\2\u0154\u0155\7k\2\2\u0155\u0156\7p\2\2\u0156"+
		"\u0157\7w\2\2\u0157\u0158\7g\2\2\u0158 \3\2\2\2\u0159\u015a\7f\2\2\u015a"+
		"\u015b\7q\2\2\u015b\"\3\2\2\2\u015c\u015d\7f\2\2\u015d\u015e\7q\2\2\u015e"+
		"\u015f\7w\2\2\u015f\u0160\7d\2\2\u0160\u0161\7n\2\2\u0161\u0162\7g\2\2"+
		"\u0162$\3\2\2\2\u0163\u0164\7g\2\2\u0164\u0165\7n\2\2\u0165\u0166\7u\2"+
		"\2\u0166\u0167\7g\2\2\u0167&\3\2\2\2\u0168\u0169\7g\2\2\u0169\u016a\7"+
		"p\2\2\u016a\u016b\7w\2\2\u016b\u016c\7o\2\2\u016c(\3\2\2\2\u016d\u016e"+
		"\7h\2\2\u016e\u016f\7n\2\2\u016f\u0170\7q\2\2\u0170\u0171\7c\2\2\u0171"+
		"\u0172\7v\2\2\u0172*\3\2\2\2\u0173\u0174\7u\2\2\u0174\u0175\7v\2\2\u0175"+
		"\u0176\7t\2\2\u0176\u0177\7k\2\2\u0177\u0178\7p\2\2\u0178\u0179\7i\2\2"+
		"\u0179,\3\2\2\2\u017a\u017b\7h\2\2\u017b\u017c\7q\2\2\u017c\u017d\7t\2"+
		"\2\u017d.\3\2\2\2\u017e\u017f\7k\2\2\u017f\u0180\7h\2\2\u0180\60\3\2\2"+
		"\2\u0181\u0182\7k\2\2\u0182\u0183\7p\2\2\u0183\u0184\7v\2\2\u0184\62\3"+
		"\2\2\2\u0185\u0186\7n\2\2\u0186\u0187\7q\2\2\u0187\u0188\7p\2\2\u0188"+
		"\u0189\7i\2\2\u0189\64\3\2\2\2\u018a\u018b\7y\2\2\u018b\u018c\7j\2\2\u018c"+
		"\u018d\7k\2\2\u018d\u018e\7n\2\2\u018e\u018f\7g\2\2\u018f\66\3\2\2\2\u0190"+
		"\u0195\5;\36\2\u0191\u0195\5=\37\2\u0192\u0195\5? \2\u0193\u0195\5A!\2"+
		"\u0194\u0190\3\2\2\2\u0194\u0191\3\2\2\2\u0194\u0192\3\2\2\2\u0194\u0193"+
		"\3\2\2\2\u01958\3\2\2\2\u0196\u0198\5I%\2\u0197\u0196\3\2\2\2\u0198\u0199"+
		"\3\2\2\2\u0199\u0197\3\2\2\2\u0199\u019a\3\2\2\2\u019a\u019b\3\2\2\2\u019b"+
		"\u019f\7\60\2\2\u019c\u019e\5I%\2\u019d\u019c\3\2\2\2\u019e\u01a1\3\2"+
		"\2\2\u019f\u019d\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0:\3\2\2\2\u01a1\u019f"+
		"\3\2\2\2\u01a2\u01a4\5E#\2\u01a3\u01a5\5C\"\2\u01a4\u01a3\3\2\2\2\u01a4"+
		"\u01a5\3\2\2\2\u01a5<\3\2\2\2\u01a6\u01a8\5Q)\2\u01a7\u01a9\5C\"\2\u01a8"+
		"\u01a7\3\2\2\2\u01a8\u01a9\3\2\2\2\u01a9>\3\2\2\2\u01aa\u01ac\5Y-\2\u01ab"+
		"\u01ad\5C\"\2\u01ac\u01ab\3\2\2\2\u01ac\u01ad\3\2\2\2\u01ad@\3\2\2\2\u01ae"+
		"\u01b0\5a\61\2\u01af\u01b1\5C\"\2\u01b0\u01af\3\2\2\2\u01b0\u01b1\3\2"+
		"\2\2\u01b1B\3\2\2\2\u01b2\u01b3\t\2\2\2\u01b3D\3\2\2\2\u01b4\u01bf\7\62"+
		"\2\2\u01b5\u01bc\5K&\2\u01b6\u01b8\5G$\2\u01b7\u01b6\3\2\2\2\u01b7\u01b8"+
		"\3\2\2\2\u01b8\u01bd\3\2\2\2\u01b9\u01ba\5O(\2\u01ba\u01bb\5G$\2\u01bb"+
		"\u01bd\3\2\2\2\u01bc\u01b7\3\2\2\2\u01bc\u01b9\3\2\2\2\u01bd\u01bf\3\2"+
		"\2\2\u01be\u01b4\3\2\2\2\u01be\u01b5\3\2\2\2\u01bfF\3\2\2\2\u01c0\u01c8"+
		"\5I%\2\u01c1\u01c3\5M\'\2\u01c2\u01c1\3\2\2\2\u01c3\u01c6\3\2\2\2\u01c4"+
		"\u01c2\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c5\u01c7\3\2\2\2\u01c6\u01c4\3\2"+
		"\2\2\u01c7\u01c9\5I%\2\u01c8\u01c4\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9H"+
		"\3\2\2\2\u01ca\u01cd\7\62\2\2\u01cb\u01cd\5K&\2\u01cc\u01ca\3\2\2\2\u01cc"+
		"\u01cb\3\2\2\2\u01cdJ\3\2\2\2\u01ce\u01cf\t\3\2\2\u01cfL\3\2\2\2\u01d0"+
		"\u01d3\5I%\2\u01d1\u01d3\7a\2\2\u01d2\u01d0\3\2\2\2\u01d2\u01d1\3\2\2"+
		"\2\u01d3N\3\2\2\2\u01d4\u01d6\7a\2\2\u01d5\u01d4\3\2\2\2\u01d6\u01d7\3"+
		"\2\2\2\u01d7\u01d5\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d8P\3\2\2\2\u01d9\u01da"+
		"\7\62\2\2\u01da\u01db\t\4\2\2\u01db\u01dc\5S*\2\u01dcR\3\2\2\2\u01dd\u01e5"+
		"\5U+\2\u01de\u01e0\5W,\2\u01df\u01de\3\2\2\2\u01e0\u01e3\3\2\2\2\u01e1"+
		"\u01df\3\2\2\2\u01e1\u01e2\3\2\2\2\u01e2\u01e4\3\2\2\2\u01e3\u01e1\3\2"+
		"\2\2\u01e4\u01e6\5U+\2\u01e5\u01e1\3\2\2\2\u01e5\u01e6\3\2\2\2\u01e6T"+
		"\3\2\2\2\u01e7\u01e8\t\5\2\2\u01e8V\3\2\2\2\u01e9\u01ec\5U+\2\u01ea\u01ec"+
		"\7a\2\2\u01eb\u01e9\3\2\2\2\u01eb\u01ea\3\2\2\2\u01ecX\3\2\2\2\u01ed\u01ef"+
		"\7\62\2\2\u01ee\u01f0\5O(\2\u01ef\u01ee\3\2\2\2\u01ef\u01f0\3\2\2\2\u01f0"+
		"\u01f1\3\2\2\2\u01f1\u01f2\5[.\2\u01f2Z\3\2\2\2\u01f3\u01fb\5]/\2\u01f4"+
		"\u01f6\5_\60\2\u01f5\u01f4\3\2\2\2\u01f6\u01f9\3\2\2\2\u01f7\u01f5\3\2"+
		"\2\2\u01f7\u01f8\3\2\2\2\u01f8\u01fa\3\2\2\2\u01f9\u01f7\3\2\2\2\u01fa"+
		"\u01fc\5]/\2\u01fb\u01f7\3\2\2\2\u01fb\u01fc\3\2\2\2\u01fc\\\3\2\2\2\u01fd"+
		"\u01fe\t\6\2\2\u01fe^\3\2\2\2\u01ff\u0202\5]/\2\u0200\u0202\7a\2\2\u0201"+
		"\u01ff\3\2\2\2\u0201\u0200\3\2\2\2\u0202`\3\2\2\2\u0203\u0204\7\62\2\2"+
		"\u0204\u0205\t\7\2\2\u0205\u0206\5c\62\2\u0206b\3\2\2\2\u0207\u020f\5"+
		"e\63\2\u0208\u020a\5g\64\2\u0209\u0208\3\2\2\2\u020a\u020d\3\2\2\2\u020b"+
		"\u0209\3\2\2\2\u020b\u020c\3\2\2\2\u020c\u020e\3\2\2\2\u020d\u020b\3\2"+
		"\2\2\u020e\u0210\5e\63\2\u020f\u020b\3\2\2\2\u020f\u0210\3\2\2\2\u0210"+
		"d\3\2\2\2\u0211\u0212\t\b\2\2\u0212f\3\2\2\2\u0213\u0216\5e\63\2\u0214"+
		"\u0216\7a\2\2\u0215\u0213\3\2\2\2\u0215\u0214\3\2\2\2\u0216h\3\2\2\2\u0217"+
		"\u021a\5k\66\2\u0218\u021a\5w<\2\u0219\u0217\3\2\2\2\u0219\u0218\3\2\2"+
		"\2\u021aj\3\2\2\2\u021b\u021c\5G$\2\u021c\u021e\7\60\2\2\u021d\u021f\5"+
		"G$\2\u021e\u021d\3\2\2\2\u021e\u021f\3\2\2\2\u021f\u0221\3\2\2\2\u0220"+
		"\u0222\5m\67\2\u0221\u0220\3\2\2\2\u0221\u0222\3\2\2\2\u0222\u0224\3\2"+
		"\2\2\u0223\u0225\5u;\2\u0224\u0223\3\2\2\2\u0224\u0225\3\2\2\2\u0225\u0237"+
		"\3\2\2\2\u0226\u0227\7\60\2\2\u0227\u0229\5G$\2\u0228\u022a\5m\67\2\u0229"+
		"\u0228\3\2\2\2\u0229\u022a\3\2\2\2\u022a\u022c\3\2\2\2\u022b\u022d\5u"+
		";\2\u022c\u022b\3\2\2\2\u022c\u022d\3\2\2\2\u022d\u0237\3\2\2\2\u022e"+
		"\u022f\5G$\2\u022f\u0231\5m\67\2\u0230\u0232\5u;\2\u0231\u0230\3\2\2\2"+
		"\u0231\u0232\3\2\2\2\u0232\u0237\3\2\2\2\u0233\u0234\5G$\2\u0234\u0235"+
		"\5u;\2\u0235\u0237\3\2\2\2\u0236\u021b\3\2\2\2\u0236\u0226\3\2\2\2\u0236"+
		"\u022e\3\2\2\2\u0236\u0233\3\2\2\2\u0237l\3\2\2\2\u0238\u0239\5o8\2\u0239"+
		"\u023a\5q9\2\u023an\3\2\2\2\u023b\u023c\t\t\2\2\u023cp\3\2\2\2\u023d\u023f"+
		"\5s:\2\u023e\u023d\3\2\2\2\u023e\u023f\3\2\2\2\u023f\u0240\3\2\2\2\u0240"+
		"\u0241\5G$\2\u0241r\3\2\2\2\u0242\u0243\t\n\2\2\u0243t\3\2\2\2\u0244\u0245"+
		"\t\13\2\2\u0245v\3\2\2\2\u0246\u0247\5y=\2\u0247\u0249\5{>\2\u0248\u024a"+
		"\5u;\2\u0249\u0248\3\2\2\2\u0249\u024a\3\2\2\2\u024ax\3\2\2\2\u024b\u024d"+
		"\5Q)\2\u024c\u024e\7\60\2\2\u024d\u024c\3\2\2\2\u024d\u024e\3\2\2\2\u024e"+
		"\u0257\3\2\2\2\u024f\u0250\7\62\2\2\u0250\u0252\t\4\2\2\u0251\u0253\5"+
		"S*\2\u0252\u0251\3\2\2\2\u0252\u0253\3\2\2\2\u0253\u0254\3\2\2\2\u0254"+
		"\u0255\7\60\2\2\u0255\u0257\5S*\2\u0256\u024b\3\2\2\2\u0256\u024f\3\2"+
		"\2\2\u0257z\3\2\2\2\u0258\u0259\5}?\2\u0259\u025a\5q9\2\u025a|\3\2\2\2"+
		"\u025b\u025c\t\f\2\2\u025c~\3\2\2\2\u025d\u025e\7v\2\2\u025e\u025f\7t"+
		"\2\2\u025f\u0260\7w\2\2\u0260\u0267\7g\2\2\u0261\u0262\7h\2\2\u0262\u0263"+
		"\7c\2\2\u0263\u0264\7n\2\2\u0264\u0265\7u\2\2\u0265\u0267\7g\2\2\u0266"+
		"\u025d\3\2\2\2\u0266\u0261\3\2\2\2\u0267\u0080\3\2\2\2\u0268\u0269\7)"+
		"\2\2\u0269\u026a\5\u0083B\2\u026a\u026b\7)\2\2\u026b\u0271\3\2\2\2\u026c"+
		"\u026d\7)\2\2\u026d\u026e\5\u008bF\2\u026e\u026f\7)\2\2\u026f\u0271\3"+
		"\2\2\2\u0270\u0268\3\2\2\2\u0270\u026c\3\2\2\2\u0271\u0082\3\2\2\2\u0272"+
		"\u0273\n\r\2\2\u0273\u0084\3\2\2\2\u0274\u0276\7$\2\2\u0275\u0277\5\u0087"+
		"D\2\u0276\u0275\3\2\2\2\u0276\u0277\3\2\2\2\u0277\u0278\3\2\2\2\u0278"+
		"\u0279\7$\2\2\u0279\u0086\3\2\2\2\u027a\u027c\5\u0089E\2\u027b\u027a\3"+
		"\2\2\2\u027c\u027d\3\2\2\2\u027d\u027b\3\2\2\2\u027d\u027e\3\2\2\2\u027e"+
		"\u0088\3\2\2\2\u027f\u0282\n\16\2\2\u0280\u0282\5\u008bF\2\u0281\u027f"+
		"\3\2\2\2\u0281\u0280\3\2\2\2\u0282\u008a\3\2\2\2\u0283\u0284\7^\2\2\u0284"+
		"\u0288\t\17\2\2\u0285\u0288\5\u008dG\2\u0286\u0288\5\u008fH\2\u0287\u0283"+
		"\3\2\2\2\u0287\u0285\3\2\2\2\u0287\u0286\3\2\2\2\u0288\u008c\3\2\2\2\u0289"+
		"\u028a\7^\2\2\u028a\u0295\5]/\2\u028b\u028c\7^\2\2\u028c\u028d\5]/\2\u028d"+
		"\u028e\5]/\2\u028e\u0295\3\2\2\2\u028f\u0290\7^\2\2\u0290\u0291\5\u0091"+
		"I\2\u0291\u0292\5]/\2\u0292\u0293\5]/\2\u0293\u0295\3\2\2\2\u0294\u0289"+
		"\3\2\2\2\u0294\u028b\3\2\2\2\u0294\u028f\3\2\2\2\u0295\u008e\3\2\2\2\u0296"+
		"\u0297\7^\2\2\u0297\u0298\7w\2\2\u0298\u0299\5U+\2\u0299\u029a\5U+\2\u029a"+
		"\u029b\5U+\2\u029b\u029c\5U+\2\u029c\u0090\3\2\2\2\u029d\u029e\t\20\2"+
		"\2\u029e\u0092\3\2\2\2\u029f\u02a0\7p\2\2\u02a0\u02a1\7w\2\2\u02a1\u02a2"+
		"\7n\2\2\u02a2\u02a3\7n\2\2\u02a3\u0094\3\2\2\2\u02a4\u02a5\7*\2\2\u02a5"+
		"\u0096\3\2\2\2\u02a6\u02a7\7+\2\2\u02a7\u0098\3\2\2\2\u02a8\u02a9\7}\2"+
		"\2\u02a9\u009a\3\2\2\2\u02aa\u02ab\7\177\2\2\u02ab\u009c\3\2\2\2\u02ac"+
		"\u02ad\7]\2\2\u02ad\u009e\3\2\2\2\u02ae\u02af\7_\2\2\u02af\u00a0\3\2\2"+
		"\2\u02b0\u02b1\7=\2\2\u02b1\u00a2\3\2\2\2\u02b2\u02b3\7.\2\2\u02b3\u00a4"+
		"\3\2\2\2\u02b4\u02b5\7\60\2\2\u02b5\u00a6\3\2\2\2\u02b6\u02b7\7?\2\2\u02b7"+
		"\u00a8\3\2\2\2\u02b8\u02b9\7@\2\2\u02b9\u00aa\3\2\2\2\u02ba\u02bb\7>\2"+
		"\2\u02bb\u00ac\3\2\2\2\u02bc\u02bd\7#\2\2\u02bd\u00ae\3\2\2\2\u02be\u02bf"+
		"\7\u0080\2\2\u02bf\u00b0\3\2\2\2\u02c0\u02c1\7A\2\2\u02c1\u00b2\3\2\2"+
		"\2\u02c2\u02c3\7<\2\2\u02c3\u00b4\3\2\2\2\u02c4\u02c5\7?\2\2\u02c5\u02c6"+
		"\7?\2\2\u02c6\u00b6\3\2\2\2\u02c7\u02c8\7>\2\2\u02c8\u02c9\7?\2\2\u02c9"+
		"\u00b8\3\2\2\2\u02ca\u02cb\7@\2\2\u02cb\u02cc\7?\2\2\u02cc\u00ba\3\2\2"+
		"\2\u02cd\u02ce\7#\2\2\u02ce\u02cf\7?\2\2\u02cf\u00bc\3\2\2\2\u02d0\u02d1"+
		"\7(\2\2\u02d1\u02d2\7(\2\2\u02d2\u00be\3\2\2\2\u02d3\u02d4\7~\2\2\u02d4"+
		"\u02d5\7~\2\2\u02d5\u00c0\3\2\2\2\u02d6\u02d7\7-\2\2\u02d7\u02d8\7-\2"+
		"\2\u02d8\u00c2\3\2\2\2\u02d9\u02da\7/\2\2\u02da\u02db\7/\2\2\u02db\u00c4"+
		"\3\2\2\2\u02dc\u02dd\7-\2\2\u02dd\u00c6\3\2\2\2\u02de\u02df\7/\2\2\u02df"+
		"\u00c8\3\2\2\2\u02e0\u02e1\7,\2\2\u02e1\u00ca\3\2\2\2\u02e2\u02e3\7\61"+
		"\2\2\u02e3\u00cc\3\2\2\2\u02e4\u02e5\7(\2\2\u02e5\u00ce\3\2\2\2\u02e6"+
		"\u02e7\7~\2\2\u02e7\u00d0\3\2\2\2\u02e8\u02e9\7`\2\2\u02e9\u00d2\3\2\2"+
		"\2\u02ea\u02eb\7\'\2\2\u02eb\u00d4\3\2\2\2\u02ec\u02ed\7-\2\2\u02ed\u02ee"+
		"\7?\2\2\u02ee\u00d6\3\2\2\2\u02ef\u02f0\7/\2\2\u02f0\u02f1\7?\2\2\u02f1"+
		"\u00d8\3\2\2\2\u02f2\u02f3\7,\2\2\u02f3\u02f4\7?\2\2\u02f4\u00da\3\2\2"+
		"\2\u02f5\u02f6\7\61\2\2\u02f6\u02f7\7?\2\2\u02f7\u00dc\3\2\2\2\u02f8\u02f9"+
		"\7(\2\2\u02f9\u02fa\7?\2\2\u02fa\u00de\3\2\2\2\u02fb\u02fc\7~\2\2\u02fc"+
		"\u02fd\7?\2\2\u02fd\u00e0\3\2\2\2\u02fe\u02ff\7`\2\2\u02ff\u0300\7?\2"+
		"\2\u0300\u00e2\3\2\2\2\u0301\u0302\7\'\2\2\u0302\u0303\7?\2\2\u0303\u00e4"+
		"\3\2\2\2\u0304\u0305\7>\2\2\u0305\u0306\7>\2\2\u0306\u0307\7?\2\2\u0307"+
		"\u00e6\3\2\2\2\u0308\u0309\7@\2\2\u0309\u030a\7@\2\2\u030a\u030b\7?\2"+
		"\2\u030b\u00e8\3\2\2\2\u030c\u030d\7@\2\2\u030d\u030e\7@\2\2\u030e\u030f"+
		"\7@\2\2\u030f\u0310\7?\2\2\u0310\u00ea\3\2\2\2\u0311\u0315\5\u00edw\2"+
		"\u0312\u0314\5\u00efx\2\u0313\u0312\3\2\2\2\u0314\u0317\3\2\2\2\u0315"+
		"\u0313\3\2\2\2\u0315\u0316\3\2\2\2\u0316\u00ec\3\2\2\2\u0317\u0315\3\2"+
		"\2\2\u0318\u031f\t\21\2\2\u0319\u031a\n\22\2\2\u031a\u031f\6w\2\2\u031b"+
		"\u031c\t\23\2\2\u031c\u031d\t\24\2\2\u031d\u031f\6w\3\2\u031e\u0318\3"+
		"\2\2\2\u031e\u0319\3\2\2\2\u031e\u031b\3\2\2\2\u031f\u00ee\3\2\2\2\u0320"+
		"\u0327\t\25\2\2\u0321\u0322\n\22\2\2\u0322\u0327\6x\4\2\u0323\u0324\t"+
		"\23\2\2\u0324\u0325\t\24\2\2\u0325\u0327\6x\5\2\u0326\u0320\3\2\2\2\u0326"+
		"\u0321\3\2\2\2\u0326\u0323\3\2\2\2\u0327\u00f0\3\2\2\2\u0328\u0329\7B"+
		"\2\2\u0329\u00f2\3\2\2\2\u032a\u032c\t\26\2\2\u032b\u032a\3\2\2\2\u032c"+
		"\u032d\3\2\2\2\u032d\u032b\3\2\2\2\u032d\u032e\3\2\2\2\u032e\u032f\3\2"+
		"\2\2\u032f\u0330\bz\2\2\u0330\u00f4\3\2\2\2\u0331\u0332\7\61\2\2\u0332"+
		"\u0333\7,\2\2\u0333\u0337\3\2\2\2\u0334\u0336\13\2\2\2\u0335\u0334\3\2"+
		"\2\2\u0336\u0339\3\2\2\2\u0337\u0338\3\2\2\2\u0337\u0335\3\2\2\2\u0338"+
		"\u033a\3\2\2\2\u0339\u0337\3\2\2\2\u033a\u033b\7,\2\2\u033b\u033c\7\61"+
		"\2\2\u033c\u033d\3\2\2\2\u033d\u033e\b{\2\2\u033e\u00f6\3\2\2\2\u033f"+
		"\u0340\7\61\2\2\u0340\u0341\7\61\2\2\u0341\u0345\3\2\2\2\u0342\u0344\n"+
		"\27\2\2\u0343\u0342\3\2\2\2\u0344\u0347\3\2\2\2\u0345\u0343\3\2\2\2\u0345"+
		"\u0346\3\2\2\2\u0346\u0348\3\2\2\2\u0347\u0345\3\2\2\2\u0348\u0349\b|"+
		"\2\2\u0349\u00f8\3\2\2\2\66\2\u0194\u0199\u019f\u01a4\u01a8\u01ac\u01b0"+
		"\u01b7\u01bc\u01be\u01c4\u01c8\u01cc\u01d2\u01d7\u01e1\u01e5\u01eb\u01ef"+
		"\u01f7\u01fb\u0201\u020b\u020f\u0215\u0219\u021e\u0221\u0224\u0229\u022c"+
		"\u0231\u0236\u023e\u0249\u024d\u0252\u0256\u0266\u0270\u0276\u027d\u0281"+
		"\u0287\u0294\u0315\u031e\u0326\u032d\u0337\u0345\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}