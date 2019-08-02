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
 * @author: WaHotDog 2019-07-15 16:51
 **/


@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
