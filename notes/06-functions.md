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

## 3 Native Function `clock()`

The `clock` native function returns the number of seconds that have passed since some fixed point in time. It is defined in the global scope.
