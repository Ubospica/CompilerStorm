/** grammar for the language Mx* */
grammar Mx;

program : (funcDefinition | (classDefinition) | (varDefinitionStmt))* EOF;

block : '{' statement* '}';


funcDefinition : returnType Identifier '(' paramList ')' block;
constructorDefinition : Identifier '(' ')' block;
paramList : (param (',' param)* )?;
param : type Identifier;

varDefinitionStmt : varDefinition ';';
varDefinition : type varDefinitionSub (',' varDefinitionSub)*;
varDefinitionSub : Identifier ('=' expression)?;

classDefinition
    : Class Identifier
        '{' (funcDefinition | constructorDefinition | (varDefinitionStmt))* '}' ';'
    ;

statement
    : block
    | varDefinition ';'
    | If '(' expression ')' trueStmt=statement
        (Else falseStmt=statement)?
    | While '(' expression ')' loopStmt=statement
    | For '(' (expression | varDefinition)? ';'
        cond=expression? ';' incr=expression? ')' loopStmt=statement
    | (Break | Continue) ';'
    | Return expression? ';'
    | expression ';'
    | ';'
    ;

expression
    : primary
    // priority level 2
    | lambda
    | expression op=('++'|'--')
    | '(' expression ')'
    | expression '.' Identifier
    | expression '(' expressionList ')'
    | expression '[' index=expression ']'
    // priority level 3
    | <assoc=right> op=('++'|'--') expression
    | <assoc=right> op=('+'|'-'|'!'|'~') expression
    | <assoc=right> newExpression
    //new delete * &
    // level 4 and more
    | expression op=('*'|'/'|'%') expression
    | expression op=('+'|'-') expression
    | expression op=('<<'|'>>') expression
    | expression op=('<'|'<='|'>'|'>=') expression
    | expression op=('=='|'!=') expression
    | expression op='&' expression
    | expression op='^' expression
    | expression op='|' expression
    | expression op='&&' expression
    | expression op='||' expression
    | <assoc=right> expression op='=' expression
    | expression op=',' expression
    ;

primary : Identifier | literal | This;

expressionList
    : (expression (',' expression)* )?
    ;

newExpression
    : New typeSub
    | New typeSub (errNewArraySize | newArraySize)
    | New Identifier ('(' ')')?
    ;
newArraySize : ('[' expression ']')* ('[' ']')*;
errNewArraySize : ('[' expression ']')* ('[' ']')+ ('[' expression ']')+;

type : typeSub | typeSub ('[' ']')+;
typeSub : Bool | Int | String | Identifier;
returnType : type | Void;

literal : IntegerLiteral | StringLiteral | BooleanLiteral | NullLiteral;

lambda : '[' '&' ']' '(' paramList ')' '->' block expressionList;


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

