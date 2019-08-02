package cn.itcast.travel.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @program: travel--cn.itcast.travel.test
 * @author: WaHotDog 2019-07-19 14:32
 **/

@Annotation1(className = "cn.itcast.travel.test.Demo1",methodName = "sleep")
public class DoAny {

    public static void main(String[] args) throws Exception {
        //谁加注解就去获取谁
        Class<DoAny> doAny = DoAny.class;
        Annotation1 annotation1 = doAny.getAnnotation(Annotation1.class);
        String className = annotation1.className();
        String methodName = annotation1.methodName();

        Class cls = Class.forName(className);
        Object o = cls.newInstance();
        Method method = cls.getMethod(methodName);
        method.invoke(o);


    }
}
