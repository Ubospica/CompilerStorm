// Generated from //wsl$/Ubuntu/home/ubospica/courses/compiler/CompilerStorm/src/Parser\Mx.g4 by ANTLR 4.9.1
package Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxParser}.
 */
public interface MxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(MxParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(MxParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#funcDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFuncDefinition(MxParser.FuncDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#funcDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFuncDefinition(MxParser.FuncDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#constructorDefinition}.
	 * @param ctx the parse tree
	 */
	void enterConstructorDefinition(MxParser.ConstructorDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#constructorDefinition}.
	 * @param ctx the parse tree
	 */
	void exitConstructorDefinition(MxParser.ConstructorDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(MxParser.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(MxParser.ParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(MxParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(MxParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varDefinitionStmt}.
	 * @param ctx the parse tree
	 */
	void enterVarDefinitionStmt(MxParser.VarDefinitionStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varDefinitionStmt}.
	 * @param ctx the parse tree
	 */
	void exitVarDefinitionStmt(MxParser.VarDefinitionStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varDefinition}.
	 * @param ctx the parse tree
	 */
	void enterVarDefinition(MxParser.VarDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varDefinition}.
	 * @param ctx the parse tree
	 */
	void exitVarDefinition(MxParser.VarDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#varDefinitionSub}.
	 * @param ctx the parse tree
	 */
	void enterVarDefinitionSub(MxParser.VarDefinitionSubContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#varDefinitionSub}.
	 * @param ctx the parse tree
	 */
	void exitVarDefinitionSub(MxParser.VarDefinitionSubContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#classDefinition}.
	 * @param ctx the parse tree
	 */
	void enterClassDefinition(MxParser.ClassDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#classDefinition}.
	 * @param ctx the parse tree
	 */
	void exitClassDefinition(MxParser.ClassDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(MxParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(MxParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(MxParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(MxParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(MxParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(MxParser.PrimaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(MxParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(MxParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#newExpression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpression(MxParser.NewExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#newExpression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpression(MxParser.NewExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#newArraySize}.
	 * @param ctx the parse tree
	 */
	void enterNewArraySize(MxParser.NewArraySizeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#newArraySize}.
	 * @param ctx the parse tree
	 */
	void exitNewArraySize(MxParser.NewArraySizeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#errNewArraySize}.
	 * @param ctx the parse tree
	 */
	void enterErrNewArraySize(MxParser.ErrNewArraySizeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#errNewArraySize}.
	 * @param ctx the parse tree
	 */
	void exitErrNewArraySize(MxParser.ErrNewArraySizeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(MxParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(MxParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#typeSub}.
	 * @param ctx the parse tree
	 */
	void enterTypeSub(MxParser.TypeSubContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#typeSub}.
	 * @param ctx the parse tree
	 */
	void exitTypeSub(MxParser.TypeSubContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(MxParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(MxParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(MxParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(MxParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxParser#lambda}.
	 * @param ctx the parse tree
	 */
	void enterLambda(MxParser.LambdaContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxParser#lambda}.
	 * @param ctx the parse tree
	 */
	void exitLambda(MxParser.LambdaContext ctx);
}