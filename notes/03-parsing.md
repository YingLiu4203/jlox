# Parsing

## 1 Solve Ambiguity

Mathematiciancs addressed the operation ambiguity by **precedence** and **associativity**. You can fix the ambiguity by stratifying the grammar: defining a separate rule for each precedence level.

The rule of `factor -> factor ( "/" | "*" ) unary | unary ;` recurses to martch the left operand. This **left-recursive** causes trouble to the parsing techniques we are going to use. We use a one without putting the rule name as the head of the body: `factor -> unary (( "/" | "*") unary)* ;`.

The complete expression grammar is as the following:

```lisp
expression -> equality ;
equality -> comparison ( ( "!=" | "==") comparison )* ;
comparison -> term ( ( ">" | ">=" | "<" | "<=" ) * term )* ;
term = factor ( ("-" | "+") factor )* ;
factor -> unary ( ( "/" | "*") unary )* ;
unary -> ( "! | "-" ) unary | primary ;
primary -> NUMBER | STRING | "true" | "false" | "nil" | "(" expression ")" ;
```

Each rule matches expressions at its precedence level or higher.

## 2 Recursive Descent Parsing

A recursive descent parser is a top-down parser because it starts from the top grammar rule and literally translates the grammar's rules into code.

- Terminal: code to match and consume a token
- Nonterminal: call to that rule's funciton
- `|`: conditional statement
- `*` or `+`: loop statement
- `?`: conditional statement

## 3 Error Handling

When the parser detects an error, it enters panic mode. Before it can get back to parsing, it needs to get its state and the sequence of forthcoming tokens aligned such at the next token matches the current rule. This process is called **synchronizatioin**. Some rules are selected as the synchronization points. Traditional places are between statements. For expression parsing, the parser panics when the grouping rule is broken.
