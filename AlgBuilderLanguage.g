grammar AlgBuilderLanguage;

start 		: ( function )* mainprocess ; 

function	: 'func' ID PIZQ ( ID ( COMA ID ) * )? PDER LLIZ command LLDE ;

mainprocess	: 'main' LLIZ command LLDE 
			| EOF
			;
			
command		: print command
			| declaration command
			| assignment command
			| expression command
			| forloop command
			| whileloop command
			| ifstatement command
			| returnstatement
			|
			;
            
returnstatement : 'return' ( expr )? SMCOLON;

print       : 'print' expr ( COMA expr )* SMCOLON ;

forloop     : 'for' ( TYPE )? ID ASSP expr SMCOLON expr SMCOLON expr LLIZ command LLDE ;

whileloop   : 'while' expr LLIZ command LLDE ;

ifstatement : 'if' expr LLIZ command LLDE ( 'else' LLIZ command LLDE )? ;

declaration	: TYPE ID ( ASSP expr )? SMCOLON
            | TYPE ID ( BIZQ expr BDER ( ASSP expr )? )? SMCOLON
			;

assignment	: ID ( BIZQ expr BDER )? ASSP expr SMCOLON ;

expression  : expr SMCOLON ;

expr		: PIZQ expr PDER
			| NOOP expr
			| SUMOP expr
			| expr POTOP expr
			| expr MULOP expr
			| expr SUMOP expr
			| expr RELOP expr
			| expr EQOP expr
			| expr ANDOP expr
			| expr OROP expr
			| ID ( ( BIZQ expr BDER )? | funcall | objcall )
			| LLIZ expr ( COMA expr )* LLDE
			| STRING
			| INTEGER
			| DOUBLE
			| BOOLEAN
			;
			
funcall     : PIZQ ( expr ( COMA expr ) * )? PDER ;

objcall     : DOT ID PIZQ ( expr ( COMA expr ) * )? PDER ;

LINE_COMMENT : '//' ~[\r\n]* -> skip ;
BLOCK_COMMENT: '/*' .*? '*/' -> skip ;

WS		    : [ \t\r\n]+ -> skip ;

ASSP	: '=' ;
OROP	: ( '||' ) ;
ANDOP	: ( '&&' ) ;
EQOP	: ( '==' | '!=' ) ;
RELOP	: ( '<' | '<=' | '>' | '>=' );
SUMOP	: ( '+' | '-' ) ;
MULOP	: ( '*' | '/' | '%' );
POTOP	: '^' ;
NOOP	: ( '!' ) ;
MIN     : ( '-' ) ;
DOT     : '.';

SMCOLON : ';' ;
COMA	: ',' ;

TYPE	: 'int'
        | 'any'
		| 'double'
		| 'string'
		| 'bool'
		| 'bTree'
		| 'graph'
		| 'arrayList'
		| 'linkedList'
		| 'gNode'
		| 'btNode'
		;

DOUBLE	: [0-9]+[.][0-9]+ ;
STRING  : COMI ~( '\r' | '\n' | '"' )* COMI ;
INTEGER : [0-9]+ ;
BOOLEAN : ( 'true' | 'false' ) ;


COMI	: ( '"' | '\'' ) ;

PIZQ	: '(' ;
PDER	: ')' ;
LLIZ    : '{' ;
LLDE    : '}' ;
BIZQ	: '[' ;
BDER	: ']' ;

ID 		: [a-zA-Z][a-zA-Z0-9_]* ;
