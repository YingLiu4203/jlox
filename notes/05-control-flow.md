# Control Flow

A Turing-complete machine has arithmetic, a little control flow, and the ability to allocate and use arbitrary amount of memory.

There are two kinds of control flows:

- **Conditional** or **branching**
- **Looping**

## 1 Conditional Execution

```lisp
statement      → exprStmt
               | ifStmt
               | printStmt
               | block ;

ifStmt         → "if" "(" expression ")" statement
               ( "else" statement )? ;
```

To avoid the **dangling else** problem, the `else` is bound to the nearest `if` that precedes it.

## 2 Logical Operators

`and` and `or` are special because they **short-circuit**. They are low in the precedence table. They are between assignment and equality. `or` is lower than `and`.

```lisp
assignment     → IDENTIFIER "=" assignment
               | logic_or ;
logic_or       → logic_and ( "or" logic_and )* ;
logic_and      → equality ( "and" equality )* ;
```

## 3 Loops

Lox has a `while` loop and a `for` loop.

A `for` loop has three clauses: an initializer, a condition, and an increment. Any of these clauses can be omitted. It can be rewritten as:

```java
initializer;
while(condition) {
  statement;
  increment;
}
```
