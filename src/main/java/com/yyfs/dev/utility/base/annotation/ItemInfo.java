package com.yyfs.dev.utility.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface ItemInfo {

	boolean isReqired() default false;

	String name() default "";

	String valueType();

	String value() default "";

	long minLength() default 0;

	long maxLength() default 0;

	String format() default "";
}
