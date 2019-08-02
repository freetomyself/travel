package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

/**
 * @program: travel--cn.itcast.travel.dao
 * @author: WaHotDog 2019-08-02 11:43
 **/


public interface FavoriteDao {

    public Favorite findUidByRid(int uid, int rid);

    public int findCountByRid(int rid);

    public void addFavorite(int rid,int id);
}
