package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 * @program: travel--cn.itcast.travel.dao
 * @author: WaHotDog 2019-08-01 16:26
 **/


public interface SellerDao {
    /**
     * 通过商家id查询商家信息
     * @param sid
     * @return
     */
    Seller findSeller(int sid);
}
