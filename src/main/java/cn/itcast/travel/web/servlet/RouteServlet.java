package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBeen;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: travel--${PACKAGE_NAME}
 * @author: WaHotDog 2019-07-31 11:48
 **/


@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rname = request.getParameter("rname");

        //用于解决rname乱码问题
        rname = new String(rname.getBytes("ISO-8859-1"), "utf-8");

        //处理参数
        int cid = 0;//类别
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0;//当前页码，不传默认为1
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        int pageSize = 0;//每页显示条数，不传默认为5条
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }

        //3调用service查询pageBean对象
        PageBeen<Route> pb = routeService.pageQuery(cid, currentPage, pageSize, rname);

        //4将pageBean对象序列化为json返回
        writeValue(pb, response);

    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取旅游项目rid
        String rid = request.getParameter("rid");
        //封装Route
        Route route = routeService.findOne(rid);
        //返回json数据
        writeValue(route, response);

    }

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取线路id
        String rid = request.getParameter("rid");
        //2.获取当前登录用户user
        User user = (User) request.getSession().getAttribute("name");
        int uid;
        if (user==null){
            //尚未登录
            uid=0;
        }else{
            //用户已登录
            uid = user.getUid();
        }
        //3.调用FavoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);
        //返回是否收藏

        writeValue(flag,response);
    }
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取线路id
        String rid = request.getParameter("rid");
        //2.获取当前登录用户user
        User user = (User) request.getSession().getAttribute("name");
        int uid;
        if (user==null){
            //尚未登录
            return ;
        }else{
            //用户已登录
            uid = user.getUid();
        }
        favoriteService.addFavorite(Integer.parseInt(rid),user.getUid());
    }
}
