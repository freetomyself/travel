package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @program: travel--cn.itcast.travel.service.impl
 * @author: WaHotDog 2019-07-24 16:53
 **/

public class CategoryServiceImpl implements CategoryService {
    private CategoryDao dao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {

        //1.从redis中查询
        //1.1调用jdeis工具
        Jedis jedis = JedisUtil.getJedis();
        //1.2获取category数据
        //Set<String> categorys = jedis.zrange("categorys", 0, -1);
        //1.3使用sortdset中的分数（cid） 和 （cname）
        Set<Tuple> categorys = jedis.zrangeWithScores("categorys", 0, -1);

        List<Category>cs=null;
        //当redis中数据为空
        if (categorys==null || categorys.size()==0){
            System.out.println("从数据库查到");
            cs = dao.findAll();
            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd("categorys",cs.get(i).getCid(),cs.get(i).getCname());
            }
        }else{
            System.out.println("从redis中查到");
            cs = new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);
            }
        }
        return cs;
    }
}
