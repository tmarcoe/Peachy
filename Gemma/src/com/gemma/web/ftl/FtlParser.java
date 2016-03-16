// Generated from Ftl.g4 by ANTLR 4.5.2

package com.gemma.web.ftl;
import com.gemma.web.ftl_helper.Transaction;
import com.gemma.web.ftl_helper.Variable;
import com.gemma.web.ftl_helper.ObjectMath;
import java.util.List;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FtlParser extends Parser {
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
	public static final int
		RULE_transaction = 0, RULE_beginStatement = 1, RULE_endStatement = 2, 
		RULE_statement = 3, RULE_expression = 4, RULE_amount = 5, RULE_description = 6, 
		RULE_account = 7, RULE_lValue = 8, RULE_rValue = 9, RULE_rVal = 10, RULE_lVal = 11, 
		RULE_operand = 12, RULE_mathOperand = 13, RULE_bitWiseOperand = 14, RULE_decarationStatement = 15, 
		RULE_type = 16, RULE_variable = 17, RULE_literal = 18, RULE_keywordAssignment = 19, 
		RULE_keyword = 20, RULE_ledgerType = 21;
	public static final String[] ruleNames = {
		"transaction", "beginStatement", "endStatement", "statement", "expression", 
		"amount", "description", "account", "lValue", "rValue", "rVal", "lVal", 
		"operand", "mathOperand", "bitWiseOperand", "decarationStatement", "type", 
		"variable", "literal", "keywordAssignment", "keyword", "ledgerType"
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

	@Override
	public String getGrammarFileName() { return "Ftl.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		public Transaction trans;
		public ObjectMath om = new ObjectMath();

	public FtlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class TransactionContext extends ParserRuleContext {
		public Transaction trans;
		public BeginStatementContext beginStatement() {
			return getRuleContext(BeginStatementContext.class,0);
		}
		public EndStatementContext endStatement() {
			return getRuleContext(EndStatementContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TransactionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public TransactionContext(ParserRuleContext parent, int invokingState, Transaction trans) {
			super(parent, invokingState);
			this.trans = trans;
		}
		@Override public int getRuleIndex() { return RULE_transaction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterTransaction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitTransaction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitTransaction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransactionContext transaction(Transaction trans) throws RecognitionException {
		TransactionContext _localctx = new TransactionContext(_ctx, getState(), trans);
		enterRule(_localctx, 0, RULE_transaction);
		this.trans = _localctx.trans;
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			beginStatement();
			setState(46); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(45);
				statement();
				}
				}
				setState(48); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Credit) | (1L << Debit) | (1L << Ledger) | (1L << BOOLEAN) | (1L << DOUBLE) | (1L << STRING) | (1L << LONG))) != 0) || _la==Identifier );
			setState(50);
			endStatement();
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

	public static class BeginStatementContext extends ParserRuleContext {
		public BeginStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_beginStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterBeginStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitBeginStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitBeginStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BeginStatementContext beginStatement() throws RecognitionException {
		BeginStatementContext _localctx = new BeginStatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_beginStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(T__0);
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

	public static class EndStatementContext extends ParserRuleContext {
		public EndStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_endStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterEndStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitEndStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitEndStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EndStatementContext endStatement() throws RecognitionException {
		EndStatementContext _localctx = new EndStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_endStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(T__1);

					System.out.println("\n");
					System.out.println("******************* VARIABLES *************************");
					List<Variable> varList = trans.getVarList();
					for (Variable var: varList) {
						System.out.printf("%s %s = %s\n", var.getType(), var.getName(), var.getValue());
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

	public static class StatementContext extends ParserRuleContext {
		public DecarationStatementContext decarationStatement() {
			return getRuleContext(DecarationStatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_statement);
		try {
			setState(66);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case DOUBLE:
			case STRING:
			case LONG:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				decarationStatement();
				setState(58);
				match(SEMI);
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				expression();
				setState(61);
				match(SEMI);
				}
				break;
			case Credit:
			case Debit:
			case Ledger:
				enterOuterAlt(_localctx, 3);
				{
				setState(63);
				keyword();
				setState(64);
				match(SEMI);
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

	public static class ExpressionContext extends ParserRuleContext {
		public String name;
		public Object obj;
		public LValueContext lValue;
		public RValueContext rValue;
		public LValueContext lValue() {
			return getRuleContext(LValueContext.class,0);
		}
		public RValueContext rValue() {
			return getRuleContext(RValueContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			((ExpressionContext)_localctx).lValue = lValue();
			setState(69);
			match(ASSIGN);
			setState(70);
			((ExpressionContext)_localctx).rValue = rValue();

					((ExpressionContext)_localctx).name =  (((ExpressionContext)_localctx).lValue!=null?_input.getText(((ExpressionContext)_localctx).lValue.start,((ExpressionContext)_localctx).lValue.stop):null);
					((ExpressionContext)_localctx).obj =  ((ExpressionContext)_localctx).rValue.obj;
					trans.assignVariable(_localctx.name, _localctx.obj);
				
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

	public static class AmountContext extends ParserRuleContext {
		public Double amt;
		public LiteralContext literal;
		public VariableContext variable;
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public AmountContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_amount; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterAmount(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitAmount(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitAmount(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AmountContext amount() throws RecognitionException {
		AmountContext _localctx = new AmountContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_amount);
		try {
			setState(79);
			switch (_input.LA(1)) {
			case IntegerLiteral:
			case FloatOrDoubleLiteral:
			case BooleanLiteral:
			case StringLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(73);
				((AmountContext)_localctx).literal = literal();

						((AmountContext)_localctx).amt =  (Double) ((AmountContext)_localctx).literal.obj;
					
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(76);
				((AmountContext)_localctx).variable = variable();

						((AmountContext)_localctx).amt =  (Double) trans.getValue((((AmountContext)_localctx).variable!=null?_input.getText(((AmountContext)_localctx).variable.start,((AmountContext)_localctx).variable.stop):null));
					
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

	public static class DescriptionContext extends ParserRuleContext {
		public String desc;
		public LiteralContext literal;
		public VariableContext variable;
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public DescriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitDescription(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitDescription(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescriptionContext description() throws RecognitionException {
		DescriptionContext _localctx = new DescriptionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_description);
		try {
			setState(87);
			switch (_input.LA(1)) {
			case IntegerLiteral:
			case FloatOrDoubleLiteral:
			case BooleanLiteral:
			case StringLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				((DescriptionContext)_localctx).literal = literal();

						((DescriptionContext)_localctx).desc =  (String) ((DescriptionContext)_localctx).literal.obj;
					
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(84);
				((DescriptionContext)_localctx).variable = variable();

						((DescriptionContext)_localctx).desc =  (String) trans.getValue((((DescriptionContext)_localctx).variable!=null?_input.getText(((DescriptionContext)_localctx).variable.start,((DescriptionContext)_localctx).variable.stop):null));
					
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

	public static class AccountContext extends ParserRuleContext {
		public String acc;
		public LiteralContext literal;
		public VariableContext variable;
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public AccountContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_account; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterAccount(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitAccount(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitAccount(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccountContext account() throws RecognitionException {
		AccountContext _localctx = new AccountContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_account);
		try {
			setState(95);
			switch (_input.LA(1)) {
			case IntegerLiteral:
			case FloatOrDoubleLiteral:
			case BooleanLiteral:
			case StringLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				((AccountContext)_localctx).literal = literal();

						((AccountContext)_localctx).acc =  (String) ((AccountContext)_localctx).literal.obj;
					
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				((AccountContext)_localctx).variable = variable();

						((AccountContext)_localctx).acc =  (String) trans.getValue((((AccountContext)_localctx).variable!=null?_input.getText(((AccountContext)_localctx).variable.start,((AccountContext)_localctx).variable.stop):null));
					
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

	public static class LValueContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public LValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterLValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitLValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitLValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LValueContext lValue() throws RecognitionException {
		LValueContext _localctx = new LValueContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_lValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			variable();
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

	public static class RValueContext extends ParserRuleContext {
		public Object obj;
		public VariableContext variable;
		public LiteralContext literal;
		public KeywordAssignmentContext keywordAssignment;
		public LValContext lVal;
		public OperandContext operand;
		public RValContext rVal;
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public KeywordAssignmentContext keywordAssignment() {
			return getRuleContext(KeywordAssignmentContext.class,0);
		}
		public LValContext lVal() {
			return getRuleContext(LValContext.class,0);
		}
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public RValContext rVal() {
			return getRuleContext(RValContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<RValueContext> rValue() {
			return getRuleContexts(RValueContext.class);
		}
		public RValueContext rValue(int i) {
			return getRuleContext(RValueContext.class,i);
		}
		public RValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterRValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitRValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitRValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RValueContext rValue() throws RecognitionException {
		RValueContext _localctx = new RValueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_rValue);

				Object rh;
				Object lh;
			
		try {
			int _alt;
			setState(122);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				((RValueContext)_localctx).variable = variable();

						((RValueContext)_localctx).obj =  trans.getValue((((RValueContext)_localctx).variable!=null?_input.getText(((RValueContext)_localctx).variable.start,((RValueContext)_localctx).variable.stop):null));
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(102);
				((RValueContext)_localctx).literal = literal();

						((RValueContext)_localctx).obj =  ((RValueContext)_localctx).literal.obj;
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(105);
				((RValueContext)_localctx).keywordAssignment = keywordAssignment();

						((RValueContext)_localctx).obj =  ((RValueContext)_localctx).keywordAssignment.obj;
					
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(108);
				((RValueContext)_localctx).lVal = lVal();
				setState(109);
				((RValueContext)_localctx).operand = operand();
				setState(110);
				((RValueContext)_localctx).rVal = rVal();

								
						lh = ((RValueContext)_localctx).lVal.obj;
						rh = ((RValueContext)_localctx).rVal.obj;
						
						switch ((((RValueContext)_localctx).operand!=null?_input.getText(((RValueContext)_localctx).operand.start,((RValueContext)_localctx).operand.stop):null)) {
						case "+":
							((RValueContext)_localctx).obj =  om.addObject(lh, rh);
							break;
						
						case "-":
							((RValueContext)_localctx).obj =  om.subObject(lh, rh);
							break;
							
						case "*":
							((RValueContext)_localctx).obj =  om.multObject(lh, rh);
							break;
							
						case "/":
							((RValueContext)_localctx).obj =  om.divObject(lh, rh);
							break;
						}
						
					
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(113);
				expression();
				setState(119);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(114);
						operand();
						setState(115);
						rValue();
						}
						} 
					}
					setState(121);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
				}
				}
				break;
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

	public static class RValContext extends ParserRuleContext {
		public Object obj;
		public LiteralContext literal;
		public VariableContext variable;
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public RValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rVal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterRVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitRVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitRVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RValContext rVal() throws RecognitionException {
		RValContext _localctx = new RValContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_rVal);
		try {
			setState(130);
			switch (_input.LA(1)) {
			case IntegerLiteral:
			case FloatOrDoubleLiteral:
			case BooleanLiteral:
			case StringLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(124);
				((RValContext)_localctx).literal = literal();

						((RValContext)_localctx).obj =  ((RValContext)_localctx).literal.obj;
					
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				((RValContext)_localctx).variable = variable();

						((RValContext)_localctx).obj =  trans.getValue((((RValContext)_localctx).variable!=null?_input.getText(((RValContext)_localctx).variable.start,((RValContext)_localctx).variable.stop):null));
					
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

	public static class LValContext extends ParserRuleContext {
		public Object obj;
		public LiteralContext literal;
		public VariableContext variable;
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public LValContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lVal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterLVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitLVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitLVal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LValContext lVal() throws RecognitionException {
		LValContext _localctx = new LValContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_lVal);
		try {
			setState(138);
			switch (_input.LA(1)) {
			case IntegerLiteral:
			case FloatOrDoubleLiteral:
			case BooleanLiteral:
			case StringLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(132);
				((LValContext)_localctx).literal = literal();

						((LValContext)_localctx).obj =  ((LValContext)_localctx).literal.obj;
					
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				((LValContext)_localctx).variable = variable();

						((LValContext)_localctx).obj =  trans.getValue((((LValContext)_localctx).variable!=null?_input.getText(((LValContext)_localctx).variable.start,((LValContext)_localctx).variable.stop):null));
					
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

	public static class OperandContext extends ParserRuleContext {
		public MathOperandContext mathOperand() {
			return getRuleContext(MathOperandContext.class,0);
		}
		public BitWiseOperandContext bitWiseOperand() {
			return getRuleContext(BitWiseOperandContext.class,0);
		}
		public OperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitOperand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitOperand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperandContext operand() throws RecognitionException {
		OperandContext _localctx = new OperandContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_operand);
		try {
			setState(142);
			switch (_input.LA(1)) {
			case T__2:
			case ADD:
			case SUB:
			case MUL:
			case DIV:
			case MOD:
				enterOuterAlt(_localctx, 1);
				{
				setState(140);
				mathOperand();
				}
				break;
			case BANG:
			case BITAND:
			case BITOR:
			case CARET:
				enterOuterAlt(_localctx, 2);
				{
				setState(141);
				bitWiseOperand();
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

	public static class MathOperandContext extends ParserRuleContext {
		public MathOperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mathOperand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterMathOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitMathOperand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitMathOperand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MathOperandContext mathOperand() throws RecognitionException {
		MathOperandContext _localctx = new MathOperandContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_mathOperand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			_la = _input.LA(1);
			if ( !(((((_la - 3)) & ~0x3f) == 0 && ((1L << (_la - 3)) & ((1L << (T__2 - 3)) | (1L << (ADD - 3)) | (1L << (SUB - 3)) | (1L << (MUL - 3)) | (1L << (DIV - 3)) | (1L << (MOD - 3)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
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

	public static class BitWiseOperandContext extends ParserRuleContext {
		public BitWiseOperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bitWiseOperand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterBitWiseOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitBitWiseOperand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitBitWiseOperand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BitWiseOperandContext bitWiseOperand() throws RecognitionException {
		BitWiseOperandContext _localctx = new BitWiseOperandContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_bitWiseOperand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			_la = _input.LA(1);
			if ( !(((((_la - 46)) & ~0x3f) == 0 && ((1L << (_la - 46)) & ((1L << (BANG - 46)) | (1L << (BITAND - 46)) | (1L << (BITOR - 46)) | (1L << (CARET - 46)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
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

	public static class DecarationStatementContext extends ParserRuleContext {
		public TypeContext type;
		public Token Identifier;
		public ExpressionContext expression;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(FtlParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DecarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decarationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterDecarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitDecarationStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitDecarationStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecarationStatementContext decarationStatement() throws RecognitionException {
		DecarationStatementContext _localctx = new DecarationStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_decarationStatement);
		try {
			setState(156);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				((DecarationStatementContext)_localctx).type = type();
				setState(149);
				((DecarationStatementContext)_localctx).Identifier = match(Identifier);

						trans.addVariable((((DecarationStatementContext)_localctx).Identifier!=null?((DecarationStatementContext)_localctx).Identifier.getText():null), (((DecarationStatementContext)_localctx).type!=null?_input.getText(((DecarationStatementContext)_localctx).type.start,((DecarationStatementContext)_localctx).type.stop):null));
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				((DecarationStatementContext)_localctx).type = type();
				setState(153);
				((DecarationStatementContext)_localctx).expression = expression();

						trans.addVariable(((DecarationStatementContext)_localctx).expression.name, (((DecarationStatementContext)_localctx).type!=null?_input.getText(((DecarationStatementContext)_localctx).type.start,((DecarationStatementContext)_localctx).type.stop):null), ((DecarationStatementContext)_localctx).expression.obj);
					
				}
				break;
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
		public TerminalNode BOOLEAN() { return getToken(FtlParser.BOOLEAN, 0); }
		public TerminalNode DOUBLE() { return getToken(FtlParser.DOUBLE, 0); }
		public TerminalNode LONG() { return getToken(FtlParser.LONG, 0); }
		public TerminalNode STRING() { return getToken(FtlParser.STRING, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << DOUBLE) | (1L << STRING) | (1L << LONG))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
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

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(FtlParser.Identifier, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(Identifier);
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

	public static class LiteralContext extends ParserRuleContext {
		public Object obj;
		public Token IntegerLiteral;
		public Token FloatOrDoubleLiteral;
		public Token StringLiteral;
		public TerminalNode IntegerLiteral() { return getToken(FtlParser.IntegerLiteral, 0); }
		public TerminalNode FloatOrDoubleLiteral() { return getToken(FtlParser.FloatOrDoubleLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(FtlParser.StringLiteral, 0); }
		public TerminalNode BooleanLiteral() { return getToken(FtlParser.BooleanLiteral, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_literal);

				String strLiteral;
			
		try {
			setState(169);
			switch (_input.LA(1)) {
			case IntegerLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				((LiteralContext)_localctx).IntegerLiteral = match(IntegerLiteral);

						((LiteralContext)_localctx).obj =  Long.valueOf((((LiteralContext)_localctx).IntegerLiteral!=null?((LiteralContext)_localctx).IntegerLiteral.getText():null));
					
				}
				break;
			case FloatOrDoubleLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
				((LiteralContext)_localctx).FloatOrDoubleLiteral = match(FloatOrDoubleLiteral);

						((LiteralContext)_localctx).obj =  Double.valueOf((((LiteralContext)_localctx).FloatOrDoubleLiteral!=null?((LiteralContext)_localctx).FloatOrDoubleLiteral.getText():null));
					
				}
				break;
			case StringLiteral:
				enterOuterAlt(_localctx, 3);
				{
				setState(166);
				((LiteralContext)_localctx).StringLiteral = match(StringLiteral);

						strLiteral = (String) (((LiteralContext)_localctx).StringLiteral!=null?((LiteralContext)_localctx).StringLiteral.getText():null);
						((LiteralContext)_localctx).obj =  strLiteral.substring(1, strLiteral.length() - 1);
					
				}
				break;
			case BooleanLiteral:
				enterOuterAlt(_localctx, 4);
				{
				setState(168);
				match(BooleanLiteral);
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

	public static class KeywordAssignmentContext extends ParserRuleContext {
		public Object obj;
		public TerminalNode GetAmount() { return getToken(FtlParser.GetAmount, 0); }
		public TerminalNode GetTax() { return getToken(FtlParser.GetTax, 0); }
		public TerminalNode GetDescription() { return getToken(FtlParser.GetDescription, 0); }
		public KeywordAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keywordAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterKeywordAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitKeywordAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitKeywordAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeywordAssignmentContext keywordAssignment() throws RecognitionException {
		KeywordAssignmentContext _localctx = new KeywordAssignmentContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_keywordAssignment);
		try {
			setState(177);
			switch (_input.LA(1)) {
			case GetAmount:
				enterOuterAlt(_localctx, 1);
				{
				setState(171);
				match(GetAmount);

						((KeywordAssignmentContext)_localctx).obj =  (Double) trans.getAmount();
					
				}
				break;
			case GetTax:
				enterOuterAlt(_localctx, 2);
				{
				setState(173);
				match(GetTax);

						((KeywordAssignmentContext)_localctx).obj =  (Double) trans.getTax();
					
				}
				break;
			case GetDescription:
				enterOuterAlt(_localctx, 3);
				{
				setState(175);
				match(GetDescription);

						((KeywordAssignmentContext)_localctx).obj =  (String) trans.getDescription();
					
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

	public static class KeywordContext extends ParserRuleContext {
		public AmountContext amount;
		public AccountContext account;
		public LedgerTypeContext ledgerType;
		public DescriptionContext description;
		public TerminalNode Credit() { return getToken(FtlParser.Credit, 0); }
		public AmountContext amount() {
			return getRuleContext(AmountContext.class,0);
		}
		public AccountContext account() {
			return getRuleContext(AccountContext.class,0);
		}
		public TerminalNode Debit() { return getToken(FtlParser.Debit, 0); }
		public TerminalNode Ledger() { return getToken(FtlParser.Ledger, 0); }
		public LedgerTypeContext ledgerType() {
			return getRuleContext(LedgerTypeContext.class,0);
		}
		public DescriptionContext description() {
			return getRuleContext(DescriptionContext.class,0);
		}
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitKeyword(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitKeyword(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_keyword);
		try {
			setState(207);
			switch (_input.LA(1)) {
			case Credit:
				enterOuterAlt(_localctx, 1);
				{
				setState(179);
				match(Credit);
				setState(180);
				match(LPAREN);
				setState(181);
				((KeywordContext)_localctx).amount = amount();
				setState(182);
				match(COMMA);
				setState(183);
				((KeywordContext)_localctx).account = account();
				setState(184);
				match(RPAREN);

						trans.credit(((KeywordContext)_localctx).amount.amt, ((KeywordContext)_localctx).account.acc);
					
				}
				break;
			case Debit:
				enterOuterAlt(_localctx, 2);
				{
				setState(187);
				match(Debit);
				setState(188);
				match(LPAREN);
				setState(189);
				((KeywordContext)_localctx).amount = amount();
				setState(190);
				match(COMMA);
				setState(191);
				((KeywordContext)_localctx).account = account();
				setState(192);
				match(RPAREN);

						trans.debit(((KeywordContext)_localctx).amount.amt, ((KeywordContext)_localctx).account.acc);
					
				}
				break;
			case Ledger:
				enterOuterAlt(_localctx, 3);
				{
				setState(195);
				match(Ledger);
				setState(196);
				match(LPAREN);
				setState(197);
				((KeywordContext)_localctx).ledgerType = ledgerType();
				setState(198);
				match(COMMA);
				setState(199);
				((KeywordContext)_localctx).amount = amount();
				setState(200);
				match(COMMA);
				setState(201);
				((KeywordContext)_localctx).account = account();
				setState(202);
				match(COMMA);
				setState(203);
				((KeywordContext)_localctx).description = description();
				setState(204);
				match(RPAREN);

						trans.ledger(((KeywordContext)_localctx).ledgerType.retType, ((KeywordContext)_localctx).amount.amt, ((KeywordContext)_localctx).account.acc, ((KeywordContext)_localctx).description.desc);
					
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

	public static class LedgerTypeContext extends ParserRuleContext {
		public char retType;
		public LedgerTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ledgerType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).enterLedgerType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FtlListener ) ((FtlListener)listener).exitLedgerType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FtlVisitor ) return ((FtlVisitor<? extends T>)visitor).visitLedgerType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LedgerTypeContext ledgerType() throws RecognitionException {
		LedgerTypeContext _localctx = new LedgerTypeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_ledgerType);
		try {
			setState(213);
			switch (_input.LA(1)) {
			case T__3:
				enterOuterAlt(_localctx, 1);
				{
				setState(209);
				match(T__3);
				((LedgerTypeContext)_localctx).retType =  'C';
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(211);
				match(T__4);
				((LedgerTypeContext)_localctx).retType =  'D';
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3S\u00da\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\6\2\61\n\2"+
		"\r\2\16\2\62\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\5\5E\n\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7R\n\7\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\5\bZ\n\b\3\t\3\t\3\t\3\t\3\t\3\t\5\tb\n\t\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\7\13x\n\13\f\13\16\13{\13\13\5\13}\n\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\5\f\u0085\n\f\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u008d\n\r"+
		"\3\16\3\16\5\16\u0091\n\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\5\21\u009f\n\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\5\24\u00ac\n\24\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u00b4"+
		"\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\5\26\u00d2\n\26\3\27\3\27\3\27\3\27\5\27\u00d8\n\27\3\27\2\2\30"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,\2\5\5\2\5\5<?CC\4\2\60"+
		"\60@B\6\2\17\17\23\23\27\27\33\33\u00da\2.\3\2\2\2\4\66\3\2\2\2\68\3\2"+
		"\2\2\bD\3\2\2\2\nF\3\2\2\2\fQ\3\2\2\2\16Y\3\2\2\2\20a\3\2\2\2\22c\3\2"+
		"\2\2\24|\3\2\2\2\26\u0084\3\2\2\2\30\u008c\3\2\2\2\32\u0090\3\2\2\2\34"+
		"\u0092\3\2\2\2\36\u0094\3\2\2\2 \u009e\3\2\2\2\"\u00a0\3\2\2\2$\u00a2"+
		"\3\2\2\2&\u00ab\3\2\2\2(\u00b3\3\2\2\2*\u00d1\3\2\2\2,\u00d7\3\2\2\2."+
		"\60\5\4\3\2/\61\5\b\5\2\60/\3\2\2\2\61\62\3\2\2\2\62\60\3\2\2\2\62\63"+
		"\3\2\2\2\63\64\3\2\2\2\64\65\5\6\4\2\65\3\3\2\2\2\66\67\7\3\2\2\67\5\3"+
		"\2\2\289\7\4\2\29:\b\4\1\2:\7\3\2\2\2;<\5 \21\2<=\7*\2\2=E\3\2\2\2>?\5"+
		"\n\6\2?@\7*\2\2@E\3\2\2\2AB\5*\26\2BC\7*\2\2CE\3\2\2\2D;\3\2\2\2D>\3\2"+
		"\2\2DA\3\2\2\2E\t\3\2\2\2FG\5\22\n\2GH\7-\2\2HI\5\24\13\2IJ\b\6\1\2J\13"+
		"\3\2\2\2KL\5&\24\2LM\b\7\1\2MR\3\2\2\2NO\5$\23\2OP\b\7\1\2PR\3\2\2\2Q"+
		"K\3\2\2\2QN\3\2\2\2R\r\3\2\2\2ST\5&\24\2TU\b\b\1\2UZ\3\2\2\2VW\5$\23\2"+
		"WX\b\b\1\2XZ\3\2\2\2YS\3\2\2\2YV\3\2\2\2Z\17\3\2\2\2[\\\5&\24\2\\]\b\t"+
		"\1\2]b\3\2\2\2^_\5$\23\2_`\b\t\1\2`b\3\2\2\2a[\3\2\2\2a^\3\2\2\2b\21\3"+
		"\2\2\2cd\5$\23\2d\23\3\2\2\2ef\5$\23\2fg\b\13\1\2g}\3\2\2\2hi\5&\24\2"+
		"ij\b\13\1\2j}\3\2\2\2kl\5(\25\2lm\b\13\1\2m}\3\2\2\2no\5\30\r\2op\5\32"+
		"\16\2pq\5\26\f\2qr\b\13\1\2r}\3\2\2\2sy\5\n\6\2tu\5\32\16\2uv\5\24\13"+
		"\2vx\3\2\2\2wt\3\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z}\3\2\2\2{y\3\2\2"+
		"\2|e\3\2\2\2|h\3\2\2\2|k\3\2\2\2|n\3\2\2\2|s\3\2\2\2}\25\3\2\2\2~\177"+
		"\5&\24\2\177\u0080\b\f\1\2\u0080\u0085\3\2\2\2\u0081\u0082\5$\23\2\u0082"+
		"\u0083\b\f\1\2\u0083\u0085\3\2\2\2\u0084~\3\2\2\2\u0084\u0081\3\2\2\2"+
		"\u0085\27\3\2\2\2\u0086\u0087\5&\24\2\u0087\u0088\b\r\1\2\u0088\u008d"+
		"\3\2\2\2\u0089\u008a\5$\23\2\u008a\u008b\b\r\1\2\u008b\u008d\3\2\2\2\u008c"+
		"\u0086\3\2\2\2\u008c\u0089\3\2\2\2\u008d\31\3\2\2\2\u008e\u0091\5\34\17"+
		"\2\u008f\u0091\5\36\20\2\u0090\u008e\3\2\2\2\u0090\u008f\3\2\2\2\u0091"+
		"\33\3\2\2\2\u0092\u0093\t\2\2\2\u0093\35\3\2\2\2\u0094\u0095\t\3\2\2\u0095"+
		"\37\3\2\2\2\u0096\u0097\5\"\22\2\u0097\u0098\7O\2\2\u0098\u0099\b\21\1"+
		"\2\u0099\u009f\3\2\2\2\u009a\u009b\5\"\22\2\u009b\u009c\5\n\6\2\u009c"+
		"\u009d\b\21\1\2\u009d\u009f\3\2\2\2\u009e\u0096\3\2\2\2\u009e\u009a\3"+
		"\2\2\2\u009f!\3\2\2\2\u00a0\u00a1\t\4\2\2\u00a1#\3\2\2\2\u00a2\u00a3\7"+
		"O\2\2\u00a3%\3\2\2\2\u00a4\u00a5\7\35\2\2\u00a5\u00ac\b\24\1\2\u00a6\u00a7"+
		"\7\36\2\2\u00a7\u00ac\b\24\1\2\u00a8\u00a9\7\"\2\2\u00a9\u00ac\b\24\1"+
		"\2\u00aa\u00ac\7 \2\2\u00ab\u00a4\3\2\2\2\u00ab\u00a6\3\2\2\2\u00ab\u00a8"+
		"\3\2\2\2\u00ab\u00aa\3\2\2\2\u00ac\'\3\2\2\2\u00ad\u00ae\7\f\2\2\u00ae"+
		"\u00b4\b\25\1\2\u00af\u00b0\7\r\2\2\u00b0\u00b4\b\25\1\2\u00b1\u00b2\7"+
		"\16\2\2\u00b2\u00b4\b\25\1\2\u00b3\u00ad\3\2\2\2\u00b3\u00af\3\2\2\2\u00b3"+
		"\u00b1\3\2\2\2\u00b4)\3\2\2\2\u00b5\u00b6\7\t\2\2\u00b6\u00b7\7$\2\2\u00b7"+
		"\u00b8\5\f\7\2\u00b8\u00b9\7+\2\2\u00b9\u00ba\5\20\t\2\u00ba\u00bb\7%"+
		"\2\2\u00bb\u00bc\b\26\1\2\u00bc\u00d2\3\2\2\2\u00bd\u00be\7\n\2\2\u00be"+
		"\u00bf\7$\2\2\u00bf\u00c0\5\f\7\2\u00c0\u00c1\7+\2\2\u00c1\u00c2\5\20"+
		"\t\2\u00c2\u00c3\7%\2\2\u00c3\u00c4\b\26\1\2\u00c4\u00d2\3\2\2\2\u00c5"+
		"\u00c6\7\13\2\2\u00c6\u00c7\7$\2\2\u00c7\u00c8\5,\27\2\u00c8\u00c9\7+"+
		"\2\2\u00c9\u00ca\5\f\7\2\u00ca\u00cb\7+\2\2\u00cb\u00cc\5\20\t\2\u00cc"+
		"\u00cd\7+\2\2\u00cd\u00ce\5\16\b\2\u00ce\u00cf\7%\2\2\u00cf\u00d0\b\26"+
		"\1\2\u00d0\u00d2\3\2\2\2\u00d1\u00b5\3\2\2\2\u00d1\u00bd\3\2\2\2\u00d1"+
		"\u00c5\3\2\2\2\u00d2+\3\2\2\2\u00d3\u00d4\7\6\2\2\u00d4\u00d8\b\27\1\2"+
		"\u00d5\u00d6\7\7\2\2\u00d6\u00d8\b\27\1\2\u00d7\u00d3\3\2\2\2\u00d7\u00d5"+
		"\3\2\2\2\u00d8-\3\2\2\2\21\62DQYay|\u0084\u008c\u0090\u009e\u00ab\u00b3"+
		"\u00d1\u00d7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}