/** grammar for the language Mx* */
grammar Mx;

program : (funcDefinition | classDefinition | varDefinition)* EOF;

type : Bool | Int | String | Identifier | type ('[' ']')+;
returnType : type | Void;

block : '{' statement* '}';

funcDefinition : returnType funcDefinitionSub;
funcDefinitionSub : Identifier '(' paramList ')' block;
paramList : '(' (param (',' param)* )? ')';
param : type Identifier;

varDefinition : type varDefinitionSub (',' varDefinitionSub)*;
varDefinitionSub : Identifier ('=' expression)?;

classDefinition
    : Class Identifier
        '{' (funcDefinitionSub | varDefinition)* '}' ';'
    ;

statement
    : block
    | varDefinition ';'
    | If '(' expression ')' trueStmt=statement
        (Else falseStmt=statement)
    | While '(' expression ')' loopStmt=statement
    | For '(' init=(expression | varDefinition)? ';'
        cond=expression? ';' incr=expression? ')' loopStmt=statement
    | (Break | Continue) ';'
    | Return expression? ';'
    | expression ';'
    | ';'
    ;

expression
    : primary
    // priority level 2
    | expression op=('++'|'--')
    | (expression)
    | expression '.' Identifier
    | expression '(' expressionList ')'
    | expression '[' index=expression ']'
    // priority level 3
    | <assoc=right> op=('++'|'--') expression
    | <assoc=right> op=('+'|'-'|'!'|'~') expression
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

expressionList
    : (expression (',' expression)* )?
    ;

primary
    : Identifier | literal
    ;

literal
    : IntegerLiteral | IntegerLiteral | BooleanLiteral | NullLiteral
    ;

lambda : '[' '&' ']' paramList '->' block expressionList;

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


Identifier
    : [a-zA-Z] [a-zA-Z_0-9]{0, 63}
    ;


Whitespace : [ \t]+ -> skip;
NewLine : '\r'?'\n' -> skip;

BlockComment : '/*'.*?'*/' -> skip;
LineComment : '//'~[\r\n]* -> skip;

