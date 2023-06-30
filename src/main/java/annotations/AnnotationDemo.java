package annotations;

public class AnnotationDemo {
    public void execute() {
        /*
            Annotations
                - A form of metadata, provide data about a program that is not part of the program itself.
                - Annotations have no direct effect on the operation of the code they annotate.
                - Annotations have a number of uses, among them:
                    - Information for the compiler — Annotations can be used by the compiler to detect errors or suppress warnings.
                    - Compile-time and deployment-time processing — Software tools can process annotation information to generate code, XML files, and so forth.
                    - Runtime processing — Some annotations are available to be examined at runtime.

            Java Built-in Annotations
                - there are several that inform compilation:
                    @Override
                    @SuppressWarnings
                    @Deprecated
                    @SafeVarargs
                    @FunctionalInterface
                    @Native

            The Format of an Annotation
                - In its simplest form, an annotation looks like the following:
                        @Entity

                - The annotation can include elements, which can be named or unnamed, and there are values for those elements:
                        @Author(
                           name = "Benjamin Franklin",
                           date = "3/27/2003"
                        )
                        class MyClass { ... }

            Where Annotations Can Be Used
                - Annotations can be applied to declarations: declarations of classes, fields, methods, and other
                    program elements. When used on a declaration, each annotation often appears, by convention, on its own line.

            Declaring an Annotation Type
                - The annotation type definition looks similar to an interface definition where the keyword interface is
                    preceded by the at sign (@)

            Example:
            Creating custom annotations
                - For instance, let's imagine that we have an object of type Person that we want to serialize into a
                JSON string. This type has a method that capitalizes the first letter of the first and last names.
                - We'll want to call this method before serializing the object:

                - By using our custom annotations, we're indicating that we can serialize a Person object to a JSON string.
                - In addition, the output should contain only the firstName, lastName, and age fields of that object.
                    Moreover, we want the initNames() method to be called before serialization.
                - By setting the key parameter of the @JsonElement annotation to “personAge,” we are indicating that
                    we'll use this name as the identifier for the field in the JSON output.
                - For the sake of demonstration, we made initNames() private, so we can't initialize our object by
                    calling it manually, and our constructors aren't using it either.

            Processing Annotations
                 -
         */
    }
}
