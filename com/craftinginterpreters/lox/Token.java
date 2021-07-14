package com.craftinginterpreters.lox;

class Token {
  final TokenType type;
  final String lexeme;  // the source text
  final Object literal;  // the token value
  final int line;

  Token(TokenType type, String lexeme, Object literal, int line) {
    this.type = type;
    this.lexeme = lexeme;
    this.literal = literal;
    this.line = line;
  }

  public String toString() {
    return type + " " + lexeme + " " + literal;
  }
}
