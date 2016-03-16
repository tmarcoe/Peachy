// Generated from Ftl.g4 by ANTLR 4.5.2

package com.gemma.web.ftl;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FtlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FtlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FtlParser#transaction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTransaction(FtlParser.TransactionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#beginStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeginStatement(FtlParser.BeginStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#endStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndStatement(FtlParser.EndStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(FtlParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(FtlParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#amount}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAmount(FtlParser.AmountContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#description}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescription(FtlParser.DescriptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#account}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccount(FtlParser.AccountContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#lValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLValue(FtlParser.LValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#rValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRValue(FtlParser.RValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#rVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRVal(FtlParser.RValContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#lVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLVal(FtlParser.LValContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#operand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperand(FtlParser.OperandContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#mathOperand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMathOperand(FtlParser.MathOperandContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#bitWiseOperand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitWiseOperand(FtlParser.BitWiseOperandContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#decarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecarationStatement(FtlParser.DecarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(FtlParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(FtlParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(FtlParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#keywordAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeywordAssignment(FtlParser.KeywordAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#keyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword(FtlParser.KeywordContext ctx);
	/**
	 * Visit a parse tree produced by {@link FtlParser#ledgerType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLedgerType(FtlParser.LedgerTypeContext ctx);
}