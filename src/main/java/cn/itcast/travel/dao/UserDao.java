package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @program: travel--cn.itcast.travel.dao
 * @author: WaHotDog 2019-07-15 17:13
 **/


public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 根据邮箱查询用户信息
     * @param email
     * @return
     */
    public User findByEmail(String email);

    /**
     * 用户保存
     * @param user
     * @return
     */
    public void save(User user);

    /**
     * 验证识别码
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 邮箱激活
     * @param code
     */
    void updataStatus(String code);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);
}
