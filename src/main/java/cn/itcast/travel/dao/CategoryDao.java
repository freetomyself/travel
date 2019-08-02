package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @program: travel--cn.itcast.travel.dao
 * @author: WaHotDog 2019-07-24 16:44
 **/


public interface CategoryDao {

    List<Category>findAll();
}
