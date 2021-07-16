# Statement

Statements don't evaluate to a value. They do some side effects that change the program state.

## 1 Program

A program is a list of statements. As start, we have the following rules:

```text
program -> statement* EOF ;
statement -> exprStmt | printStmt ;
exprStmt -> expression ";" ;
printStmt -> "print" expression ";" ;
```
