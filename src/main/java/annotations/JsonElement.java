package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    annotation to mark the fields that we are going to include in the generated JSON.

 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JsonElement {
    String key() default "";
    /*
        The annotation declares one String parameter with the name “key” and an empty string as the default value.
     */
}
