grammar Ftl;

@header {
package com.ftl.antlr;
import com.ftl.transaction.Transaction;
import com.ftl.antlr.Variable;
import com.ftl.antlr.ObjectMath;
import java.util.List;
}
@members{
	public Transaction trans;
	public ObjectMath om = new ObjectMath();
}
transaction [Transaction trans]
	@init{this.trans = $trans;}
    : beginStatement statement+ endStatement
    ;
 
beginStatement
	: 'begin'
	;

endStatement
	: 'end'
	;

    
statement
	: decarationStatement ';'
	| expression ';'
	| keyword ';'
	;
	
expression returns [String name, Object obj] 
	: lValue '=' rValue
	{
		$name = $lValue.text;
		$obj = $rValue.obj;
		trans.assignVariable($name, $obj);
	}
	;

amount returns [Double amt]
	:	literal
	{
		$amt = (Double) $literal.obj;
	}
	| variable
	{
		$amt = (Double) trans.getValue($variable.text);
	}
	;
	
description returns [String desc]
	:	literal
	{
		$desc = (String) $literal.obj;
	}
	| variable
	{
		$desc = (String) trans.getValue($variable.text);
	}
	;

account returns [String acc]
	:	literal
	{
		$acc = (String) $literal.obj;
	}
	| variable
	{
		$acc = (String) trans.getValue($variable.text);
	}
	;
	
lValue
	: variable
	;

rValue returns [Object obj]
	@init{
		Object rh;
		Object lh;
	}
	:	variable
	{
		$obj = trans.getValue($variable.text);
	}
	|	literal 
	{
		$obj = $literal.obj;
	}
	|	keywordAssignment
	{
		$obj = $keywordAssignment.obj;
	}
	|	lVal operand rVal
	{
				
		lh = $lVal.obj;
		rh = $rVal.obj;
		
		switch ($operand.text) {
		case "+":
			$obj = om.addObject(lh, rh);
			break;
		
		case "-":
			$obj = om.subObject(lh, rh);
			break;
			
		case "*":
			$obj = om.multObject(lh, rh);
			break;
			
		case "/":
			$obj = om.divObject(lh, rh);
			break;
		}
		
	}
	|   expression (operand rValue)*
	;
	
rVal returns [Object obj]
	: literal
	{
		$obj = $literal.obj;
	}
	| variable
	{
		$obj = trans.getValue($variable.text);
	}
	;

lVal returns [Object obj]
	: literal
	{
		$obj = $literal.obj;
	}
	| variable
	{
		$obj = trans.getValue($variable.text);
	}
	;



operand
	: mathOperand
	| bitWiseOperand
	;

mathOperand
	: '+'
	| '-'
	| '*'
	| '/'
	| '**'
	| '%'
	;

bitWiseOperand
	: '&'
	| '|'
	| '!'
	| '^'
	;
	
decarationStatement
	: type Identifier
	{
		trans.addVariable($Identifier.text, $type.text);
	}
	| type expression
	{
		trans.addVariable($expression.name, $type.text, $expression.obj);
	}
	;
	
type 
	:	BOOLEAN
	|	DOUBLE
	|	LONG
	|	STRING
 	;

variable
	: Identifier
	;

literal returns [Object obj]
	@init{
		String strLiteral;
	}
	:	IntegerLiteral
	{
		$obj = Long.valueOf($IntegerLiteral.text);
	}
	|	FloatOrDoubleLiteral
	{
		$obj = Double.valueOf($FloatOrDoubleLiteral.text);
	}
	|	StringLiteral
	{
		strLiteral = (String) $StringLiteral.text;
		$obj = strLiteral.substring(1, strLiteral.length() - 1);
	}
	|	BooleanLiteral
	;

keywordAssignment returns [Object obj]
	:	GetAmount
	{
		$obj = (Double) trans.getAmount();
	}
	|	GetTax
	{
		$obj = (Double) trans.getTax();
	}
	|	GetDescription
	{
		$obj = (String) trans.getDescription();
	}
	;

keyword
	:	Credit '(' amount ',' account ')'
	{
		trans.credit($amount.amt, $account.acc);
	}
	|	Debit '(' amount ',' account ')'
	{
		trans.debit($amount.amt, $account.acc);
	}
	|	Ledger '(' ledgerType ',' amount ',' account ',' description ')'
	{
		trans.ledger($ledgerType.retType, $amount.amt, $account.acc, $description.desc);
	}
	;
	
