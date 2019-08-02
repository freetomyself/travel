package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * @program: travel--cn.itcast.travel.dao.impl
 * @author: WaHotDog 2019-08-01 16:14
 **/


public interface RouteImgDao {
    /**
     * 获取图片集合
     * @param rid
     * @return
     */
    List<RouteImg> findByRid(int rid);
}
