package com.oono.java1;

import java.lang.annotation.*;

/**
 * @author oono
 * @date 2020 07 27
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.CONSTRUCTOR})
public @interface MyAnnotations {

    MyAnnotation[] value();

}