ledgerType returns [char retType]
	:	'C' {$retType = 'C';}
	|	'D' {$retType = 'D';}
	;

// LEXER

// §3.9 Keywords
// Reserve words in Ftl

Bind 			: 'bind';
Credit			: 'credit';
Debit			: 'debit';
Ledger			: 'ledger';
GetAmount		: 'getAmount';
GetTax			: 'getTax';
GetDescription	: 'getDescription';

BOOLEAN       : 'boolean';
CHAR          : 'char';
CONTINUE      : 'continue';
DO            : 'do';
DOUBLE        : 'double';
ELSE          : 'else';
ENUM          : 'enum';
FLOAT         : 'float';
STRING		  : 'string';
FOR           : 'for';
IF            : 'if';
INT           : 'int';
LONG          : 'long';
WHILE         : 'while';

// §3.10.1 Integer Literals

IntegerLiteral
    :   DecimalIntegerLiteral
    |   HexIntegerLiteral
    |   OctalIntegerLiteral
    |   BinaryIntegerLiteral
    ;


FloatOrDoubleLiteral
	: Digit+ '.' Digit*
	;

fragment
DecimalIntegerLiteral
    :   DecimalNumeral IntegerTypeSuffix?
    ;

fragment
HexIntegerLiteral
    :   HexNumeral IntegerTypeSuffix?
    ;

fragment
OctalIntegerLiteral
    :   OctalNumeral IntegerTypeSuffix?
    ;

fragment
BinaryIntegerLiteral
    :   BinaryNumeral IntegerTypeSuffix?
    ;

fragment
IntegerTypeSuffix
    :   [lL]
    ;

fragment
DecimalNumeral
    :   '0'
    |   NonZeroDigit (Digits? | Underscores Digits)
    ;

fragment
Digits
    :   Digit (DigitOrUnderscore* Digit)?
    ;

fragment
Digit
    :   '0'
    |   NonZeroDigit
    ;

fragment
NonZeroDigit
    :   [1-9]
    ;

fragment
DigitOrUnderscore
    :   Digit
    |   '_'
    ;

fragment
Underscores
    :   '_'+
    ;

fragment
HexNumeral
    :   '0' [xX] HexDigits
    ;

fragment
HexDigits
    :   HexDigit (HexDigitOrUnderscore* HexDigit)?
    ;

fragment
HexDigit
    :   [0-9a-fA-F]
    ;

fragment
HexDigitOrUnderscore
    :   HexDigit
    |   '_'
    ;

fragment
OctalNumeral
    :   '0' Underscores? OctalDigits
    ;

fragment
OctalDigits
    :   OctalDigit (OctalDigitOrUnderscore* OctalDigit)?
    ;

fragment
OctalDigit
    :   [0-7]
    ;

fragment
OctalDigitOrUnderscore
    :   OctalDigit
    |   '_'
    ;

fragment
BinaryNumeral
    :   '0' [bB] BinaryDigits
    ;

fragment
BinaryDigits
    :   BinaryDigit (BinaryDigitOrUnderscore* BinaryDigit)?
    ;

fragment
BinaryDigit
    :   [01]
    ;

fragment
BinaryDigitOrUnderscore
    :   BinaryDigit
    |   '_'
    ;

// §3.10.2 Floating-Point Literals

FloatingPointLiteral
    :   DecimalFloatingPointLiteral
    |   HexadecimalFloatingPointLiteral
    ;

fragment
DecimalFloatingPointLiteral
    :   Digits '.' Digits? ExponentPart? FloatTypeSuffix?
    |   '.' Digits ExponentPart? FloatTypeSuffix?
    |   Digits ExponentPart FloatTypeSuffix?
    |   Digits FloatTypeSuffix
    ;

fragment
ExponentPart
    :   ExponentIndicator SignedInteger
    ;

fragment
ExponentIndicator
    :   [eE]
    ;

fragment
SignedInteger
    :   Sign? Digits
    ;

fragment
Sign
    :   [+-]
    ;

fragment
FloatTypeSuffix
    :   [fFdD]
    ;

fragment
HexadecimalFloatingPointLiteral
    :   HexSignificand BinaryExponent FloatTypeSuffix?
    ;

