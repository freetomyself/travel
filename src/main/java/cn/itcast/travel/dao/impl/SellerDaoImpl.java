package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @program: travel--cn.itcast.travel.dao.impl
 * @author: WaHotDog 2019-08-01 16:28
 **/


public class SellerDaoImpl  implements SellerDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Seller findSeller(int sid) {
        String sql = "select * from tab_seller where sid = ? ";
        Seller seller = template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class),sid);
        return seller;
    }
}
