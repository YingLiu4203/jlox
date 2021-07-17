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

## 2 Variable Declaration

A **variable declaration** statement creates a new binding that assicates a name with a value.

There are two levels of "precedence" for statemens. Inside a block or at the top leve, any kind of statement is allowed. Others such as the cluases in control flow statements, only the "higher" precedence statements are allowed. The variable declaration statement is not allowed in control flow clauses. To accomodate the distinction, we define the following rules:

```text
program -> declaration* EOF ;
declaration -> varDecl | statement ;
varDecl -> "var" IDENTIFIER ( "=" expression )? ";" ;
primary -> NUMBER | STRING | "true" | "false" | "nil" | "(" expression ")" | IDENTIFIER ;
```

`statement` and `expression` stay the same. We added the `IDENTIFIER` to the `primary`.

Then we add a statement node for a variable declaration: `"Var: Token name, Expr iniitializer"` and an expression node for accessing a variable: `"Varaible: Token name"`.

## 3 Environments

The data structure that stores the variable bindings is called an **environment**.
