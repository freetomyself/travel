package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBeen;
import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @program: travel--cn.itcast.travel.service
 * @author: WaHotDog 2019-07-31 14:52
 **/


public interface RouteService {
    public PageBeen<Route> pageQuery(int cid,int currentPage,int pageSize,String rname);


    Route findOne(String rid);

}
