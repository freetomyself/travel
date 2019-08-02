package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

/**
 * @program: travel--cn.itcast.travel.dao.impl
 * @author: WaHotDog 2019-08-02 11:44
 **/


public class FavoriteDaoImpl implements FavoriteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favorite findUidByRid(int uid, int rid) {
        String sql ="select * from tab_favorite where uid = ? and rid = ? ";
        Favorite favorite=null;
        try {
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), uid, rid);
            return favorite;
        }catch (Exception e){
            return favorite;
        }
    }

    @Override
    public int findCountByRid(int rid) {
        String sql = "select count(*) from tab_favorite  where rid = ? ";
        int count;
        try{
            count = template.queryForObject(sql,Integer.class,rid);
        }catch (Exception e){
            count = 0;
        }
        return count;
    }

    @Override
    public void addFavorite(int rid ,int uid) {
        String sql ="insert into tab_favorite values(?,?,?) ";
        template.update(sql,rid,new Date(),uid);
    }
}
