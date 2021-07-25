# Scanning

**Scanning**, or **lexing**, or **lexical analysis**, is the first step in any compiler or interpreter. It reads in raw source code as a series of characters and groups it into a series of chunks called **tokens**. Tokens are meaningful "words" and "punctuation" that make up the language's grammar.

## Introduction

The smallest sequence that represents somthing is called a **lexeme**. They are the raw substirngs of the source code.

When a lexeme is combined with other useful information such as types, location (line number) and runtime values (for strings and numbers), it becomes a **token**.

## Token Types

jLox has the following lexemes:

- Punctuations, such as `(`, `)`, `{`, `}`, `.`, `;`.
- Operators: one or two character operators such as `+`, `-`, `>=` etc.
- Keywords: a lexeme that has special pre-defined meaning. For example: `class`, `fun`, `and` etc.
- Ltierals: identifers (variable names), strings and numbers.

To the concern of scanner, it uses the following token types:

- Single-charactre tokens: `LEFT_PAREN`, `RIGHT_PAREN`, `LEFT_BRACE`, `RIGHT_BRACE`, `COMMA`, `DOT`, `MINUS`, `PLUS`, `SEMICOLON`, `SLASH`, `STAR`.
- One or two character tokens:`BANG`, `BANG_EQUAL`,`EQUAL`, `EQUAL_EQUAL`,`GREATER`, `GREATER_EQUAL`, `LESS`, `LESS_EQUAL`.
- Literals: `IDENTIFIER`, `STRING`, `NUMBER`.
- Keywords: `AND`, `CLASS`, `ELSE`, `FALSE`, `FUN`, `FOR`, `IF`, `NIL`, `OR`, `PRINT`, `RETURN`, `SUPER`, `THIS`, `TRUE`, `VAR`, `WHILE`.
- End: `EOF`

Nubmer and strings are read in and are converted to the values of the living runtime objects that will be used by the interpreter later.

Each token has four fields:

- token type
- lexeme string
- literal value: only string and number have a value
- line: the line number in the source file

## Scan Tokens

- scan single character tokens except slash.
- scan one or two character tokens and slash. Ignore comments after the double slash.
- ignore white spaces: `' '`, `'\t'` and `'\r'`.
- add line number for each `'\n'`, ignore it.
- scan string literal.
- For the rest
  - scan number
  - scan keyword and identifier
  - report error

The result of scanner is a list of tokens, repreted by Java's `ArrayList`.
