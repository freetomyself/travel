package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

/**
 * @program: travel--cn.itcast.travel.service.impl
 * @author: WaHotDog 2019-08-02 11:42
 **/


public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = favoriteDao.findUidByRid(uid, Integer.parseInt(rid));
        return  favorite != null ? true : false;
    }

    @Override
    public void addFavorite(int rid,int uid) {
        favoriteDao.addFavorite(rid,uid);
    }
}