fragment
HexSignificand
    :   HexNumeral '.'?
    |   '0' [xX] HexDigits? '.' HexDigits
    ;

fragment
BinaryExponent
    :   BinaryExponentIndicator SignedInteger
    ;

fragment
BinaryExponentIndicator
    :   [pP]
    ;

// §3.10.3 Boolean Literals

BooleanLiteral
    :   'true'
    |   'false'
    ;

// §3.10.4 Character Literals

CharacterLiteral
    :   '\'' SingleCharacter '\''
    |   '\'' EscapeSequence '\''
    ;

fragment
SingleCharacter
    :   ~['\\]
    ;
// §3.10.5 String Literals
StringLiteral
    :   '"' StringCharacters? '"'
    ;
fragment
StringCharacters
    :   StringCharacter+
    ;
fragment
StringCharacter
    :   ~["\\]
    |   EscapeSequence
    ;
// §3.10.6 Escape Sequences for Character and String Literals
fragment
EscapeSequence
    :   '\\' [btnfr"'\\]
    |   OctalEscape
    |   UnicodeEscape
    ;

fragment
OctalEscape
    :   '\\' OctalDigit
    |   '\\' OctalDigit OctalDigit
    |   '\\' ZeroToThree OctalDigit OctalDigit
    ;

fragment
UnicodeEscape
    :   '\\' 'u' HexDigit HexDigit HexDigit HexDigit
    ;

fragment
ZeroToThree
    :   [0-3]
    ;

// §3.10.7 The Null Literal

NullLiteral
    :   'null'
    ;
    
// §3.11 Separators

LPAREN          : '(';
RPAREN          : ')';
LBRACE          : '{';
RBRACE          : '}';
LBRACK          : '[';
RBRACK          : ']';
SEMI            : ';';
COMMA           : ',';
DOT             : '.';

// §3.12 Operators

ASSIGN          : '=';
GT              : '>';
LT              : '<';
BANG            : '!';
TILDE           : '~';
QUESTION        : '?';
COLON           : ':';
EQUAL           : '==';
LE              : '<=';
GE              : '>=';
NOTEQUAL        : '!=';
AND             : '&&';
OR              : '||';
INC             : '++';
DEC             : '--';
ADD             : '+';
SUB             : '-';
MUL             : '*';
DIV             : '/';
BITAND          : '&';
BITOR           : '|';
CARET           : '^';
MOD             : '%';

ADD_ASSIGN      : '+=';
SUB_ASSIGN      : '-=';
MUL_ASSIGN      : '*=';
DIV_ASSIGN      : '/=';
AND_ASSIGN      : '&=';
OR_ASSIGN       : '|=';
XOR_ASSIGN      : '^=';
MOD_ASSIGN      : '%=';
LSHIFT_ASSIGN   : '<<=';
RSHIFT_ASSIGN   : '>>=';
URSHIFT_ASSIGN  : '>>>=';

// §3.8 Identifiers (must appear after all keywords in the grammar)

Identifier
    :   JavaLetter JavaLetterOrDigit*
    ;

fragment
JavaLetter
    :   [a-zA-Z$_] // these are the "java letters" below 0x7F
    |   // covers all characters above 0x7F which are not a surrogate
        ~[\u0000-\u007F\uD800-\uDBFF]
        {Character.isJavaIdentifierStart(_input.LA(-1))}?
    |   // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
        [\uD800-\uDBFF] [\uDC00-\uDFFF]
        {Character.isJavaIdentifierStart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
    ;

fragment
JavaLetterOrDigit
    :   [a-zA-Z0-9$_] // these are the "java letters or digits" below 0x7F
    |   // covers all characters above 0x7F which are not a surrogate
        ~[\u0000-\u007F\uD800-\uDBFF]
        {Character.isJavaIdentifierPart(_input.LA(-1))}?
    |   // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
        [\uD800-\uDBFF] [\uDC00-\uDFFF]
        {Character.isJavaIdentifierPart(Character.toCodePoint((char)_input.LA(-2), (char)_input.LA(-1)))}?
    ;

//
// Additional symbols not defined in the lexical specification
//

AT : '@';


//
// Whitespace and comments
//

WS  :  [ \t\r\n\u000C]+ -> skip
    ;

COMMENT
    :   '/*' .*? '*/' -> skip
    ;

LINE_COMMENT
    :   '//' ~[\r\n]* -> skip
    ;
