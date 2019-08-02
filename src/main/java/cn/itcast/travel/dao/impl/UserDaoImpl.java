package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @program: travel--cn.itcast.travel.dao.impl
 * @author: WaHotDog 2019-07-15 17:14
 **/


public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询用户名是否存在
     *
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            //定义sql
            String sql = "select * from tab_user where username = ? ";
            //执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (DataAccessException e) {
            return null;
        }
        return user;
    }

    /**
     * 查询邮箱是否存在
     *
     * @param email
     * @return
     */
    @Override
    public User findByEmail(String email) {
        User user = null;
        try {
            //定义sql
            String sql = "select * from tab_user where email = ?";
            //执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), email);
        } catch (DataAccessException e) {
            return null;
        }
        return user;
    }

    /**
     * 添加用户
     *
     * @param user
     */
    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        template.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail(), user.getStatus(), user.getCode());
    }

    @Override
    public User findByCode(String code) {

        User user = null;
        try {
            String sql ="select * from tab_user where code = ? ";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (DataAccessException e) {
            return null;
        }
        return user;
    }

    @Override
    public void updataStatus(String code) {
        String sql ="update tab_user set status = ? where code = ? ";
        template.update(sql,"Y" ,code);
    }

    @Override
    public User login(User user) {
        try {
            String sql ="select * from tab_user where username = ? and password = ?";
            User u = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUsername(), user.getPassword());
            return u;
        } catch (DataAccessException e) {
            return null;
        }
    }
}
