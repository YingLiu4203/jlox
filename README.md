# Crafting an Interpreter

## 1 Top Level Structure

The main program runs either as a REPL or as a script executor. The source code is a string.

The `run(string source)` has four steps:

- scan source to return a list of tokens
- parse the list of tokens to return a list of statements
- resolve the list of statements to add variable resolving data to the interpreter.
- interpret/execute the list of statements

The first steps return compiling errors and the last step returns runtime errors. It uses the exit error code is defined in [Unix's `sysexits`](https://www.freebsd.org/cgi/man.cgi?query=sysexits).

- `64`: wrong command line arguments.
- `65`: incorrect input data (compiling error)
- `70`: an internal software error (runtime error)

## 2 Grammars and Parser

The scanner uses a regular language grammar that can be implemented as a finite state automaton.

The parser uses a context-free language grammar that can be implemented as a pushdown automaton because it has recursive rules. The grammar is carefully written to avoid ambiguity and enable levels of precedenceallow and a top-down parser using recursive descent implementation.

It uses meta-programming to create two class trees representing the expressions and statements. The visitor pattern is used to process the trees.

The parser creates a list of statements using the tokens from the scanner.

## 3 Interpreter

Using the visitor pattern, the interpreter `execute` each statement and `evaluate` each expression. It creates environment to enable scoped variable binding.

## 4 Semantic Analysis

A resovler is used to walk the tree, visisting each node and conduct semantic analysis. It can find grammar errors and build meta-data used by the interpreter.
