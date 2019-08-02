package cn.itcast.travel.test;

import java.lang.annotation.*;

/**
 * @program: travel--cn.itcast.travel.test
 * @author: WaHotDog 2019-07-19 10:01
 **/

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Annotation1 {
    String className();
    String methodName();

}
