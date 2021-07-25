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
