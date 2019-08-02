package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: travel--${PACKAGE_NAME}
 * @author: WaHotDog 2019-07-18 08:37
 **/


public class BaseServlet extends HttpServlet {

    /**
     * 实现service对servlet的分发
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("BaseServlet的service方法被执行了...");
        //可以再service中实现方法的分发  路径为 travel/user/add
        //1.获取请求路径
        String uri = req.getRequestURI();
        System.out.println("请求uri：" + uri); //travel/user/add
        //2.获取请求方
        String methodName = uri.substring(uri.lastIndexOf("/") + 1);
        System.out.println("方法名称：" + methodName);
        //3.获取方法对象Method
        //谁调用我我代表谁
        System.out.println(this);
        try {
            //getDeclaredMethod 忽略访问权限修饰符
            //Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            try {
                //暴力反射
                //method.setAccessible(true);
                //4.执行方法
                method.invoke(this, req, resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     *直接将传入的数据序列化为json返回客户端
     * @param obj
     * @param response
     */
    public void writeValue(Object obj,HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),obj);
    }

    /**
     * 将传入对象序列化为json，返回调用者
     * @param obj
     * @return
     * @throws JsonProcessingException
     */
    public String writeAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
