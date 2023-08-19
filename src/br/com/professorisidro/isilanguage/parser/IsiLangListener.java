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

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IsiLangParser}.
 */
public interface IsiLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(IsiLangParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(IsiLangParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(IsiLangParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(IsiLangParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(IsiLangParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(IsiLangParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(IsiLangParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(IsiLangParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void enterCmd(IsiLangParser.CmdContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#cmd}.
	 * @param ctx the parse tree
	 */
	void exitCmd(IsiLangParser.CmdContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#readCommand}.
	 * @param ctx the parse tree
	 */
	void enterReadCommand(IsiLangParser.ReadCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#readCommand}.
	 * @param ctx the parse tree
	 */
	void exitReadCommand(IsiLangParser.ReadCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#writeCommand}.
	 * @param ctx the parse tree
	 */
	void enterWriteCommand(IsiLangParser.WriteCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#writeCommand}.
	 * @param ctx the parse tree
	 */
	void exitWriteCommand(IsiLangParser.WriteCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#attribCommand}.
	 * @param ctx the parse tree
	 */
	void enterAttribCommand(IsiLangParser.AttribCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#attribCommand}.
	 * @param ctx the parse tree
	 */
	void exitAttribCommand(IsiLangParser.AttribCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#conditionalCommand}.
	 * @param ctx the parse tree
	 */
	void enterConditionalCommand(IsiLangParser.ConditionalCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#conditionalCommand}.
	 * @param ctx the parse tree
	 */
	void exitConditionalCommand(IsiLangParser.ConditionalCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#whileCommand}.
	 * @param ctx the parse tree
	 */
	void enterWhileCommand(IsiLangParser.WhileCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#whileCommand}.
	 * @param ctx the parse tree
	 */
	void exitWhileCommand(IsiLangParser.WhileCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#caseCommand}.
	 * @param ctx the parse tree
	 */
	void enterCaseCommand(IsiLangParser.CaseCommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#caseCommand}.
	 * @param ctx the parse tree
	 */
	void exitCaseCommand(IsiLangParser.CaseCommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(IsiLangParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(IsiLangParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#expr_}.
	 * @param ctx the parse tree
	 */
	void enterExpr_(IsiLangParser.Expr_Context ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#expr_}.
	 * @param ctx the parse tree
	 */
	void exitExpr_(IsiLangParser.Expr_Context ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(IsiLangParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(IsiLangParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#term_}.
	 * @param ctx the parse tree
	 */
	void enterTerm_(IsiLangParser.Term_Context ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#term_}.
	 * @param ctx the parse tree
	 */
	void exitTerm_(IsiLangParser.Term_Context ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLangParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(IsiLangParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLangParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(IsiLangParser.FactorContext ctx);
}