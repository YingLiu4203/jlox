# Representing Code

A parser takes tokens and transforms them into a code representation that is easy for the interpreter to consume later. A tree structure matches the operator nesting structure of a grammar.

## 1 Syntactic Grammar

In the syntactic grammar, a "letter" of an alphabet is a token and a "string" is a sequence of tokens. A grammar defines a set of **rules** that can dervie/produce a string from its alphabet. Rules are called **productions**.

Each production has a **head** and a **body**. The head is the rule name and the body describes what it generates. In its pure forms, the body is simply a list of symbols that come in two types:

- A **teminal** is a letter from the grammar's alphabet. Terminals are individual lexemes. It is terminal because it produces one symbol and doesn't lead to any future process.
- A **nonterminal** is a named reference to anther rule in the grammar. It means applying that rule and inserting whatever it produces. This is how grammar composes.

You may have multiple rules with the same name and you can pick any of the rules. **Backus-Naur from (BNF)** is a common notation to define rules.

- A name is followed by an arrow `->`, followed by a sequence of symboles, and ending with a semicolon `;`.
- A series of products are separated by a pipe `|` for the same name.
- Selecting one from a series of options is represented by a pair of parentheses enclosing options separated by `|`.
- A postfix `*` means repeating 0 or more times.
- A postfix `+` means repeating at least once.
- A postfix `?` means repeating 0 or one time, but not more.

## 2 A Grammar for Lox Expressions

```text
expression -> literal | unary | binary | grouping ;
literal -> NUMBER | STRING | "true" | "false" | "nil" ;
grouping -> "(" expression ")" ;
unary -> ("-" | "!") expression ;
binary -> expression operator expression ;
operator -> "==" | "!=" | "<" | "<= " | ">" | ">=" | "+" | "-" | "*" | "/" ;
```

## Implementing Syntax Trees

Because different expressions have different syntax trees, we use different classess derived from a base `Expr` to represent them. Each class is a bug of data. It has no method because it is created by a parser but is used by an interpreter. The class is used to communicate data between a parser and an interpreter.

```java
package com.craftinginterpreters.lox;

abstract class Expr {
  static class Binary extends Expr {
    Binary(Expr left, Token operator, Expr right) {
      this.left = left;
      this.operator = operator;
      this.right = right;
    }

    final Expr left;
    final Token operator;
    final Expr right;
  }

  // Other expressions...
}
```

It is tedious to handwrite each class definition, field declaration, constructor, and initializer. We write code to generate the classes.
