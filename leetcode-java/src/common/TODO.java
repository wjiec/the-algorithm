package common;

import java.lang.annotation.Repeatable;

@Repeatable(value = TODO.TODOs.class)
public @interface TODO {

    // reference url
    String url() default "";

    // tips for problem
    String tips() default "";

    @interface TODOs {
        TODO[] value() default {};
    }

}
