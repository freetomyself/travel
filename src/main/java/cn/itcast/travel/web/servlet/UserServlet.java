package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @program: travel--${PACKAGE_NAME}
 * @author: WaHotDog 2019-07-18 08:36
 **/


@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    /**
     * 邮箱激活
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        UserService service = new UserServiceImpl();
        String msg =null;
        if (service.checkCode(code)!=null){
            service.updateStatus(code);
            msg="激活成功，<a href='login.html'>立刻登录<a>";
        }else{
            msg="激活失败，请联系管理员";
        }
//        response.setContentType("text/html;charset=utf-8");
//        response.getWriter().write(msg);
        writeValue(msg,response);
    }

    /**
     * 用户退出
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1销毁session
        request.getSession().invalidate();
        //2页面跳转
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    /**
     * 返回登录用户的信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session中的用户名
        HttpSession session = request.getSession();
        User name = (User) session.getAttribute("name");
        //通过序列化将name转为json格式
        /*ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),name);*/
        writeValue(name,response);
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取登录用户数据
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        ResultInfo info = new ResultInfo();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //判断验证码是否正确
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (check!=null && check.equalsIgnoreCase(checkcode_server)){
            UserService service = new UserServiceImpl();
            User u = service.login(user);
            //判断用户是否存在，状态是否激活
            if(u!=null){
                if ("Y".equalsIgnoreCase(u.getStatus())){
                    session.setAttribute("name",u);
                    info.setFlag(true);
                }else{
                    info.setFlag(false);
                    info.setErrorMsg("请前往邮箱激活账号！");
                }
            }else{
                info.setFlag(false);
                info.setErrorMsg("账号或密码错误！");
            }

        }else{
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
        }
        //通过序列化将数据转为json格式
        /*ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);*/
        writeValue(info,response);
    }

    /**
     * 用户注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        ResultInfo info = new ResultInfo();
        if (check!=null && check.equalsIgnoreCase(checkcode_server)){
            //1接收前端传的数据
            Map<String, String[]> map = request.getParameterMap();
            //2封装对象
            User user = new User();
            System.out.println(user.toString());
            try {
                BeanUtils.populate(user,map);
                System.out.println(user.toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //3调用service邮箱验证
            UserService service = new UserServiceImpl();
            if (service.checkEmail(user.getEmail())==null) {
                //4调用service完成注册
                if (service.checkEmail(user.getEmail()) != null) {

                }
                boolean flag = service.regist(user);
                //4响应结果
                if (flag) {
                    info.setFlag(true);
                } else {
                    info.setFlag(false);
                    info.setErrorMsg("注册失败，用户名已被使用！");
                }
            }else{
                info.setFlag(false);
                info.setErrorMsg("1.此邮箱已有账户，请勿重复注册！/2.未在邮箱进行激活！");
            }
        }else{
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
        }
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(info);
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(json);
        writeValue(info,response);
    }

}
