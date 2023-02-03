// Generated from /home/ubospica/courses/compiler/CompilerStorm/src/Parser/Mx.g4 by ANTLR 4.9.2
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
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

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
		RULE_program = 0, RULE_programSub = 1, RULE_block = 2, RULE_funcDef = 3, 
		RULE_constructorDef = 4, RULE_paramList = 5, RULE_param = 6, RULE_varDefStmt = 7, 
		RULE_varDef = 8, RULE_varDefSub = 9, RULE_classDef = 10, RULE_statement = 11, 
		RULE_expression = 12, RULE_primary = 13, RULE_expressionList = 14, RULE_newExpression = 15, 
		RULE_newArraySize = 16, RULE_errNewArraySize = 17, RULE_type = 18, RULE_typeSub = 19, 
		RULE_returnType = 20, RULE_literal = 21, RULE_captureList = 22, RULE_lambda = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "programSub", "block", "funcDef", "constructorDef", "paramList", 
			"param", "varDefStmt", "varDef", "varDefSub", "classDef", "statement", 
			"expression", "primary", "expressionList", "newExpression", "newArraySize", 
			"errNewArraySize", "type", "typeSub", "returnType", "literal", "captureList", 
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
		public List<ProgramSubContext> programSub() {
			return getRuleContexts(ProgramSubContext.class);
		}
		public ProgramSubContext programSub(int i) {
			return getRuleContext(ProgramSubContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Void) | (1L << Class) | (1L << Identifier))) != 0)) {
				{
				{
				setState(48);
				programSub();
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
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

	public static class ProgramSubContext extends ParserRuleContext {
		public FuncDefContext funcDef() {
			return getRuleContext(FuncDefContext.class,0);
		}
		public ClassDefContext classDef() {
			return getRuleContext(ClassDefContext.class,0);
		}
		public VarDefStmtContext varDefStmt() {
			return getRuleContext(VarDefStmtContext.class,0);
		}
		public ProgramSubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programSub; }
	}

	public final ProgramSubContext programSub() throws RecognitionException {
		ProgramSubContext _localctx = new ProgramSubContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_programSub);
		try {
			setState(59);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				funcDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(57);
				classDef();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(58);
				varDefStmt();
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
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(T__0);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__2) | (1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << IntegerLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << NullLiteral) | (1L << Int) | (1L << Bool) | (1L << String) | (1L << If) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << New) | (1L << This) | (1L << Identifier))) != 0)) {
				{
				{
				setState(62);
				statement();
				}
				}
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(68);
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

	public static class FuncDefContext extends ParserRuleContext {
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
		public FuncDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDef; }
	}

	public final FuncDefContext funcDef() throws RecognitionException {
		FuncDefContext _localctx = new FuncDefContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_funcDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			returnType();
			setState(71);
			match(Identifier);
			setState(72);
			match(T__2);
			setState(73);
			paramList();
			setState(74);
			match(T__3);
			setState(75);
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

	public static class ConstructorDefContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ConstructorDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorDef; }
	}

	public final ConstructorDefContext constructorDef() throws RecognitionException {
		ConstructorDefContext _localctx = new ConstructorDefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_constructorDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(Identifier);
			setState(78);
			match(T__2);
			setState(79);
			match(T__3);
			setState(80);
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
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(82);
				param();
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(83);
					match(T__4);
					setState(84);
					param();
					}
					}
					setState(89);
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
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			type();
			setState(93);
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

	public static class VarDefStmtContext extends ParserRuleContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public VarDefStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDefStmt; }
	}

	public final VarDefStmtContext varDefStmt() throws RecognitionException {
		VarDefStmtContext _localctx = new VarDefStmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_varDefStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			varDef();
			setState(96);
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

	public static class VarDefContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<VarDefSubContext> varDefSub() {
			return getRuleContexts(VarDefSubContext.class);
		}
		public VarDefSubContext varDefSub(int i) {
			return getRuleContext(VarDefSubContext.class,i);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			type();
			setState(99);
			varDefSub();
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(100);
				match(T__4);
				setState(101);
				varDefSub();
				}
				}
				setState(106);
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

	public static class VarDefSubContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarDefSubContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDefSub; }
	}

	public final VarDefSubContext varDefSub() throws RecognitionException {
		VarDefSubContext _localctx = new VarDefSubContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_varDefSub);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			match(Identifier);
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(108);
				match(T__6);
				setState(109);
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

	public static class ClassDefContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public List<FuncDefContext> funcDef() {
			return getRuleContexts(FuncDefContext.class);
		}
		public FuncDefContext funcDef(int i) {
			return getRuleContext(FuncDefContext.class,i);
		}
		public List<ConstructorDefContext> constructorDef() {
			return getRuleContexts(ConstructorDefContext.class);
		}
		public ConstructorDefContext constructorDef(int i) {
			return getRuleContext(ConstructorDefContext.class,i);
		}
		public List<VarDefStmtContext> varDefStmt() {
			return getRuleContexts(VarDefStmtContext.class);
		}
		public VarDefStmtContext varDefStmt(int i) {
			return getRuleContext(VarDefStmtContext.class,i);
		}
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_classDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(Class);
			setState(113);
			match(Identifier);
			setState(114);
			match(T__0);
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Void) | (1L << Identifier))) != 0)) {
				{
				setState(118);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(115);
					funcDef();
					}
					break;
				case 2:
					{
					setState(116);
					constructorDef();
					}
					break;
				case 3:
					{
					setState(117);
					varDefStmt();
					}
					break;
				}
				}
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(123);
			match(T__1);
			setState(124);
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
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ForStmtContext extends StatementContext {
		public VarDefContext initDef;
		public ExpressionContext init;
		public ExpressionContext cond;
		public ExpressionContext incr;
		public TerminalNode For() { return getToken(MxParser.For, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class ExprStmtContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class WhileStmtContext extends StatementContext {
		public TerminalNode While() { return getToken(MxParser.While, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class IfStmtContext extends StatementContext {
		public StatementContext trueStmt;
		public StatementContext falseStmt;
		public TerminalNode If() { return getToken(MxParser.If, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxParser.Else, 0); }
		public IfStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class BlockStmtContext extends StatementContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public BlockStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class ControlStmtContext extends StatementContext {
		public TerminalNode Break() { return getToken(MxParser.Break, 0); }
		public TerminalNode Continue() { return getToken(MxParser.Continue, 0); }
		public ControlStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class EmptyStmtContext extends StatementContext {
		public EmptyStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class VarDefineStmtContext extends StatementContext {
		public VarDefStmtContext varDefStmt() {
			return getRuleContext(VarDefStmtContext.class,0);
		}
		public VarDefineStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}
	public static class ReturnStmtContext extends StatementContext {
		public TerminalNode Return() { return getToken(MxParser.Return, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStmtContext(StatementContext ctx) { copyFrom(ctx); }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_statement);
		int _la;
		try {
			setState(170);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new BlockStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				block();
				}
				break;
			case 2:
				_localctx = new VarDefineStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(127);
				varDefStmt();
				}
				break;
			case 3:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(128);
				match(If);
				setState(129);
				match(T__2);
				setState(130);
				expression(0);
				setState(131);
				match(T__3);
				setState(132);
				((IfStmtContext)_localctx).trueStmt = statement();
				setState(135);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(133);
					match(Else);
					setState(134);
					((IfStmtContext)_localctx).falseStmt = statement();
					}
					break;
				}
				}
				break;
			case 4:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(137);
				match(While);
				setState(138);
				match(T__2);
				setState(139);
				expression(0);
				setState(140);
				match(T__3);
				setState(141);
				statement();
				}
				break;
			case 5:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(143);
				match(For);
				setState(144);
				match(T__2);
				setState(147);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(145);
					((ForStmtContext)_localctx).initDef = varDef();
					}
					break;
				case 2:
					{
					setState(146);
					((ForStmtContext)_localctx).init = expression(0);
					}
					break;
				}
				setState(149);
				match(T__5);
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << IntegerLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << NullLiteral) | (1L << New) | (1L << This) | (1L << Identifier))) != 0)) {
					{
					setState(150);
					((ForStmtContext)_localctx).cond = expression(0);
					}
				}

				setState(153);
				match(T__5);
				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << IntegerLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << NullLiteral) | (1L << New) | (1L << This) | (1L << Identifier))) != 0)) {
					{
					setState(154);
					((ForStmtContext)_localctx).incr = expression(0);
					}
				}

				setState(157);
				match(T__3);
				setState(158);
				statement();
				}
				break;
			case 6:
				_localctx = new ControlStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(159);
				_la = _input.LA(1);
				if ( !(_la==Break || _la==Continue) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(160);
				match(T__5);
				}
				break;
			case 7:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(161);
				match(Return);
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << IntegerLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << NullLiteral) | (1L << New) | (1L << This) | (1L << Identifier))) != 0)) {
					{
					setState(162);
					expression(0);
					}
				}

				setState(165);
				match(T__5);
				}
				break;
			case 8:
				_localctx = new ExprStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(166);
				expression(0);
				setState(167);
				match(T__5);
				}
				break;
			case 9:
				_localctx = new EmptyStmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(169);
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
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NewExprContext extends ExpressionContext {
		public NewExpressionContext newExpression() {
			return getRuleContext(NewExpressionContext.class,0);
		}
		public NewExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class PrefixExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrefixExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class SubscriptExprContext extends ExpressionContext {
		public ExpressionContext base;
		public ExpressionContext index;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public SubscriptExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class LambdaExprContext extends ExpressionContext {
		public LambdaContext lambda() {
			return getRuleContext(LambdaContext.class,0);
		}
		public LambdaExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class SuffixExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SuffixExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class AtomExprContext extends ExpressionContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public AtomExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class BinaryExprContext extends ExpressionContext {
		public ExpressionContext src1;
		public Token op;
		public ExpressionContext src2;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class MemberAccessExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxParser.Identifier, 0); }
		public MemberAccessExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class FuncCallExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public FuncCallExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class BinaryBoolExprContext extends ExpressionContext {
		public ExpressionContext src1;
		public Token op;
		public ExpressionContext src2;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryBoolExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class AssignExprContext extends ExpressionContext {
		public ExpressionContext src1;
		public Token op;
		public ExpressionContext src2;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AssignExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class ParenExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IntegerLiteral:
			case StringLiteral:
			case BooleanLiteral:
			case NullLiteral:
			case This:
			case Identifier:
				{
				_localctx = new AtomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(173);
				primary();
				}
				break;
			case T__10:
				{
				_localctx = new LambdaExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(174);
				lambda();
				}
				break;
			case New:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(175);
				newExpression();
				}
				break;
			case T__2:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(176);
				match(T__2);
				setState(177);
				expression(0);
				setState(178);
				match(T__3);
				}
				break;
			case T__7:
			case T__8:
				{
				_localctx = new PrefixExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(180);
				((PrefixExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
					((PrefixExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(181);
				expression(13);
				}
				break;
			case T__12:
			case T__13:
			case T__14:
			case T__15:
				{
				_localctx = new PrefixExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(182);
				((PrefixExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15))) != 0)) ) {
					((PrefixExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(183);
				expression(12);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(236);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(234);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(186);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(187);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__16) | (1L << T__17) | (1L << T__18))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(188);
						((BinaryExprContext)_localctx).src2 = expression(12);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(189);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(190);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__12 || _la==T__13) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(191);
						((BinaryExprContext)_localctx).src2 = expression(11);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(192);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(193);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__19 || _la==T__20) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(194);
						((BinaryExprContext)_localctx).src2 = expression(10);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(195);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(196);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(197);
						((BinaryExprContext)_localctx).src2 = expression(9);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(198);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(199);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__25 || _la==T__26) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(200);
						((BinaryExprContext)_localctx).src2 = expression(8);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(201);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(202);
						((BinaryExprContext)_localctx).op = match(T__27);
						setState(203);
						((BinaryExprContext)_localctx).src2 = expression(7);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(204);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(205);
						((BinaryExprContext)_localctx).op = match(T__28);
						setState(206);
						((BinaryExprContext)_localctx).src2 = expression(6);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(207);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(208);
						((BinaryExprContext)_localctx).op = match(T__29);
						setState(209);
						((BinaryExprContext)_localctx).src2 = expression(5);
						}
						break;
					case 9:
						{
						_localctx = new BinaryBoolExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryBoolExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(210);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(211);
						((BinaryBoolExprContext)_localctx).op = match(T__30);
						setState(212);
						((BinaryBoolExprContext)_localctx).src2 = expression(4);
						}
						break;
					case 10:
						{
						_localctx = new BinaryBoolExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryBoolExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(213);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(214);
						((BinaryBoolExprContext)_localctx).op = match(T__31);
						setState(215);
						((BinaryBoolExprContext)_localctx).src2 = expression(3);
						}
						break;
					case 11:
						{
						_localctx = new AssignExprContext(new ExpressionContext(_parentctx, _parentState));
						((AssignExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(216);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(217);
						((AssignExprContext)_localctx).op = match(T__6);
						setState(218);
						((AssignExprContext)_localctx).src2 = expression(1);
						}
						break;
					case 12:
						{
						_localctx = new SuffixExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(219);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(220);
						((SuffixExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__8) ) {
							((SuffixExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					case 13:
						{
						_localctx = new MemberAccessExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(221);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(222);
						((MemberAccessExprContext)_localctx).op = match(T__9);
						setState(223);
						match(Identifier);
						}
						break;
					case 14:
						{
						_localctx = new FuncCallExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(224);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(225);
						match(T__2);
						setState(226);
						expressionList();
						setState(227);
						match(T__3);
						}
						break;
					case 15:
						{
						_localctx = new SubscriptExprContext(new ExpressionContext(_parentctx, _parentState));
						((SubscriptExprContext)_localctx).base = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(229);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(230);
						match(T__10);
						setState(231);
						((SubscriptExprContext)_localctx).index = expression(0);
						setState(232);
						match(T__11);
						}
						break;
					}
					} 
				}
				setState(238);
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
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_primary);
		try {
			setState(242);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(239);
				match(Identifier);
				}
				break;
			case IntegerLiteral:
			case StringLiteral:
			case BooleanLiteral:
			case NullLiteral:
				enterOuterAlt(_localctx, 2);
				{
				setState(240);
				literal();
				}
				break;
			case This:
				enterOuterAlt(_localctx, 3);
				{
				setState(241);
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
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << IntegerLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << NullLiteral) | (1L << New) | (1L << This) | (1L << Identifier))) != 0)) {
				{
				setState(244);
				expression(0);
				setState(249);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(245);
					match(T__4);
					setState(246);
					expression(0);
					}
					}
					setState(251);
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

	public static class NewExpressionContext extends ParserRuleContext {
		public NewExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_newExpression; }
	 
		public NewExpressionContext() { }
		public void copyFrom(NewExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ArrayNewExprContext extends NewExpressionContext {
		public TerminalNode New() { return getToken(MxParser.New, 0); }
		public TypeSubContext typeSub() {
			return getRuleContext(TypeSubContext.class,0);
		}
		public NewArraySizeContext newArraySize() {
			return getRuleContext(NewArraySizeContext.class,0);
		}
		public ArrayNewExprContext(NewExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class ClassNewExprContext extends NewExpressionContext {
		public TerminalNode New() { return getToken(MxParser.New, 0); }
		public TypeSubContext typeSub() {
			return getRuleContext(TypeSubContext.class,0);
		}
		public ClassNewExprContext(NewExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class SimpleNewExprContext extends NewExpressionContext {
		public TerminalNode New() { return getToken(MxParser.New, 0); }
		public TypeSubContext typeSub() {
			return getRuleContext(TypeSubContext.class,0);
		}
		public SimpleNewExprContext(NewExpressionContext ctx) { copyFrom(ctx); }
	}
	public static class ErrArrayNewExprContext extends NewExpressionContext {
		public TerminalNode New() { return getToken(MxParser.New, 0); }
		public TypeSubContext typeSub() {
			return getRuleContext(TypeSubContext.class,0);
		}
		public ErrNewArraySizeContext errNewArraySize() {
			return getRuleContext(ErrNewArraySizeContext.class,0);
		}
		public ErrArrayNewExprContext(NewExpressionContext ctx) { copyFrom(ctx); }
	}

	public final NewExpressionContext newExpression() throws RecognitionException {
		NewExpressionContext _localctx = new NewExpressionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_newExpression);
		try {
			setState(270);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				_localctx = new ErrArrayNewExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(254);
				match(New);
				setState(255);
				typeSub();
				setState(256);
				errNewArraySize();
				}
				break;
			case 2:
				_localctx = new ArrayNewExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(258);
				match(New);
				setState(259);
				typeSub();
				setState(260);
				newArraySize();
				}
				break;
			case 3:
				_localctx = new ClassNewExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(262);
				match(New);
				setState(263);
				typeSub();
				setState(266);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(264);
					match(T__2);
					setState(265);
					match(T__3);
					}
					break;
				}
				}
				break;
			case 4:
				_localctx = new SimpleNewExprContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(268);
				match(New);
				setState(269);
				typeSub();
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
	}

	public final NewArraySizeContext newArraySize() throws RecognitionException {
		NewArraySizeContext _localctx = new NewArraySizeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_newArraySize);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(276); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(272);
					match(T__10);
					setState(273);
					expression(0);
					setState(274);
					match(T__11);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(278); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(284);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(280);
					match(T__10);
					setState(281);
					match(T__11);
					}
					} 
				}
				setState(286);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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
	}

	public final ErrNewArraySizeContext errNewArraySize() throws RecognitionException {
		ErrNewArraySizeContext _localctx = new ErrNewArraySizeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_errNewArraySize);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(287);
					match(T__10);
					setState(288);
					expression(0);
					setState(289);
					match(T__11);
					}
					} 
				}
				setState(295);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			setState(298); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(296);
					match(T__10);
					setState(297);
					match(T__11);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(300); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(306); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(302);
					match(T__10);
					setState(303);
					expression(0);
					setState(304);
					match(T__11);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(308); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(317);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(310);
					match(T__10);
					setState(312);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__7) | (1L << T__8) | (1L << T__10) | (1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << IntegerLiteral) | (1L << StringLiteral) | (1L << BooleanLiteral) | (1L << NullLiteral) | (1L << New) | (1L << This) | (1L << Identifier))) != 0)) {
						{
						setState(311);
						expression(0);
						}
					}

					setState(314);
					match(T__11);
					}
					} 
				}
				setState(319);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
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

	public static class TypeContext extends ParserRuleContext {
		public TypeSubContext typeSub() {
			return getRuleContext(TypeSubContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_type);
		int _la;
		try {
			setState(328);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(320);
				typeSub();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(321);
				typeSub();
				setState(324); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(322);
					match(T__10);
					setState(323);
					match(T__11);
					}
					}
					setState(326); 
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
	}

	public final TypeSubContext typeSub() throws RecognitionException {
		TypeSubContext _localctx = new TypeSubContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_typeSub);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
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
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_returnType);
		try {
			setState(334);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Int:
			case Bool:
			case String:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(332);
				type();
				}
				break;
			case Void:
				enterOuterAlt(_localctx, 2);
				{
				setState(333);
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
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
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

	public static class CaptureListContext extends ParserRuleContext {
		public CaptureListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_captureList; }
	}

	public final CaptureListContext captureList() throws RecognitionException {
		CaptureListContext _localctx = new CaptureListContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_captureList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(338);
				match(T__27);
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

	public static class LambdaContext extends ParserRuleContext {
		public CaptureListContext captureList() {
			return getRuleContext(CaptureListContext.class,0);
		}
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public LambdaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambda; }
	}

	public final LambdaContext lambda() throws RecognitionException {
		LambdaContext _localctx = new LambdaContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_lambda);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(341);
			match(T__10);
			setState(342);
			captureList();
			setState(343);
			match(T__11);
			setState(344);
			match(T__2);
			setState(345);
			paramList();
			setState(346);
			match(T__3);
			setState(347);
			match(T__32);
			setState(348);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 12:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		case 11:
			return precpred(_ctx, 18);
		case 12:
			return precpred(_ctx, 16);
		case 13:
			return precpred(_ctx, 15);
		case 14:
			return precpred(_ctx, 14);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3=\u0161\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\7\2\64\n\2\f\2\16\2\67\13\2\3\2\3\2\3\3\3\3\3\3\5\3>\n\3\3\4\3\4"+
		"\7\4B\n\4\f\4\16\4E\13\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\7\3\7\3\7\7\7X\n\7\f\7\16\7[\13\7\5\7]\n\7\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\7\ni\n\n\f\n\16\nl\13\n\3\13\3\13\3\13\5\13"+
		"q\n\13\3\f\3\f\3\f\3\f\3\f\3\f\7\fy\n\f\f\f\16\f|\13\f\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u008a\n\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\5\r\u0096\n\r\3\r\3\r\5\r\u009a\n\r\3\r\3\r\5\r\u009e"+
		"\n\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00a6\n\r\3\r\3\r\3\r\3\r\3\r\5\r\u00ad"+
		"\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16"+
		"\u00bb\n\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00ed\n\16\f\16\16\16\u00f0"+
		"\13\16\3\17\3\17\3\17\5\17\u00f5\n\17\3\20\3\20\3\20\7\20\u00fa\n\20\f"+
		"\20\16\20\u00fd\13\20\5\20\u00ff\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\5\21\u010d\n\21\3\21\3\21\5\21\u0111\n\21"+
		"\3\22\3\22\3\22\3\22\6\22\u0117\n\22\r\22\16\22\u0118\3\22\3\22\7\22\u011d"+
		"\n\22\f\22\16\22\u0120\13\22\3\23\3\23\3\23\3\23\7\23\u0126\n\23\f\23"+
		"\16\23\u0129\13\23\3\23\3\23\6\23\u012d\n\23\r\23\16\23\u012e\3\23\3\23"+
		"\3\23\3\23\6\23\u0135\n\23\r\23\16\23\u0136\3\23\3\23\5\23\u013b\n\23"+
		"\3\23\7\23\u013e\n\23\f\23\16\23\u0141\13\23\3\24\3\24\3\24\3\24\6\24"+
		"\u0147\n\24\r\24\16\24\u0148\5\24\u014b\n\24\3\25\3\25\3\26\3\26\5\26"+
		"\u0151\n\26\3\27\3\27\3\30\5\30\u0156\n\30\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\2\3\32\32\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&(*,.\60\2\f\3\2\63\64\3\2\n\13\3\2\17\22\3\2\23\25\3\2\17\20\3"+
		"\2\26\27\3\2\30\33\3\2\34\35\4\2(*99\3\2$\'\2\u0188\2\65\3\2\2\2\4=\3"+
		"\2\2\2\6?\3\2\2\2\bH\3\2\2\2\nO\3\2\2\2\f\\\3\2\2\2\16^\3\2\2\2\20a\3"+
		"\2\2\2\22d\3\2\2\2\24m\3\2\2\2\26r\3\2\2\2\30\u00ac\3\2\2\2\32\u00ba\3"+
		"\2\2\2\34\u00f4\3\2\2\2\36\u00fe\3\2\2\2 \u0110\3\2\2\2\"\u0116\3\2\2"+
		"\2$\u0127\3\2\2\2&\u014a\3\2\2\2(\u014c\3\2\2\2*\u0150\3\2\2\2,\u0152"+
		"\3\2\2\2.\u0155\3\2\2\2\60\u0157\3\2\2\2\62\64\5\4\3\2\63\62\3\2\2\2\64"+
		"\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\668\3\2\2\2\67\65\3\2\2\289\7"+
		"\2\2\39\3\3\2\2\2:>\5\b\5\2;>\5\26\f\2<>\5\20\t\2=:\3\2\2\2=;\3\2\2\2"+
		"=<\3\2\2\2>\5\3\2\2\2?C\7\3\2\2@B\5\30\r\2A@\3\2\2\2BE\3\2\2\2CA\3\2\2"+
		"\2CD\3\2\2\2DF\3\2\2\2EC\3\2\2\2FG\7\4\2\2G\7\3\2\2\2HI\5*\26\2IJ\79\2"+
		"\2JK\7\5\2\2KL\5\f\7\2LM\7\6\2\2MN\5\6\4\2N\t\3\2\2\2OP\79\2\2PQ\7\5\2"+
		"\2QR\7\6\2\2RS\5\6\4\2S\13\3\2\2\2TY\5\16\b\2UV\7\7\2\2VX\5\16\b\2WU\3"+
		"\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2\\T\3\2\2\2\\"+
		"]\3\2\2\2]\r\3\2\2\2^_\5&\24\2_`\79\2\2`\17\3\2\2\2ab\5\22\n\2bc\7\b\2"+
		"\2c\21\3\2\2\2de\5&\24\2ej\5\24\13\2fg\7\7\2\2gi\5\24\13\2hf\3\2\2\2i"+
		"l\3\2\2\2jh\3\2\2\2jk\3\2\2\2k\23\3\2\2\2lj\3\2\2\2mp\79\2\2no\7\t\2\2"+
		"oq\5\32\16\2pn\3\2\2\2pq\3\2\2\2q\25\3\2\2\2rs\7\67\2\2st\79\2\2tz\7\3"+
		"\2\2uy\5\b\5\2vy\5\n\6\2wy\5\20\t\2xu\3\2\2\2xv\3\2\2\2xw\3\2\2\2y|\3"+
		"\2\2\2zx\3\2\2\2z{\3\2\2\2{}\3\2\2\2|z\3\2\2\2}~\7\4\2\2~\177\7\b\2\2"+
		"\177\27\3\2\2\2\u0080\u00ad\5\6\4\2\u0081\u00ad\5\20\t\2\u0082\u0083\7"+
		"/\2\2\u0083\u0084\7\5\2\2\u0084\u0085\5\32\16\2\u0085\u0086\7\6\2\2\u0086"+
		"\u0089\5\30\r\2\u0087\u0088\7\60\2\2\u0088\u008a\5\30\r\2\u0089\u0087"+
		"\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u00ad\3\2\2\2\u008b\u008c\7\62\2\2"+
		"\u008c\u008d\7\5\2\2\u008d\u008e\5\32\16\2\u008e\u008f\7\6\2\2\u008f\u0090"+
		"\5\30\r\2\u0090\u00ad\3\2\2\2\u0091\u0092\7\61\2\2\u0092\u0095\7\5\2\2"+
		"\u0093\u0096\5\22\n\2\u0094\u0096\5\32\16\2\u0095\u0093\3\2\2\2\u0095"+
		"\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0099\7\b"+
		"\2\2\u0098\u009a\5\32\16\2\u0099\u0098\3\2\2\2\u0099\u009a\3\2\2\2\u009a"+
		"\u009b\3\2\2\2\u009b\u009d\7\b\2\2\u009c\u009e\5\32\16\2\u009d\u009c\3"+
		"\2\2\2\u009d\u009e\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\7\6\2\2\u00a0"+
		"\u00ad\5\30\r\2\u00a1\u00a2\t\2\2\2\u00a2\u00ad\7\b\2\2\u00a3\u00a5\7"+
		"\65\2\2\u00a4\u00a6\5\32\16\2\u00a5\u00a4\3\2\2\2\u00a5\u00a6\3\2\2\2"+
		"\u00a6\u00a7\3\2\2\2\u00a7\u00ad\7\b\2\2\u00a8\u00a9\5\32\16\2\u00a9\u00aa"+
		"\7\b\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00ad\7\b\2\2\u00ac\u0080\3\2\2\2\u00ac"+
		"\u0081\3\2\2\2\u00ac\u0082\3\2\2\2\u00ac\u008b\3\2\2\2\u00ac\u0091\3\2"+
		"\2\2\u00ac\u00a1\3\2\2\2\u00ac\u00a3\3\2\2\2\u00ac\u00a8\3\2\2\2\u00ac"+
		"\u00ab\3\2\2\2\u00ad\31\3\2\2\2\u00ae\u00af\b\16\1\2\u00af\u00bb\5\34"+
		"\17\2\u00b0\u00bb\5\60\31\2\u00b1\u00bb\5 \21\2\u00b2\u00b3\7\5\2\2\u00b3"+
		"\u00b4\5\32\16\2\u00b4\u00b5\7\6\2\2\u00b5\u00bb\3\2\2\2\u00b6\u00b7\t"+
		"\3\2\2\u00b7\u00bb\5\32\16\17\u00b8\u00b9\t\4\2\2\u00b9\u00bb\5\32\16"+
		"\16\u00ba\u00ae\3\2\2\2\u00ba\u00b0\3\2\2\2\u00ba\u00b1\3\2\2\2\u00ba"+
		"\u00b2\3\2\2\2\u00ba\u00b6\3\2\2\2\u00ba\u00b8\3\2\2\2\u00bb\u00ee\3\2"+
		"\2\2\u00bc\u00bd\f\r\2\2\u00bd\u00be\t\5\2\2\u00be\u00ed\5\32\16\16\u00bf"+
		"\u00c0\f\f\2\2\u00c0\u00c1\t\6\2\2\u00c1\u00ed\5\32\16\r\u00c2\u00c3\f"+
		"\13\2\2\u00c3\u00c4\t\7\2\2\u00c4\u00ed\5\32\16\f\u00c5\u00c6\f\n\2\2"+
		"\u00c6\u00c7\t\b\2\2\u00c7\u00ed\5\32\16\13\u00c8\u00c9\f\t\2\2\u00c9"+
		"\u00ca\t\t\2\2\u00ca\u00ed\5\32\16\n\u00cb\u00cc\f\b\2\2\u00cc\u00cd\7"+
		"\36\2\2\u00cd\u00ed\5\32\16\t\u00ce\u00cf\f\7\2\2\u00cf\u00d0\7\37\2\2"+
		"\u00d0\u00ed\5\32\16\b\u00d1\u00d2\f\6\2\2\u00d2\u00d3\7 \2\2\u00d3\u00ed"+
		"\5\32\16\7\u00d4\u00d5\f\5\2\2\u00d5\u00d6\7!\2\2\u00d6\u00ed\5\32\16"+
		"\6\u00d7\u00d8\f\4\2\2\u00d8\u00d9\7\"\2\2\u00d9\u00ed\5\32\16\5\u00da"+
		"\u00db\f\3\2\2\u00db\u00dc\7\t\2\2\u00dc\u00ed\5\32\16\3\u00dd\u00de\f"+
		"\24\2\2\u00de\u00ed\t\3\2\2\u00df\u00e0\f\22\2\2\u00e0\u00e1\7\f\2\2\u00e1"+
		"\u00ed\79\2\2\u00e2\u00e3\f\21\2\2\u00e3\u00e4\7\5\2\2\u00e4\u00e5\5\36"+
		"\20\2\u00e5\u00e6\7\6\2\2\u00e6\u00ed\3\2\2\2\u00e7\u00e8\f\20\2\2\u00e8"+
		"\u00e9\7\r\2\2\u00e9\u00ea\5\32\16\2\u00ea\u00eb\7\16\2\2\u00eb\u00ed"+
		"\3\2\2\2\u00ec\u00bc\3\2\2\2\u00ec\u00bf\3\2\2\2\u00ec\u00c2\3\2\2\2\u00ec"+
		"\u00c5\3\2\2\2\u00ec\u00c8\3\2\2\2\u00ec\u00cb\3\2\2\2\u00ec\u00ce\3\2"+
		"\2\2\u00ec\u00d1\3\2\2\2\u00ec\u00d4\3\2\2\2\u00ec\u00d7\3\2\2\2\u00ec"+
		"\u00da\3\2\2\2\u00ec\u00dd\3\2\2\2\u00ec\u00df\3\2\2\2\u00ec\u00e2\3\2"+
		"\2\2\u00ec\u00e7\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee"+
		"\u00ef\3\2\2\2\u00ef\33\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00f5\79\2\2"+
		"\u00f2\u00f5\5,\27\2\u00f3\u00f5\78\2\2\u00f4\u00f1\3\2\2\2\u00f4\u00f2"+
		"\3\2\2\2\u00f4\u00f3\3\2\2\2\u00f5\35\3\2\2\2\u00f6\u00fb\5\32\16\2\u00f7"+
		"\u00f8\7\7\2\2\u00f8\u00fa\5\32\16\2\u00f9\u00f7\3\2\2\2\u00fa\u00fd\3"+
		"\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd"+
		"\u00fb\3\2\2\2\u00fe\u00f6\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\37\3\2\2"+
		"\2\u0100\u0101\7\66\2\2\u0101\u0102\5(\25\2\u0102\u0103\5$\23\2\u0103"+
		"\u0111\3\2\2\2\u0104\u0105\7\66\2\2\u0105\u0106\5(\25\2\u0106\u0107\5"+
		"\"\22\2\u0107\u0111\3\2\2\2\u0108\u0109\7\66\2\2\u0109\u010c\5(\25\2\u010a"+
		"\u010b\7\5\2\2\u010b\u010d\7\6\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2"+
		"\2\2\u010d\u0111\3\2\2\2\u010e\u010f\7\66\2\2\u010f\u0111\5(\25\2\u0110"+
		"\u0100\3\2\2\2\u0110\u0104\3\2\2\2\u0110\u0108\3\2\2\2\u0110\u010e\3\2"+
		"\2\2\u0111!\3\2\2\2\u0112\u0113\7\r\2\2\u0113\u0114\5\32\16\2\u0114\u0115"+
		"\7\16\2\2\u0115\u0117\3\2\2\2\u0116\u0112\3\2\2\2\u0117\u0118\3\2\2\2"+
		"\u0118\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011e\3\2\2\2\u011a\u011b"+
		"\7\r\2\2\u011b\u011d\7\16\2\2\u011c\u011a\3\2\2\2\u011d\u0120\3\2\2\2"+
		"\u011e\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f#\3\2\2\2\u0120\u011e\3"+
		"\2\2\2\u0121\u0122\7\r\2\2\u0122\u0123\5\32\16\2\u0123\u0124\7\16\2\2"+
		"\u0124\u0126\3\2\2\2\u0125\u0121\3\2\2\2\u0126\u0129\3\2\2\2\u0127\u0125"+
		"\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u012c\3\2\2\2\u0129\u0127\3\2\2\2\u012a"+
		"\u012b\7\r\2\2\u012b\u012d\7\16\2\2\u012c\u012a\3\2\2\2\u012d\u012e\3"+
		"\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0134\3\2\2\2\u0130"+
		"\u0131\7\r\2\2\u0131\u0132\5\32\16\2\u0132\u0133\7\16\2\2\u0133\u0135"+
		"\3\2\2\2\u0134\u0130\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0134\3\2\2\2\u0136"+
		"\u0137\3\2\2\2\u0137\u013f\3\2\2\2\u0138\u013a\7\r\2\2\u0139\u013b\5\32"+
		"\16\2\u013a\u0139\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013c\3\2\2\2\u013c"+
		"\u013e\7\16\2\2\u013d\u0138\3\2\2\2\u013e\u0141\3\2\2\2\u013f\u013d\3"+
		"\2\2\2\u013f\u0140\3\2\2\2\u0140%\3\2\2\2\u0141\u013f\3\2\2\2\u0142\u014b"+
		"\5(\25\2\u0143\u0146\5(\25\2\u0144\u0145\7\r\2\2\u0145\u0147\7\16\2\2"+
		"\u0146\u0144\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u0146\3\2\2\2\u0148\u0149"+
		"\3\2\2\2\u0149\u014b\3\2\2\2\u014a\u0142\3\2\2\2\u014a\u0143\3\2\2\2\u014b"+
		"\'\3\2\2\2\u014c\u014d\t\n\2\2\u014d)\3\2\2\2\u014e\u0151\5&\24\2\u014f"+
		"\u0151\7,\2\2\u0150\u014e\3\2\2\2\u0150\u014f\3\2\2\2\u0151+\3\2\2\2\u0152"+
		"\u0153\t\13\2\2\u0153-\3\2\2\2\u0154\u0156\7\36\2\2\u0155\u0154\3\2\2"+
		"\2\u0155\u0156\3\2\2\2\u0156/\3\2\2\2\u0157\u0158\7\r\2\2\u0158\u0159"+
		"\5.\30\2\u0159\u015a\7\16\2\2\u015a\u015b\7\5\2\2\u015b\u015c\5\f\7\2"+
		"\u015c\u015d\7\6\2\2\u015d\u015e\7#\2\2\u015e\u015f\5\6\4\2\u015f\61\3"+
		"\2\2\2$\65=CY\\jpxz\u0089\u0095\u0099\u009d\u00a5\u00ac\u00ba\u00ec\u00ee"+
		"\u00f4\u00fb\u00fe\u010c\u0110\u0118\u011e\u0127\u012e\u0136\u013a\u013f"+
		"\u0148\u014a\u0150\u0155";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}