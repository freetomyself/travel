package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

/**
 * @program: travel--cn.itcast.travel.service
 * @author: WaHotDog 2019-07-15 17:12
 **/


public interface UserService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    boolean regist(User user);

    /**
     * 判断邮箱是否使用
     * @param email
     * @return
     */
    User checkEmail(String email);

    /**
     * 激活码核对
     * @param code
     * @return
     */
    User checkCode(String code);

    /**
     * 激活邮箱
     * @param code
     */
    void updateStatus(String code);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);
}
