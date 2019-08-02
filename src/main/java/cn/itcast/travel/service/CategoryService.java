package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @program: travel--cn.itcast.travel.service
 * @author: WaHotDog 2019-07-24 16:52
 **/


public interface CategoryService {

    List<Category> findAll();
}
