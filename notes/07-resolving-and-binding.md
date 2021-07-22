# Resolving and Binding

## 1 Static Scope

Lox uses **lexical scoping**. A variable reference is determined by the variable declaration in the source code. The binding scope doens't change at runtime. This is called **static scope**: a variable usage refers to the preceding declaraion with the same name in the innermost scope that encloses the epxression where the variable is used.

In Lox implementation, enviornments act like the entire block is one scope. However, the sceope before a variable declaration and the scope after it are different. This is not a static scope. To be a static scope language, every variable declaration should create a new scope.

A **persistent data structure** can never be directly modified. Instead, any “modification” to an existing structure produces a brand new object that contains all of the original data and the new modification. The original is left unchanged. To apply it to the Enviornment, every time you declared a variable it would return a new environment that contained all of the previously declared variables along with the one new name.

In Lox, a function should capture a frozen snapshot of the enviornment as it existed at the moment the function was declared. One way to fix the funciton closure is to use a copy of the environment when it is declared.

## 2 Sementic Analysis

Lox resovles a variable by refering to its declaration each and every time the variable expression is evaluated. A better solution is to resolve each variable use once.

Write a chunk of code that inspects the user’s program, finds every variable mentioned, and figures out which declaration each refers to. This process is an example of a **semantic analysis**. Semantic analysis figures out what pieces of the program actually mean.

Each environment corresponds to a single lexical scope where variables are declared. If we could ensure a variable lookup always walked the same number of links in the environment chain, that would ensure that it found the same variable in the same scope every time.

To “resolve” a variable usage, we only need to calculate how many “hops” away the declared variable will be in the environment chain. It is another way to fix the scope issue.

After the parser produces the syntax tree, but before the interpreter starts executing it, we’ll do a single walk over the tree to resolve all of the variables it contains. Additional passes between parsing and execution are common. If Lox had static types, we could slide a type checker in there. Optimizations are often implemented in separate passes like this too. Basically, any work that doesn’t rely on state that’s only available at runtime can be done in this way.

## Resolver

The variable resolution pass works like a sort of mini-interpreter. It walks the tree, visiting each node, but a static analysis is different from a dynamic execution:

- There are no side effects. When the static analysis visits a print statement, it doesn’t actually print anything. Calls to native functions or other operations that reach out to the outside world are stubbed out and have no effect.
- There is no control flow. Loops are visited only once. Both branches are visited in if statements. Logic operators are not short-circuited.

The resolver needs to visit every node in the syntax tree, it implements the visitor abstraction. Only a few kinds of nodes are interesting when it comes to resolving variables:

- A block statement introduces a new scope for the statements it contains.
- A function declaration introduces a new scope for its body and binds its parameters in that scope.
- A variable declaration adds a new variable to the current scope.
- Variable and assignment expressions need to have their variables resolved.
- The rest of the nodes don’t do anything special, but we still need to implement visit methods for them that traverse into their subtrees. Even though a + expression doesn’t itself have any variables to resolve, either of its operands might.

Each time the resolver visits a variable, it tells the interpreter how many scopes there are between the current scope and the scope where the variable is defined. At runtime, this corresponds exactly to the number of environments between the current one and the enclosing one where the interpreter can find the variable’s value.
