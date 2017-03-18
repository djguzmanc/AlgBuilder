// Generated from AlgBuilderLanguage.g by ANTLR 4.5.3
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AlgBuilderLanguageParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AlgBuilderLanguageVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AlgBuilderLanguageParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(AlgBuilderLanguageParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgBuilderLanguageParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(AlgBuilderLanguageParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgBuilderLanguageParser#mainprocess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainprocess(AlgBuilderLanguageParser.MainprocessContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgBuilderLanguageParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(AlgBuilderLanguageParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgBuilderLanguageParser#returnstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnstatement(AlgBuilderLanguageParser.ReturnstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgBuilderLanguageParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(AlgBuilderLanguageParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgBuilderLanguageParser#forloop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForloop(AlgBuilderLanguageParser.ForloopContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgBuilderLanguageParser#whileloop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileloop(AlgBuilderLanguageParser.WhileloopContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgBuilderLanguageParser#ifstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfstatement(AlgBuilderLanguageParser.IfstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgBuilderLanguageParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(AlgBuilderLanguageParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgBuilderLanguageParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(AlgBuilderLanguageParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgBuilderLanguageParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(AlgBuilderLanguageParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgBuilderLanguageParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(AlgBuilderLanguageParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgBuilderLanguageParser#funcall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncall(AlgBuilderLanguageParser.FuncallContext ctx);
	/**
	 * Visit a parse tree produced by {@link AlgBuilderLanguageParser#objcall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjcall(AlgBuilderLanguageParser.ObjcallContext ctx);
}