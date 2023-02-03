/** grammar for the language Mx* */
grammar Mx;

program : (programSub)* EOF;
programSub : funcDef | (classDef) | (varDefStmt);

block : '{' statement* '}';


funcDef : returnType Identifier '(' paramList ')' block;
constructorDef : Identifier '(' ')' block;
paramList : (param (',' param)* )?;
param : type Identifier;

varDefStmt : varDef ';';
varDef : type varDefSub (',' varDefSub)*;
varDefSub : Identifier ('=' expression)?;

classDef
    : Class Identifier
        '{' (funcDef | constructorDef | varDefStmt)* '}' ';'
    ;

statement
    : block                                                    # blockStmt
    | varDefStmt                                               # varDefineStmt
    | If '(' expression ')' trueStmt=statement
        (Else falseStmt=statement)?                            # ifStmt
    | While '(' expression ')' statement                       # whileStmt
//    | For '(' (expression | varDef)? ';'
    | For '(' (initDef=varDef | init=expression)? ';'
        cond=expression? ';' incr=expression? ')' statement    # forStmt
    | (Break | Continue) ';'                                   # controlStmt
    | Return expression? ';'                                   # returnStmt
    | expression ';'                                           # exprStmt
    | ';'                                                      # emptyStmt
    ;

expression
    : primary                                           # atomExpr
    // priority level 2
    | lambda                                            # lambdaExpr
    | <assoc=right> newExpression                       # newExpr
    | expression op=('++'|'--')                         # suffixExpr
    | '(' expression ')'                                # parenExpr
    | expression op='.' Identifier                      # memberAccessExpr
    | expression '(' expressionList ')'                 # funcCallExpr
    | base=expression '[' index=expression ']'          # subscriptExpr
    // priority level 3
    | <assoc=right> op=('++'|'--') expression           # prefixExpr
    | <assoc=right> op=('+'|'-'|'!'|'~') expression     # prefixExpr
    // new delete * &
    // level 4 and more
    | src1=expression op=('*'|'/'|'%') src2=expression            # binaryExpr
    | src1=expression op=('+'|'-') src2=expression                # binaryExpr
    | src1=expression op=('<<'|'>>') src2=expression              # binaryExpr
    | src1=expression op=('<'|'<='|'>'|'>=') src2=expression      # binaryExpr
    | src1=expression op=('=='|'!=') src2=expression              # binaryExpr
    | src1=expression op='&' src2=expression                      # binaryExpr
    | src1=expression op='^' src2=expression                      # binaryExpr
    | src1=expression op='|' src2=expression                      # binaryExpr
    | src1=expression op='&&' src2=expression                     # binaryBoolExpr
    | src1=expression op='||' src2=expression                     # binaryBoolExpr
    | <assoc=right> src1=expression op='=' src2=expression        # assignExpr
//    | src1=expression op=',' src2=expression                      # binaryExpr
    ;

primary : Identifier | literal | This;

expressionList
    : (expression (',' expression)* )?
    ;

newExpression
    : New typeSub errNewArraySize                       # errArrayNewExpr
    | New typeSub newArraySize                          # arrayNewExpr
    | New typeSub ('(' ')')?                            # classNewExpr
    | New typeSub                                       # simpleNewExpr
    ;

newArraySize : ('[' expression ']')+ ('[' ']')*;
errNewArraySize : ('[' expression ']')* ('[' ']')+ ('[' expression ']')+ ('[' expression? ']')*;

type : typeSub | typeSub ('[' ']')+;
typeSub : Bool | Int | String | Identifier;
returnType : type | Void;

literal : IntegerLiteral | StringLiteral | BooleanLiteral | NullLiteral;

captureList : '&'?;
lambda : '[' captureList ']' '(' paramList ')' '->' block;


// literal constants

IntegerLiteral
    : [1-9][0-9]*
    | '0'
    ;

StringLiteral
    : '"' (~[\\"] | '\\' ["\\tn])* '"'
    ;

BooleanLiteral
    : True | False
    ;

NullLiteral
    : Null
    ;

// keywords
// int bool string null void true false if else for while break continue return new class this
Int : 'int';
Bool : 'bool';
String : 'string';
Null : 'null';
Void : 'void';
True : 'true';
False : 'false';
If : 'if';
Else : 'else';
For : 'for';
While : 'while';
Break : 'break';
Continue : 'continue';
Return : 'return';
New : 'new';
Class : 'class';
This : 'this';

Identifier
    : [a-zA-Z] [a-zA-Z_0-9]*
    ;


Whitespace : [ \t]+ -> skip;
NewLine : '\r'?'\n' -> skip;

BlockComment : '/*'.*?'*/' -> skip;
LineComment : '//'~[\r\n]* -> skip;
