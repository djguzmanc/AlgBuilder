// Generated from AlgBuilderLanguage.g by ANTLR 4.5.3
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AlgBuilderLanguageParser}.
 */
public interface AlgBuilderLanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AlgBuilderLanguageParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(AlgBuilderLanguageParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgBuilderLanguageParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(AlgBuilderLanguageParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgBuilderLanguageParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(AlgBuilderLanguageParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgBuilderLanguageParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(AlgBuilderLanguageParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgBuilderLanguageParser#mainprocess}.
	 * @param ctx the parse tree
	 */
	void enterMainprocess(AlgBuilderLanguageParser.MainprocessContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgBuilderLanguageParser#mainprocess}.
	 * @param ctx the parse tree
	 */
	void exitMainprocess(AlgBuilderLanguageParser.MainprocessContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgBuilderLanguageParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(AlgBuilderLanguageParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgBuilderLanguageParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(AlgBuilderLanguageParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgBuilderLanguageParser#returnstatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnstatement(AlgBuilderLanguageParser.ReturnstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgBuilderLanguageParser#returnstatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnstatement(AlgBuilderLanguageParser.ReturnstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgBuilderLanguageParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(AlgBuilderLanguageParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgBuilderLanguageParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(AlgBuilderLanguageParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgBuilderLanguageParser#forloop}.
	 * @param ctx the parse tree
	 */
	void enterForloop(AlgBuilderLanguageParser.ForloopContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgBuilderLanguageParser#forloop}.
	 * @param ctx the parse tree
	 */
	void exitForloop(AlgBuilderLanguageParser.ForloopContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgBuilderLanguageParser#whileloop}.
	 * @param ctx the parse tree
	 */
	void enterWhileloop(AlgBuilderLanguageParser.WhileloopContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgBuilderLanguageParser#whileloop}.
	 * @param ctx the parse tree
	 */
	void exitWhileloop(AlgBuilderLanguageParser.WhileloopContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgBuilderLanguageParser#ifstatement}.
	 * @param ctx the parse tree
	 */
	void enterIfstatement(AlgBuilderLanguageParser.IfstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgBuilderLanguageParser#ifstatement}.
	 * @param ctx the parse tree
	 */
	void exitIfstatement(AlgBuilderLanguageParser.IfstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgBuilderLanguageParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(AlgBuilderLanguageParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgBuilderLanguageParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(AlgBuilderLanguageParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgBuilderLanguageParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(AlgBuilderLanguageParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgBuilderLanguageParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(AlgBuilderLanguageParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgBuilderLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(AlgBuilderLanguageParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgBuilderLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(AlgBuilderLanguageParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgBuilderLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(AlgBuilderLanguageParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgBuilderLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(AlgBuilderLanguageParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgBuilderLanguageParser#funcall}.
	 * @param ctx the parse tree
	 */
	void enterFuncall(AlgBuilderLanguageParser.FuncallContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgBuilderLanguageParser#funcall}.
	 * @param ctx the parse tree
	 */
	void exitFuncall(AlgBuilderLanguageParser.FuncallContext ctx);
	/**
	 * Enter a parse tree produced by {@link AlgBuilderLanguageParser#objcall}.
	 * @param ctx the parse tree
	 */
	void enterObjcall(AlgBuilderLanguageParser.ObjcallContext ctx);
	/**
	 * Exit a parse tree produced by {@link AlgBuilderLanguageParser#objcall}.
	 * @param ctx the parse tree
	 */
	void exitObjcall(AlgBuilderLanguageParser.ObjcallContext ctx);
}