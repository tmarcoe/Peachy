// Generated from Ftl.g4 by ANTLR 4.5.2

package com.gemma.web.ftl;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FtlParser}.
 */
public interface FtlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FtlParser#transaction}.
	 * @param ctx the parse tree
	 */
	void enterTransaction(FtlParser.TransactionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#transaction}.
	 * @param ctx the parse tree
	 */
	void exitTransaction(FtlParser.TransactionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#beginStatement}.
	 * @param ctx the parse tree
	 */
	void enterBeginStatement(FtlParser.BeginStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#beginStatement}.
	 * @param ctx the parse tree
	 */
	void exitBeginStatement(FtlParser.BeginStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#endStatement}.
	 * @param ctx the parse tree
	 */
	void enterEndStatement(FtlParser.EndStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#endStatement}.
	 * @param ctx the parse tree
	 */
	void exitEndStatement(FtlParser.EndStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(FtlParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(FtlParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(FtlParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(FtlParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#amount}.
	 * @param ctx the parse tree
	 */
	void enterAmount(FtlParser.AmountContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#amount}.
	 * @param ctx the parse tree
	 */
	void exitAmount(FtlParser.AmountContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#description}.
	 * @param ctx the parse tree
	 */
	void enterDescription(FtlParser.DescriptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#description}.
	 * @param ctx the parse tree
	 */
	void exitDescription(FtlParser.DescriptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#account}.
	 * @param ctx the parse tree
	 */
	void enterAccount(FtlParser.AccountContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#account}.
	 * @param ctx the parse tree
	 */
	void exitAccount(FtlParser.AccountContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#lValue}.
	 * @param ctx the parse tree
	 */
	void enterLValue(FtlParser.LValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#lValue}.
	 * @param ctx the parse tree
	 */
	void exitLValue(FtlParser.LValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#rValue}.
	 * @param ctx the parse tree
	 */
	void enterRValue(FtlParser.RValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#rValue}.
	 * @param ctx the parse tree
	 */
	void exitRValue(FtlParser.RValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#rVal}.
	 * @param ctx the parse tree
	 */
	void enterRVal(FtlParser.RValContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#rVal}.
	 * @param ctx the parse tree
	 */
	void exitRVal(FtlParser.RValContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#lVal}.
	 * @param ctx the parse tree
	 */
	void enterLVal(FtlParser.LValContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#lVal}.
	 * @param ctx the parse tree
	 */
	void exitLVal(FtlParser.LValContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#operand}.
	 * @param ctx the parse tree
	 */
	void enterOperand(FtlParser.OperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#operand}.
	 * @param ctx the parse tree
	 */
	void exitOperand(FtlParser.OperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#mathOperand}.
	 * @param ctx the parse tree
	 */
	void enterMathOperand(FtlParser.MathOperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#mathOperand}.
	 * @param ctx the parse tree
	 */
	void exitMathOperand(FtlParser.MathOperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#bitWiseOperand}.
	 * @param ctx the parse tree
	 */
	void enterBitWiseOperand(FtlParser.BitWiseOperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#bitWiseOperand}.
	 * @param ctx the parse tree
	 */
	void exitBitWiseOperand(FtlParser.BitWiseOperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#decarationStatement}.
	 * @param ctx the parse tree
	 */
	void enterDecarationStatement(FtlParser.DecarationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#decarationStatement}.
	 * @param ctx the parse tree
	 */
	void exitDecarationStatement(FtlParser.DecarationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(FtlParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(FtlParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(FtlParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(FtlParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(FtlParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(FtlParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#keywordAssignment}.
	 * @param ctx the parse tree
	 */
	void enterKeywordAssignment(FtlParser.KeywordAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#keywordAssignment}.
	 * @param ctx the parse tree
	 */
	void exitKeywordAssignment(FtlParser.KeywordAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#keyword}.
	 * @param ctx the parse tree
	 */
	void enterKeyword(FtlParser.KeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#keyword}.
	 * @param ctx the parse tree
	 */
	void exitKeyword(FtlParser.KeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link FtlParser#ledgerType}.
	 * @param ctx the parse tree
	 */
	void enterLedgerType(FtlParser.LedgerTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link FtlParser#ledgerType}.
	 * @param ctx the parse tree
	 */
	void exitLedgerType(FtlParser.LedgerTypeContext ctx);
}