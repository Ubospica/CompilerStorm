// Generated from //wsl$/Ubuntu/home/ubospica/courses/compiler/CompilerStorm/src/Parser\Mx.g4 by ANTLR 4.9.1
package Parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, IntegerLiteral=34, StringLiteral=35, BooleanLiteral=36, 
		NullLiteral=37, Int=38, Bool=39, String=40, Null=41, Void=42, True=43, 
		False=44, If=45, Else=46, For=47, While=48, Break=49, Continue=50, Return=51, 
		New=52, Class=53, This=54, Identifier=55, Whitespace=56, NewLine=57, BlockComment=58, 
		LineComment=59;
	public static final int
		RULE_program = 0, RULE_block = 1, RULE_funcDefinition = 2, RULE_constructorDefinition = 3, 
		RULE_paramList = 4, RULE_param = 5, RULE_varDefinitionStmt = 6, RULE_varDefinition = 7, 
		RULE_varDefinitionSub = 8, RULE_classDefinition = 9, RULE_statement = 10, 
		RULE_expression = 11, RULE_primary = 12, RULE_expressionList = 13, RULE_newExpression = 14, 
		RULE_newArraySize = 15, RULE_errNewArraySize = 16, RULE_type = 17, RULE_typeSub = 18, 
		RULE_returnType = 19, RULE_literal = 20, RULE_lambda = 21;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "block", "funcDefinition", "constructorDefinition", "paramList", 
			"param", "varDefinitionStmt", "varDefinition", "varDefinitionSub", "classDefinition", 
			"statement", "expression", "primary", "expressionList", "newExpression", 
			"newArraySize", "errNewArraySize", "type", "typeSub", "returnType", "literal", 
			"lambda"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "'('", "')'", "','", "';'", "'='", "'++'", "'--'", 
			"'.'", "'['", "']'", "'+'", "'-'", "'!'", "'~'", "'*'", "'/'", "'%'", 
			"'<<'", "'>>'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'&'", 
			"'^'", "'|'", "'&&'", "'||'", "'->'", null, null, null, null, "'int'", 
			"'bool'", "'string'", "'null'", "'void'", "'true'", "'false'", "'if'", 
			"'else'", "'for'", "'while'", "'break'", "'continue'", "'return'", "'new'", 
			"'class'", "'this'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, "IntegerLiteral", 
			"StringLiteral", "BooleanLiteral", "NullLiteral", "Int", "Bool", "String", 
			"Null", "Void", "True", "False", "If", "Else", "For", "While", "Break", 
			"Continue", "Return", "New", "Class", "This", "Identifier", "Whitespace", 
			"NewLine", "BlockComment", "LineComment"
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

	@Override
	public String getGrammarFileName() { return "Mx.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MxParser.EOF, 0); }
		public List<FuncDefinitionContext> funcDefinition() {
			return getRuleContexts(FuncDefinitionContext.class);
		}
		public FuncDefinitionContext funcDefinition(int i) {
			return getRuleContext(FuncDefinitionContext.class,i);
		}
		public List<ClassDefinitionContext> classDefinition() {
			return getRuleContexts(ClassDefinitionContext.class);
		}
		public ClassDefinitionContext classDefinition(int i) {
			return getRuleContext(ClassDefinitionContext.class,i);
		}
		public List<VarDefinitionStmtContext> varDefinitionStmt() {
			return getRuleContexts(VarDefinitionStmtContext.class);
		}
		public VarDefinitionStmtContext varDefinitionStmt(int i) {
			return getRuleContext(VarDefinitionStmtContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Void) | (1L << Class) | (1L << Identifier))) != 0)) {
				{
				setState(47);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(44);
					funcDefinition();
					}
					break;
				case 2:
					{
					{
					setState(45);
					classDefinition();
					}
					}
					break;
				case 3:
					{
					{
					setState(46);
					varDefinitionStmt();
					}
					}
					break;
				}
				}
				setState(51);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(52);
			match(EOF);
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

	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(T__0);
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << IntegerLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << NullLiteral) | (1L << Int) | (1L << Bool) | (1L << String) | (1L << If) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << New) | (1L << This) | (1L << Identifier))) != 0)) {
				{
				{
				setState(55);
				statement();
				}
				}
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(61);
			match(T__1);
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

	public static class FuncDefinitionContext extends ParserRuleContext {
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FuncDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterFuncDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitFuncDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitFuncDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDefinitionContext funcDefinition() throws RecognitionException {
		FuncDefinitionContext _localctx = new FuncDefinitionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_funcDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			returnType();
			setState(64);
			match(Identifier);
			setState(65);
			match(T__2);
			setState(66);
			paramList();
			setState(67);
			match(T__3);
			setState(68);
			block();
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

	public static class ConstructorDefinitionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ConstructorDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterConstructorDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitConstructorDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitConstructorDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstructorDefinitionContext constructorDefinition() throws RecognitionException {
		ConstructorDefinitionContext _localctx = new ConstructorDefinitionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_constructorDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(Identifier);
			setState(71);
			match(T__2);
			setState(72);
			match(T__3);
			setState(73);
			block();
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

	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitParamList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(75);
				param();
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(76);
					match(T__4);
					setState(77);
					param();
					}
					}
					setState(82);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
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

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			type();
			setState(86);
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

	public static class VarDefinitionStmtContext extends ParserRuleContext {
		public VarDefinitionContext varDefinition() {
			return getRuleContext(VarDefinitionContext.class,0);
		}
		public VarDefinitionStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDefinitionStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVarDefinitionStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVarDefinitionStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVarDefinitionStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefinitionStmtContext varDefinitionStmt() throws RecognitionException {
		VarDefinitionStmtContext _localctx = new VarDefinitionStmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_varDefinitionStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			varDefinition();
			setState(89);
			match(T__5);
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

	public static class VarDefinitionContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<VarDefinitionSubContext> varDefinitionSub() {
			return getRuleContexts(VarDefinitionSubContext.class);
		}
		public VarDefinitionSubContext varDefinitionSub(int i) {
			return getRuleContext(VarDefinitionSubContext.class,i);
		}
		public VarDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVarDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVarDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVarDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefinitionContext varDefinition() throws RecognitionException {
		VarDefinitionContext _localctx = new VarDefinitionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_varDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			type();
			setState(92);
			varDefinitionSub();
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(93);
				match(T__4);
				setState(94);
				varDefinitionSub();
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class VarDefinitionSubContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarDefinitionSubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDefinitionSub; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterVarDefinitionSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitVarDefinitionSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitVarDefinitionSub(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefinitionSubContext varDefinitionSub() throws RecognitionException {
		VarDefinitionSubContext _localctx = new VarDefinitionSubContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_varDefinitionSub);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(Identifier);
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(101);
				match(T__6);
				setState(102);
				expression(0);
				}
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

	public static class ClassDefinitionContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public List<FuncDefinitionContext> funcDefinition() {
			return getRuleContexts(FuncDefinitionContext.class);
		}
		public FuncDefinitionContext funcDefinition(int i) {
			return getRuleContext(FuncDefinitionContext.class,i);
		}
		public List<ConstructorDefinitionContext> constructorDefinition() {
			return getRuleContexts(ConstructorDefinitionContext.class);
		}
		public ConstructorDefinitionContext constructorDefinition(int i) {
			return getRuleContext(ConstructorDefinitionContext.class,i);
		}
		public List<VarDefinitionStmtContext> varDefinitionStmt() {
			return getRuleContexts(VarDefinitionStmtContext.class);
		}
		public VarDefinitionStmtContext varDefinitionStmt(int i) {
			return getRuleContext(VarDefinitionStmtContext.class,i);
		}
		public ClassDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterClassDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitClassDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitClassDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefinitionContext classDefinition() throws RecognitionException {
		ClassDefinitionContext _localctx = new ClassDefinitionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_classDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(Class);
			setState(106);
			match(Identifier);
			setState(107);
			match(T__0);
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Void) | (1L << Identifier))) != 0)) {
				{
				setState(111);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(108);
					funcDefinition();
					}
					break;
				case 2:
					{
					setState(109);
					constructorDefinition();
					}
					break;
				case 3:
					{
					{
					setState(110);
					varDefinitionStmt();
					}
					}
					break;
				}
				}
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(116);
			match(T__1);
			setState(117);
			match(T__5);
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
		public StatementContext trueStmt;
		public StatementContext falseStmt;
		public StatementContext loopStmt;
		public ExpressionContext cond;
		public ExpressionContext incr;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public VarDefinitionContext varDefinition() {
			return getRuleContext(VarDefinitionContext.class,0);
		}
		public TerminalNode If() { return getToken(MxParser.If, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxParser.Else, 0); }
		public TerminalNode While() { return getToken(MxParser.While, 0); }
		public TerminalNode For() { return getToken(MxParser.For, 0); }
		public TerminalNode Break() { return getToken(MxParser.Break, 0); }
		public TerminalNode Continue() { return getToken(MxParser.Continue, 0); }
		public TerminalNode Return() { return getToken(MxParser.Return, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_statement);
		int _la;
		try {
			setState(165);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(119);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(120);
				varDefinition();
				setState(121);
				match(T__5);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(123);
				match(If);
				setState(124);
				match(T__2);
				setState(125);
				expression(0);
				setState(126);
				match(T__3);
				setState(127);
				((StatementContext)_localctx).trueStmt = statement();
				setState(130);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(128);
					match(Else);
					setState(129);
					((StatementContext)_localctx).falseStmt = statement();
					}
					break;
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(132);
				match(While);
				setState(133);
				match(T__2);
				setState(134);
				expression(0);
				setState(135);
				match(T__3);
				setState(136);
				((StatementContext)_localctx).loopStmt = statement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(138);
				match(For);
				setState(139);
				match(T__2);
				setState(142);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(140);
					expression(0);
					}
					break;
				case 2:
					{
					setState(141);
					varDefinition();
					}
					break;
				}
				setState(144);
				match(T__5);
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << IntegerLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << NullLiteral) | (1L << New) | (1L << This) | (1L << Identifier))) != 0)) {
					{
					setState(145);
					((StatementContext)_localctx).cond = expression(0);
					}
				}

				setState(148);
				match(T__5);
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << IntegerLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << NullLiteral) | (1L << New) | (1L << This) | (1L << Identifier))) != 0)) {
					{
					setState(149);
					((StatementContext)_localctx).incr = expression(0);
					}
				}

				setState(152);
				match(T__3);
				setState(153);
				((StatementContext)_localctx).loopStmt = statement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(154);
				_la = _input.LA(1);
				if ( !(_la==Break || _la==Continue) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(155);
				match(T__5);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(156);
				match(Return);
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << IntegerLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << NullLiteral) | (1L << New) | (1L << This) | (1L << Identifier))) != 0)) {
					{
					setState(157);
					expression(0);
					}
				}

				setState(160);
				match(T__5);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(161);
				expression(0);
				setState(162);
				match(T__5);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(164);
				match(T__5);
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

	public static class ExpressionContext extends ParserRuleContext {
		public Token op;
		public ExpressionContext index;
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public LambdaContext lambda() {
			return getRuleContext(LambdaContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public NewExpressionContext newExpression() {
			return getRuleContext(NewExpressionContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IntegerLiteral:
			case StringLiteral:
			case BooleanLiteral:
			case NullLiteral:
			case This:
			case Identifier:
				{
				setState(168);
				primary();
				}
				break;
			case T__10:
				{
				setState(169);
				lambda();
				}
				break;
			case T__2:
				{
				setState(170);
				match(T__2);
				setState(171);
				expression(0);
				setState(172);
				match(T__3);
				}
				break;
			case T__7:
			case T__8:
				{
				setState(174);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(175);
				expression(15);
				}
				break;
			case T__12:
			case T__13:
			case T__14:
			case T__15:
				{
				setState(176);
				((ExpressionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15))) != 0)) ) {
					((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(177);
				expression(14);
				}
				break;
			case New:
				{
				setState(178);
				newExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(234);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(232);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(181);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(182);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(183);
						expression(13);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(184);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(185);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__12 || _la==T__13) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(186);
						expression(12);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(187);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(188);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__19 || _la==T__20) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(189);
						expression(11);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(190);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(191);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(192);
						expression(10);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(193);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(194);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__25 || _la==T__26) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(195);
						expression(9);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(196);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(197);
						((ExpressionContext)_localctx).op = match(T__27);
						setState(198);
						expression(8);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(199);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(200);
						((ExpressionContext)_localctx).op = match(T__28);
						setState(201);
						expression(7);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(202);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(203);
						((ExpressionContext)_localctx).op = match(T__29);
						setState(204);
						expression(6);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(205);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(206);
						((ExpressionContext)_localctx).op = match(T__30);
						setState(207);
						expression(5);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(208);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(209);
						((ExpressionContext)_localctx).op = match(T__31);
						setState(210);
						expression(4);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(211);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(212);
						((ExpressionContext)_localctx).op = match(T__6);
						setState(213);
						expression(2);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(214);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(215);
						((ExpressionContext)_localctx).op = match(T__4);
						setState(216);
						expression(2);
						}
						break;
					case 13:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(217);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(218);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__8) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 14:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(219);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(220);
						match(T__9);
						setState(221);
						match(Identifier);
						}
						break;
					case 15:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(222);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(223);
						match(T__2);
						setState(224);
						expressionList();
						setState(225);
						match(T__3);
						}
						break;
					case 16:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(227);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(228);
						match(T__10);
						setState(229);
						((ExpressionContext)_localctx).index = expression(0);
						setState(230);
						match(T__11);
						}
						break;
					}
					} 
				}
				setState(236);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode This() { return getToken(MxParser.This, 0); }
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_primary);
		try {
			setState(240);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(237);
				match(Identifier);
				}
				break;
			case IntegerLiteral:
			case StringLiteral:
			case BooleanLiteral:
			case NullLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(238);
				literal();
				}
				break;
			case This:
				enterOuterAlt(_localctx, 3);
				{
				setState(239);
				match(This);
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

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expressionList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(242);
				expression(0);
				setState(247);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(243);
						match(T__4);
						setState(244);
						expression(0);
						}
						} 
					}
					setState(249);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
				}
				}
				break;
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

	public static class NewExpressionContext extends ParserRuleContext {
		public TerminalNode New() { return getToken(MxParser.New, 0); }
		public TypeSubContext typeSub() {
			return getRuleContext(TypeSubContext.class,0);
		}
		public ErrNewArraySizeContext errNewArraySize() {
			return getRuleContext(ErrNewArraySizeContext.class,0);
		}
		public NewArraySizeContext newArraySize() {
			return getRuleContext(NewArraySizeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public NewExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNewExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNewExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNewExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewExpressionContext newExpression() throws RecognitionException {
		NewExpressionContext _localctx = new NewExpressionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_newExpression);
		try {
			setState(266);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(252);
				match(New);
				setState(253);
				typeSub();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(254);
				match(New);
				setState(255);
				typeSub();
				setState(258);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(256);
					errNewArraySize();
					}
					break;
				case 2:
					{
					setState(257);
					newArraySize();
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(260);
				match(New);
				setState(261);
				match(Identifier);
				setState(264);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(262);
					match(T__2);
					setState(263);
					match(T__3);
					}
					break;
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

	public static class NewArraySizeContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public NewArraySizeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newArraySize; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterNewArraySize(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitNewArraySize(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitNewArraySize(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NewArraySizeContext newArraySize() throws RecognitionException {
		NewArraySizeContext _localctx = new NewArraySizeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_newArraySize);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(268);
					match(T__10);
					setState(269);
					expression(0);
					setState(270);
					match(T__11);
					}
					} 
				}
				setState(276);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			}
			setState(281);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(277);
					match(T__10);
					setState(278);
					match(T__11);
					}
					} 
				}
				setState(283);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
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

	public static class ErrNewArraySizeContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ErrNewArraySizeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_errNewArraySize; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterErrNewArraySize(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitErrNewArraySize(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitErrNewArraySize(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ErrNewArraySizeContext errNewArraySize() throws RecognitionException {
		ErrNewArraySizeContext _localctx = new ErrNewArraySizeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_errNewArraySize);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(284);
					match(T__10);
					setState(285);
					expression(0);
					setState(286);
					match(T__11);
					}
					} 
				}
				setState(292);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}
			setState(295); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(293);
					match(T__10);
					setState(294);
					match(T__11);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(297); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(303); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(299);
					match(T__10);
					setState(300);
					expression(0);
					setState(301);
					match(T__11);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(305); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
		public TypeSubContext typeSub() {
			return getRuleContext(TypeSubContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_type);
		int _la;
		try {
			setState(315);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(307);
				typeSub();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(308);
				typeSub();
				setState(311); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(309);
					match(T__10);
					setState(310);
					match(T__11);
					}
					}
					setState(313); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__10 );
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

	public static class TypeSubContext extends ParserRuleContext {
		public TerminalNode Bool() { return getToken(MxParser.Bool, 0); }
		public TerminalNode Int() { return getToken(MxParser.Int, 0); }
		public TerminalNode String() { return getToken(MxParser.String, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public TypeSubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeSub; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterTypeSub(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitTypeSub(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitTypeSub(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeSubContext typeSub() throws RecognitionException {
		TypeSubContext _localctx = new TypeSubContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_typeSub);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	public static class ReturnTypeContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode Void() { return getToken(MxParser.Void, 0); }
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterReturnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitReturnType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitReturnType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_returnType);
		try {
			setState(321);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Int:
			case Bool:
			case String:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(319);
				type();
				}
				break;
			case Void:
				enterOuterAlt(_localctx, 2);
				{
				setState(320);
				match(Void);
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

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode IntegerLiteral() { return getToken(MxParser.IntegerLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(MxParser.StringLiteral, 0); }
		public TerminalNode BooleanLiteral() { return getToken(MxParser.BooleanLiteral, 0); }
		public TerminalNode NullLiteral() { return getToken(MxParser.NullLiteral, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntegerLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << NullLiteral))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
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

	public static class LambdaContext extends ParserRuleContext {
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public LambdaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambda; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).enterLambda(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxListener ) ((MxListener)listener).exitLambda(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxVisitor ) return ((MxVisitor<? extends T>)visitor).visitLambda(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaContext lambda() throws RecognitionException {
		LambdaContext _localctx = new LambdaContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_lambda);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			match(T__10);
			setState(326);
			match(T__27);
			setState(327);
			match(T__11);
			setState(328);
			match(T__2);
			setState(329);
			paramList();
			setState(330);
			match(T__3);
			setState(331);
			match(T__32);
			setState(332);
			block();
			setState(333);
			expressionList();
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 12);
		case 1:
			return precpred(_ctx, 11);
		case 2:
			return precpred(_ctx, 10);
		case 3:
			return precpred(_ctx, 9);
		case 4:
			return precpred(_ctx, 8);
		case 5:
			return precpred(_ctx, 7);
		case 6:
			return precpred(_ctx, 6);
		case 7:
			return precpred(_ctx, 5);
		case 8:
			return precpred(_ctx, 4);
		case 9:
			return precpred(_ctx, 3);
		case 10:
			return precpred(_ctx, 2);
		case 11:
			return precpred(_ctx, 1);
		case 12:
			return precpred(_ctx, 20);
		case 13:
			return precpred(_ctx, 18);
		case 14:
			return precpred(_ctx, 17);
		case 15:
			return precpred(_ctx, 16);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3=\u0152\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\7\2\62"+
		"\n\2\f\2\16\2\65\13\2\3\2\3\2\3\3\3\3\7\3;\n\3\f\3\16\3>\13\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\7\6Q\n\6"+
		"\f\6\16\6T\13\6\5\6V\n\6\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\7\tb"+
		"\n\t\f\t\16\te\13\t\3\n\3\n\3\n\5\nj\n\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\7\13r\n\13\f\13\16\13u\13\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\5\f\u0085\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\5\f\u0091\n\f\3\f\3\f\5\f\u0095\n\f\3\f\3\f\5\f\u0099\n\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\5\f\u00a1\n\f\3\f\3\f\3\f\3\f\3\f\5\f\u00a8\n\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00b6\n\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u00eb\n\r\f\r\16"+
		"\r\u00ee\13\r\3\16\3\16\3\16\5\16\u00f3\n\16\3\17\3\17\3\17\7\17\u00f8"+
		"\n\17\f\17\16\17\u00fb\13\17\5\17\u00fd\n\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\5\20\u0105\n\20\3\20\3\20\3\20\3\20\5\20\u010b\n\20\5\20\u010d\n"+
		"\20\3\21\3\21\3\21\3\21\7\21\u0113\n\21\f\21\16\21\u0116\13\21\3\21\3"+
		"\21\7\21\u011a\n\21\f\21\16\21\u011d\13\21\3\22\3\22\3\22\3\22\7\22\u0123"+
		"\n\22\f\22\16\22\u0126\13\22\3\22\3\22\6\22\u012a\n\22\r\22\16\22\u012b"+
		"\3\22\3\22\3\22\3\22\6\22\u0132\n\22\r\22\16\22\u0133\3\23\3\23\3\23\3"+
		"\23\6\23\u013a\n\23\r\23\16\23\u013b\5\23\u013e\n\23\3\24\3\24\3\25\3"+
		"\25\5\25\u0144\n\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\2\3\30\30\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&"+
		"(*,\2\f\3\2\63\64\3\2\n\13\3\2\17\22\3\2\23\25\3\2\17\20\3\2\26\27\3\2"+
		"\30\33\3\2\34\35\4\2(*99\3\2$\'\2\u0179\2\63\3\2\2\2\48\3\2\2\2\6A\3\2"+
		"\2\2\bH\3\2\2\2\nU\3\2\2\2\fW\3\2\2\2\16Z\3\2\2\2\20]\3\2\2\2\22f\3\2"+
		"\2\2\24k\3\2\2\2\26\u00a7\3\2\2\2\30\u00b5\3\2\2\2\32\u00f2\3\2\2\2\34"+
		"\u00fc\3\2\2\2\36\u010c\3\2\2\2 \u0114\3\2\2\2\"\u0124\3\2\2\2$\u013d"+
		"\3\2\2\2&\u013f\3\2\2\2(\u0143\3\2\2\2*\u0145\3\2\2\2,\u0147\3\2\2\2."+
		"\62\5\6\4\2/\62\5\24\13\2\60\62\5\16\b\2\61.\3\2\2\2\61/\3\2\2\2\61\60"+
		"\3\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\66\3\2\2\2\65\63"+
		"\3\2\2\2\66\67\7\2\2\3\67\3\3\2\2\28<\7\3\2\29;\5\26\f\2:9\3\2\2\2;>\3"+
		"\2\2\2<:\3\2\2\2<=\3\2\2\2=?\3\2\2\2><\3\2\2\2?@\7\4\2\2@\5\3\2\2\2AB"+
		"\5(\25\2BC\79\2\2CD\7\5\2\2DE\5\n\6\2EF\7\6\2\2FG\5\4\3\2G\7\3\2\2\2H"+
		"I\79\2\2IJ\7\5\2\2JK\7\6\2\2KL\5\4\3\2L\t\3\2\2\2MR\5\f\7\2NO\7\7\2\2"+
		"OQ\5\f\7\2PN\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2SV\3\2\2\2TR\3\2\2\2"+
		"UM\3\2\2\2UV\3\2\2\2V\13\3\2\2\2WX\5$\23\2XY\79\2\2Y\r\3\2\2\2Z[\5\20"+
		"\t\2[\\\7\b\2\2\\\17\3\2\2\2]^\5$\23\2^c\5\22\n\2_`\7\7\2\2`b\5\22\n\2"+
		"a_\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2d\21\3\2\2\2ec\3\2\2\2fi\79\2"+
		"\2gh\7\t\2\2hj\5\30\r\2ig\3\2\2\2ij\3\2\2\2j\23\3\2\2\2kl\7\67\2\2lm\7"+
		"9\2\2ms\7\3\2\2nr\5\6\4\2or\5\b\5\2pr\5\16\b\2qn\3\2\2\2qo\3\2\2\2qp\3"+
		"\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2tv\3\2\2\2us\3\2\2\2vw\7\4\2\2wx\7"+
		"\b\2\2x\25\3\2\2\2y\u00a8\5\4\3\2z{\5\20\t\2{|\7\b\2\2|\u00a8\3\2\2\2"+
		"}~\7/\2\2~\177\7\5\2\2\177\u0080\5\30\r\2\u0080\u0081\7\6\2\2\u0081\u0084"+
		"\5\26\f\2\u0082\u0083\7\60\2\2\u0083\u0085\5\26\f\2\u0084\u0082\3\2\2"+
		"\2\u0084\u0085\3\2\2\2\u0085\u00a8\3\2\2\2\u0086\u0087\7\62\2\2\u0087"+
		"\u0088\7\5\2\2\u0088\u0089\5\30\r\2\u0089\u008a\7\6\2\2\u008a\u008b\5"+
		"\26\f\2\u008b\u00a8\3\2\2\2\u008c\u008d\7\61\2\2\u008d\u0090\7\5\2\2\u008e"+
		"\u0091\5\30\r\2\u008f\u0091\5\20\t\2\u0090\u008e\3\2\2\2\u0090\u008f\3"+
		"\2\2\2\u0090\u0091\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\7\b\2\2\u0093"+
		"\u0095\5\30\r\2\u0094\u0093\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3"+
		"\2\2\2\u0096\u0098\7\b\2\2\u0097\u0099\5\30\r\2\u0098\u0097\3\2\2\2\u0098"+
		"\u0099\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\7\6\2\2\u009b\u00a8\5\26"+
		"\f\2\u009c\u009d\t\2\2\2\u009d\u00a8\7\b\2\2\u009e\u00a0\7\65\2\2\u009f"+
		"\u00a1\5\30\r\2\u00a0\u009f\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\3"+
		"\2\2\2\u00a2\u00a8\7\b\2\2\u00a3\u00a4\5\30\r\2\u00a4\u00a5\7\b\2\2\u00a5"+
		"\u00a8\3\2\2\2\u00a6\u00a8\7\b\2\2\u00a7y\3\2\2\2\u00a7z\3\2\2\2\u00a7"+
		"}\3\2\2\2\u00a7\u0086\3\2\2\2\u00a7\u008c\3\2\2\2\u00a7\u009c\3\2\2\2"+
		"\u00a7\u009e\3\2\2\2\u00a7\u00a3\3\2\2\2\u00a7\u00a6\3\2\2\2\u00a8\27"+
		"\3\2\2\2\u00a9\u00aa\b\r\1\2\u00aa\u00b6\5\32\16\2\u00ab\u00b6\5,\27\2"+
		"\u00ac\u00ad\7\5\2\2\u00ad\u00ae\5\30\r\2\u00ae\u00af\7\6\2\2\u00af\u00b6"+
		"\3\2\2\2\u00b0\u00b1\t\3\2\2\u00b1\u00b6\5\30\r\21\u00b2\u00b3\t\4\2\2"+
		"\u00b3\u00b6\5\30\r\20\u00b4\u00b6\5\36\20\2\u00b5\u00a9\3\2\2\2\u00b5"+
		"\u00ab\3\2\2\2\u00b5\u00ac\3\2\2\2\u00b5\u00b0\3\2\2\2\u00b5\u00b2\3\2"+
		"\2\2\u00b5\u00b4\3\2\2\2\u00b6\u00ec\3\2\2\2\u00b7\u00b8\f\16\2\2\u00b8"+
		"\u00b9\t\5\2\2\u00b9\u00eb\5\30\r\17\u00ba\u00bb\f\r\2\2\u00bb\u00bc\t"+
		"\6\2\2\u00bc\u00eb\5\30\r\16\u00bd\u00be\f\f\2\2\u00be\u00bf\t\7\2\2\u00bf"+
		"\u00eb\5\30\r\r\u00c0\u00c1\f\13\2\2\u00c1\u00c2\t\b\2\2\u00c2\u00eb\5"+
		"\30\r\f\u00c3\u00c4\f\n\2\2\u00c4\u00c5\t\t\2\2\u00c5\u00eb\5\30\r\13"+
		"\u00c6\u00c7\f\t\2\2\u00c7\u00c8\7\36\2\2\u00c8\u00eb\5\30\r\n\u00c9\u00ca"+
		"\f\b\2\2\u00ca\u00cb\7\37\2\2\u00cb\u00eb\5\30\r\t\u00cc\u00cd\f\7\2\2"+
		"\u00cd\u00ce\7 \2\2\u00ce\u00eb\5\30\r\b\u00cf\u00d0\f\6\2\2\u00d0\u00d1"+
		"\7!\2\2\u00d1\u00eb\5\30\r\7\u00d2\u00d3\f\5\2\2\u00d3\u00d4\7\"\2\2\u00d4"+
		"\u00eb\5\30\r\6\u00d5\u00d6\f\4\2\2\u00d6\u00d7\7\t\2\2\u00d7\u00eb\5"+
		"\30\r\4\u00d8\u00d9\f\3\2\2\u00d9\u00da\7\7\2\2\u00da\u00eb\5\30\r\4\u00db"+
		"\u00dc\f\26\2\2\u00dc\u00eb\t\3\2\2\u00dd\u00de\f\24\2\2\u00de\u00df\7"+
		"\f\2\2\u00df\u00eb\79\2\2\u00e0\u00e1\f\23\2\2\u00e1\u00e2\7\5\2\2\u00e2"+
		"\u00e3\5\34\17\2\u00e3\u00e4\7\6\2\2\u00e4\u00eb\3\2\2\2\u00e5\u00e6\f"+
		"\22\2\2\u00e6\u00e7\7\r\2\2\u00e7\u00e8\5\30\r\2\u00e8\u00e9\7\16\2\2"+
		"\u00e9\u00eb\3\2\2\2\u00ea\u00b7\3\2\2\2\u00ea\u00ba\3\2\2\2\u00ea\u00bd"+
		"\3\2\2\2\u00ea\u00c0\3\2\2\2\u00ea\u00c3\3\2\2\2\u00ea\u00c6\3\2\2\2\u00ea"+
		"\u00c9\3\2\2\2\u00ea\u00cc\3\2\2\2\u00ea\u00cf\3\2\2\2\u00ea\u00d2\3\2"+
		"\2\2\u00ea\u00d5\3\2\2\2\u00ea\u00d8\3\2\2\2\u00ea\u00db\3\2\2\2\u00ea"+
		"\u00dd\3\2\2\2\u00ea\u00e0\3\2\2\2\u00ea\u00e5\3\2\2\2\u00eb\u00ee\3\2"+
		"\2\2\u00ec\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\31\3\2\2\2\u00ee\u00ec"+
		"\3\2\2\2\u00ef\u00f3\79\2\2\u00f0\u00f3\5*\26\2\u00f1\u00f3\78\2\2\u00f2"+
		"\u00ef\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f2\u00f1\3\2\2\2\u00f3\33\3\2\2"+
		"\2\u00f4\u00f9\5\30\r\2\u00f5\u00f6\7\7\2\2\u00f6\u00f8\5\30\r\2\u00f7"+
		"\u00f5\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2"+
		"\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc\u00f4\3\2\2\2\u00fc"+
		"\u00fd\3\2\2\2\u00fd\35\3\2\2\2\u00fe\u00ff\7\66\2\2\u00ff\u010d\5&\24"+
		"\2\u0100\u0101\7\66\2\2\u0101\u0104\5&\24\2\u0102\u0105\5\"\22\2\u0103"+
		"\u0105\5 \21\2\u0104\u0102\3\2\2\2\u0104\u0103\3\2\2\2\u0105\u010d\3\2"+
		"\2\2\u0106\u0107\7\66\2\2\u0107\u010a\79\2\2\u0108\u0109\7\5\2\2\u0109"+
		"\u010b\7\6\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010d\3\2"+
		"\2\2\u010c\u00fe\3\2\2\2\u010c\u0100\3\2\2\2\u010c\u0106\3\2\2\2\u010d"+
		"\37\3\2\2\2\u010e\u010f\7\r\2\2\u010f\u0110\5\30\r\2\u0110\u0111\7\16"+
		"\2\2\u0111\u0113\3\2\2\2\u0112\u010e\3\2\2\2\u0113\u0116\3\2\2\2\u0114"+
		"\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u011b\3\2\2\2\u0116\u0114\3\2"+
		"\2\2\u0117\u0118\7\r\2\2\u0118\u011a\7\16\2\2\u0119\u0117\3\2\2\2\u011a"+
		"\u011d\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c!\3\2\2\2"+
		"\u011d\u011b\3\2\2\2\u011e\u011f\7\r\2\2\u011f\u0120\5\30\r\2\u0120\u0121"+
		"\7\16\2\2\u0121\u0123\3\2\2\2\u0122\u011e\3\2\2\2\u0123\u0126\3\2\2\2"+
		"\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0129\3\2\2\2\u0126\u0124"+
		"\3\2\2\2\u0127\u0128\7\r\2\2\u0128\u012a\7\16\2\2\u0129\u0127\3\2\2\2"+
		"\u012a\u012b\3\2\2\2\u012b\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u0131"+
		"\3\2\2\2\u012d\u012e\7\r\2\2\u012e\u012f\5\30\r\2\u012f\u0130\7\16\2\2"+
		"\u0130\u0132\3\2\2\2\u0131\u012d\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0131"+
		"\3\2\2\2\u0133\u0134\3\2\2\2\u0134#\3\2\2\2\u0135\u013e\5&\24\2\u0136"+
		"\u0139\5&\24\2\u0137\u0138\7\r\2\2\u0138\u013a\7\16\2\2\u0139\u0137\3"+
		"\2\2\2\u013a\u013b\3\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c"+
		"\u013e\3\2\2\2\u013d\u0135\3\2\2\2\u013d\u0136\3\2\2\2\u013e%\3\2\2\2"+
		"\u013f\u0140\t\n\2\2\u0140\'\3\2\2\2\u0141\u0144\5$\23\2\u0142\u0144\7"+
		",\2\2\u0143\u0141\3\2\2\2\u0143\u0142\3\2\2\2\u0144)\3\2\2\2\u0145\u0146"+
		"\t\13\2\2\u0146+\3\2\2\2\u0147\u0148\7\r\2\2\u0148\u0149\7\36\2\2\u0149"+
		"\u014a\7\16\2\2\u014a\u014b\7\5\2\2\u014b\u014c\5\n\6\2\u014c\u014d\7"+
		"\6\2\2\u014d\u014e\7#\2\2\u014e\u014f\5\4\3\2\u014f\u0150\5\34\17\2\u0150"+
		"-\3\2\2\2\"\61\63<RUciqs\u0084\u0090\u0094\u0098\u00a0\u00a7\u00b5\u00ea"+
		"\u00ec\u00f2\u00f9\u00fc\u0104\u010a\u010c\u0114\u011b\u0124\u012b\u0133"+
		"\u013b\u013d\u0143";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}