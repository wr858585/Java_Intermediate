package com.oono.java1;

import java.lang.annotation.*;

/**
 * @author oono
 * @date 2020 07 26
 */

@Inherited
@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.CONSTRUCTOR,ElementType.TYPE_PARAMETER,ElementType.TYPE_USE})
public @interface MyAnnotation {

    String value() default "hello";

}
