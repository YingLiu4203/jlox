# Classes

## 1 OOP

There are three broad paths to object-oriented programming: **classes**, **prototypes**, and **multimethods**.

In Lox, a class declaration is the class keyword, followed by the class’s name, then a curly-braced body. Inside that body is a list of method declarations. Unlike function declarations, methods don’t have a leading `fun` keyword. Each method is a name, parameter list, and body. Like most dynamically typed languages, fields are not explicitly listed in the class declaration. Instances are loose bags of data and you can freely add fields to them as you see fit using normal imperative code.

In Lox, we use two new runtime entities, `LoxClass` and `LoxInstance`. The former is where behavior for objects lives, and the latter is for state.

We could define methods right on a single object, inside `LoxInstance`. We could let a `LoxInstance` object delegates directly to another `LoxInstance` object to reuse its fields and methods, sort of like inheritance. Objects used as delegates represent “canonical” or “prototypical” objects that others refine. This paramdigm is called "prototype".

Prototypes are simpler than classes—less code for the language implementer to write, and fewer concepts for the user to learn and understand. But the tradeoffs are **breadth**, **ease** and **simplicity**. It depends on how users use the language.

## 2 Implementation

Like most dynamically typed languages, fields are not explicitly listed in the class declaration. Instances are loose bags of data and you can freely add fields to them as you see fit using normal imperative code.

We already have class objects, and we already have function calls, so we’ll use call expressions on class objects to create new instances. It’s as if a class is a factory function that generates instances of itself.

The property dispatch in Lox is dynamic since it is evaluated in runtime interpreter, not in the static resolver.

Lox storeds fields in a hash map. Doing a hash table lookup for every field access is fast enough for many language implementations, but not ideal. High performance VMs for languages like JavaScript use sophisticated optimizations

Lox allows `var m = object.method; m(argument);` that the getter `.` and call `()` can be pulled apart.

Like Python, C#, and others, we will have methods “bind” this to the original instance when the method is first grabbed. Python calls these **bound methods**. In practice, that’s usually what you want. If you take a reference to a method on some object so you can use it as a callback later, you want to remember the instance it belonged to, even if that callback happens to be stored in a field on some other object.

## 3 `this`

If we defined `this` as a sort of hidden variable in an environment that surrounds the function returned when looking up a method, then uses of this in the body would be able to find it later. LoxFunction already has the ability to hold on to a surrounding environment, so we have the machinery we need.

When we evaluate an object's `get` expression, we create a new environment that binds `this` to the object the method is accessed from. Then we make a new `LoxFunction` with the same code as the original one but using that new environment as its closure.

The resolver has a new scope for `this`, so the interpreter needs to create a corresponding environment for it. Remember, we always have to keep the resolver’s scope chains and the interpreter’s linked environments in sync with each other. At runtime, we create the environment after we find the method on the instance.

## 4 Constructors and Initializers

Constructor is one of the trickest parts of a language to design. We use `init` as the initialization method name.

We statically disallow returning a value from an initializer, but you can still use an empty early `return`.
