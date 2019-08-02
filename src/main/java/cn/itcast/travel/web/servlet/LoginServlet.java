package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.mail.Session;
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
 * @author: WaHotDog 2019-07-16 16:10
 **/


@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
