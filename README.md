# Crafting an Interpreter

## 1 Lox Grammar

There are five sets of grammar rules:

### 1.1 Program Rule

A program is a series of declarations: `program -> declaration\* EOF`.

### 1.2 Declaration Rules

Declarations are binding declarations and different statements: `declaration -> classDecl | funDecl | varDecl | statement`. They can be one of two types:

- binding declarations: `classDecl | funDecl | varDecl`
  - Class: `classDecl -> "class" IDENTIFIER ( "<" IDENTIFIER )? "{" function* "}"`
  - Function: `funDecl -> "fun" function`
  - Variable: `varDecl -> "var" IDENTIFIER ( "=" expression )? ";"`
- producing side effects: `statement -> exprStmt | forStmt | ifStmt | printStmt | returnStmt | whileStmt | block`. The effects are function/method call and printing output. Other statments control the execution flows or define scope.
  - Expression statement (a function/method call): `exprStmt -> expression ";"`
  - For statement: `forStmt -> "for" "(" (varDecl | exprStmt | ";" ) expression? ";" expression? ")" statement`
  - If statement: `ifStmt -> "if" "(" expression ")" statement ( "else" statement )?`
  - Print statement: `printStmt -> "print" expression ";"`
  - Return statement: `returnStmt -> "return" expression ";"`
  - While statement: `whileStmt -> "while" "(" expression ")" statement`
  - Block statement: `blockStmt -> "{" declaration* "}"`

### 1.3 Expression Rules

Expression produce values. Expressions can be chained while statements can not. Lox has a number of unary and binary operators with different levels of precedence. Lox uses a separate rule for each precedence level to make the precedence level explicit.

```lisp
expression     -> assignment ;

assignment     -> ( call "." )? IDENTIFIER "=" assignment
               | logic_or ;

logic_or       -> logic_and ( "or" logic_and )* ;
logic_and      -> equality ( "and" equality )* ;
equality       -> comparison ( ( "!=" | "==" ) comparison )* ;
comparison     -> term ( ( ">" | ">=" | "<" | "<=" ) term )* ;
term           -> factor ( ( "-" | "+" ) factor )* ;
factor         -> unary ( ( "/" | "*" ) unary )* ;

unary          -> ( "!" | "-" ) unary | call ;
call           -> primary ( "(" arguments? ")" | "." IDENTIFIER )* ;
primary        -> "true" | "false" | "nil" | "this"
               | NUMBER | STRING | IDENTIFIER | "(" expression ")"
               | "super" "." IDENTIFIER ;
```

### 1.4 Utility Rules

These rules are used to make the above grammar cleaner.

```lisp
function       -> IDENTIFIER "(" parameters? ")" block ;
parameters     -> IDENTIFIER ( "," IDENTIFIER )* ;
arguments      -> expression ( "," expression )* ;
```

### 1.5 Lexical Grammar

The lexical grammar is used by the scanner to group characters into tokens. Where the syntax is context free, the lexical grammar is regular—note that there are no recursive rules.

```lisp
NUMBER         -> DIGIT+ ( "." DIGIT+ )? ;
STRING         -> "\"" <any char except "\"">* "\"" ;
IDENTIFIER     -> ALPHA ( ALPHA | DIGIT )* ;
ALPHA          -> "a" ... "z" | "A" ... "Z" | "_" ;
DIGIT          -> "0" ... "9" ;
```
