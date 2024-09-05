package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    - add meta-annotations to specify the scope and the target of our custom annotation
    - here it has runtime visibility and we can apply it to types (classes).
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JsonSerializable {
    /*
        This serves as a simple marker to mark classes that can be serialized into JSON.
     */
}
