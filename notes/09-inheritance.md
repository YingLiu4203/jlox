# Inheritance

Lox uses `<` to define a subclass inheriting from a single superclass. There is no root "Object" class.

A `super` call is a super access followed by a function call. The lookup should start on the superclass of the class containing the super expression. We could add a field to `LoxFunction` to store a reference to the LoxClass that owns that method. But that's a lot of plumbing.

With super expressions, the superclass is a fixed property of the class declaration itself. Every time you evaluate some super expression, the superclass is always the same. That means we can create the environment for the superclass once, when the class definition is executed. Immediately before we define the methods, we make a new environment to bind the class’s superclass to the name `super`.
