# Functions

## 1 Function Calls

A call can be thought as a postfix operator that starts with `(`. The expression can be function name or an experssion that evaluates to a funciton. This "operator" has higher precedence than any other operator, even the unary ones.

```text
unary          → ( "!" | "-" ) unary | call ;
call           → primary ( "(" arguments? ")" )*
arguments      → expression ( "," expression )* ;
```

The syntax allows calls like `fn(1)(2)(3)` -- the currying style.

## 2 Native Functions

**Native functions** are also called **primitives**, **external functions**, or **foreign functions**. They are part of the runtime that can be called from a user's program. They provide access to the fundamental services that applications can use.

Many languages also allow users to provide their own native functions. The mechanism for doing so is called a **foreign function interface (FFI)**, **native extension**, or **native interface**.

The `clock` native function returns the number of seconds that have passed since some fixed point in time. It is defined in the global scope.

## 3 Function Declarations

A named function declaration performs two actions: creating a new function object, and binding a new variable to it. We use two rules to define function declaration rules because the `function` rule can be used to define class methods.

```text
declaration    → funDecl | varDecl | statement ;
funDecl        → "fun" function ;
function       → IDENTIFIER "(" parameters? ")" block ;
parameters     → IDENTIFIER ( "," IDENTIFIER )* ;
```

We don’t want the runtime phase of the interpreter to bleed into the front end’s syntax classes so we don’t want `Stmt.Function` itself to implement that. Instead, we wrap it in a new class `LoxFunction`.

## 4 Execution

Parameters are core to functions, especially the fact that a function encapsulates its parameters—no other code outside of the function can see them. This means each function gets its own environment where it stores those variables.

Further, this environment must be created dynamically. Each function call gets its own environment. Otherwise, recursion would break.

Interpreting a return statement is tricky. You can return from anywhere within the body of a function, even deeply nested inside other statements. It causes the function call to complete.

## 5 Local Functions and Closures

Lox supports **local functions** that are defined inside another function, or nested inside a block.

A **closure** holds on to the surrounding variables where the function is declared. In `LoxFunction`, we add a field to store an environment and initialize it in the constructor.
