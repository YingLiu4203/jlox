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

## The Expression Problem and The Visitor Pattern

To use the syntax tree, you can check the class types (or better using polymorphic dispatch) and process them in a function -- a functional style. It is easy to add new funcitons to work with the classes but adding a new type requires changes to all functions. Using an interfact with a set of predefined methods makes it easy to add new classes -- an OO style, but it is hard to add new methods to the interface because all classes are affected. The dilemma is called the expression problem that concerns the extenibility and modularity of adding new representations and new behaviors to a data abstraction.

For an OO language like Java, the Visitor pattern makes it easy to add new operations to classes without changing the classes. The implementation is to add an `abstract void accept(Visitor visitor)` method to the base class. Each subclass implement this method using a specific method defined in the `Visitor` interface, like `visitor.visitSubClass(this)`. The `Visitor` interface defines such a specific method for each subclass. Therefore classes that implement all methods of `Visitor` can be passed to any subclass to perform an appropirate operation. Adding a new operation is adding a new class implementing the `Visitor` interface. However, adding a new type is till an issue because you need to change all classes implementing the `Visitor` interface. It is just a solution to make it easy to add behaviors to a set of classes. The classes are pretty fixed because the language syntax is fixed -- however, the operations are more flexible than the class hierarchy.
