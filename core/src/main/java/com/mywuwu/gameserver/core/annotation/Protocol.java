package com.mywuwu.gameserver.core.annotation;


import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Protocol {
    int value();
}