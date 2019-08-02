package cn.itcast.travel.service;

/**
 * @program: travel--cn.itcast.travel.service
 * @author: WaHotDog 2019-08-02 10:18
 **/


public interface FavoriteService {

    /**
     * 判断收费收藏
     * @param rid
     * @param uid
     * @return
     */
    public boolean isFavorite(String rid,int uid);

    public void addFavorite(int rid,int uid);


}
