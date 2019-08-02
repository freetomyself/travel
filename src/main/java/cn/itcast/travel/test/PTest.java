package cn.itcast.travel.test;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 用于测试专用
 * @program: travel--cn.itcast.travel.test
 * @author: WaHotDog 2019-07-18 15:29
 * @since 1.5
 **/


public class PTest {

    @Test
    public void personTest() throws Exception {
        Class p = Person.class;
        Field[] fields = p.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        System.out.println("-------------");
        Field a = p.getField("a");
        Person person = new Person();
        Object o = a.get(person);
        System.out.println(o);
        a.set(person,"hehe");
        System.out.println(person);
        System.out.println("===============");
        Field[] fields1 = p.getDeclaredFields();
        for (Field field : fields1) {
            System.out.println(field);
        }
        Field d = p.getDeclaredField("name");
        //忽略访问权限修饰符的安全检查
        //暴力映射
        d.setAccessible(true);
        Object o1 = d.get(person);
        System.out.println(o1);
        d.set(person,"123");
        System.out.println(person);

    }
    @Test
    public void Ptest1() throws Exception{
        Class personClass = Person.class;
        Constructor constructor = personClass.getConstructor(String.class, int.class);
        System.out.println(constructor);
        Object obj = constructor.newInstance("张三", 1);
        System.out.println(obj);
        System.out.println("================");
        Object newInstance = personClass.newInstance();
        System.out.println(newInstance);
        Constructor constructor1 = personClass.getConstructor();
        Object o = constructor1.newInstance();
        System.out.println(o);
    }

    @Test
    public void Ptest2() throws Exception{
        Class.forName("cn.itcast.travel.test.Person");
        Class personClass = Person.class;
        Person person = new Person();
        Class p = person.getClass();
        System.out.println(p);
        System.out.println("=====================");
        Method[] methods = personClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("=====================");
        Method[] declaredMethods = personClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
        }
        System.out.println("======================");
        String name = personClass.getName();
        System.out.println(name);

    }
    @Test
    public void Ptest3() throws Exception {
        //1加载配置文件
        //1.1创建properties
        Properties properties = new Properties();
        //1.2获取文件路径
        InputStream is = PTest.class.getClassLoader().getResourceAsStream("pro.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2获取配置文件中的配置
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");
        //3将方法添加到内存
        Class cls = Class.forName(className);
        Object o = cls.newInstance();
        Method method = cls.getMethod(methodName);
        method.invoke(o);
    }


}
