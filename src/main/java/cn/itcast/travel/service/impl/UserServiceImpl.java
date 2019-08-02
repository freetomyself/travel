package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

/**
 * @program: travel--cn.itcast.travel.service.impl
 * @author: WaHotDog 2019-07-15 17:13
 **/


public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        //1根据用户名查询用户对象
        User u = dao.findByUsername(user.getUsername());
        //2保存用户是否为null
        if (u!=null){
            //用户名存在
            return false;
        }else {
            //1产生唯一识别码
            user.setCode(UuidUtil.getUuid());
            //2产生状态码
            user.setStatus("N");
            dao.save(user);
            //3发送邮件
            //此处需要注意url需要要随服务器变化而变化
            String content = "<a href='http://localhost:8080/travel/activeUserServlet?code=" + user.getCode()+ "'>点击激活【黑马旅游网】</a>";
            System.out.println(content);
            MailUtils.sendMail(user.getEmail(),content,"【黑马旅游网】激活邮件");
            return true;
        }
    }

    @Override
    public User checkEmail(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public User checkCode(String code) {
        return dao.findByCode(code);
    }

    @Override
    public void updateStatus(String code) {
        dao.updataStatus(code);
    }

    @Override
    public User login(User user) {
        return dao.login(user);
    }
}
