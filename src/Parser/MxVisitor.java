// Generated from //wsl$/Ubuntu/home/ubospica/courses/compiler/CompilerStorm/src/Parser\Mx.g4 by ANTLR 4.9.1
package Parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MxParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(MxParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#funcDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDefinition(MxParser.FuncDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#constructorDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstructorDefinition(MxParser.ConstructorDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(MxParser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(MxParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varDefinitionStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDefinitionStmt(MxParser.VarDefinitionStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDefinition(MxParser.VarDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#varDefinitionSub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDefinitionSub(MxParser.VarDefinitionSubContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#classDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDefinition(MxParser.ClassDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MxParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MxParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(MxParser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(MxParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#newExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExpression(MxParser.NewExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#newArraySize}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewArraySize(MxParser.NewArraySizeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#errNewArraySize}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitErrNewArraySize(MxParser.ErrNewArraySizeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(MxParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#typeSub}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeSub(MxParser.TypeSubContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnType(MxParser.ReturnTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(MxParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxParser#lambda}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambda(MxParser.LambdaContext ctx);
}