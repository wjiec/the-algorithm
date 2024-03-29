package common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Tag {

    // tips for problem, required
    String[] value();

    @interface Tags {
        Tag[] value() default {};
    }

}
