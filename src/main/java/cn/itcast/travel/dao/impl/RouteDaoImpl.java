package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: travel--cn.itcast.travel.dao.impl
 * @author: WaHotDog 2019-07-31 15:05
 **/


public class RouteDaoImpl implements RouteDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findTotalCount(int cid,String rname) {
//        String sql="select count(*) from tab_route where cid = ? ";
        //定义sql模板
        StringBuilder sb = new StringBuilder(" select count(*) from tab_route where 1=1 ");
        List params = new ArrayList<>();
        if (cid>0){
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (rname!=null && rname.length()>0 && !"null".equals(rname)){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        String sql = sb.toString();
        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int start, int pageSize,String rname) {
        //String sql = "select * from tab_route where cid = ? limit ? , ? ";
        //定义sql模板
        StringBuilder sb = new StringBuilder(" select * from tab_route where 1=1 ");
        List params = new ArrayList<>();
        if (cid>0){
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if (rname!=null && rname.length()>0 && !"null".equals(rname)){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        sb.append(" limit ? ,?  ");
        params.add(start);
        params.add(pageSize);

        String sql = sb.toString();
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ? " ;
        Route route = template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
        return route;
    }
}
