package com.ssm.service.impl;

import com.ssm.dao.GoodsDao;
import com.ssm.domain.Goods;
import com.ssm.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    //根据使用者查找现在他能加入购物车的商品
    @Override
    public List<Goods> getAll(Integer uid) {
        return goodsDao.getAll(uid);
    }

    //查找所有商品
    @Override
    public List<Goods> getAll2() {
        return goodsDao.getAll2();
    }

    //按id删除商品
    @Override
    public boolean delete(Integer id) {
        goodsDao.delete(id);
        return true;
    }

    //插入商品
    @Override
    public boolean save(Goods goods) {
        goodsDao.save(goods);
        return true;
    }

    //更新商品信息
    @Override
    public boolean update(Goods goods) {
        goodsDao.update(goods);
        return true;
    }

    //查找除了某id以外的全部
    @Override
    public List<Goods> getAllExcepte(Integer id) {
        return goodsDao.getAllExcepte(id);
    }

    //按名字模糊查找
    @Override
    public List<Goods> getByName(String name) {
        return goodsDao.getByName(name);
    }

    //按id查找商品
    @Override
    public Goods getById(Integer id) {
        return goodsDao.getById(id);
    }

    //根据商家查找其名下的所有商品
    @Override
    public List<Goods> getAllWithBusiness(Integer bid) {
        return goodsDao.getAllWithBusiness(bid);
    }

    //根据用户查找他的购买的商品
    @Override
    public List<Goods> getAllWithUser(Integer uid) {
        return goodsDao.getAllWithUser(uid);
    }

    //根据商家名字查找它的id
    @Override
    public int getBusinessId(String name) {
        return goodsDao.getBusinessId(name);
    }

    //根据商品类型查找现在能购买的商品
    @Override
    public List<Goods> getAllByType(String type) {
        return goodsDao.getAllByType(type);
    }

    //根据商品类型查找所有的商品（包括已经售出的）
    @Override
    public List<Goods> getAllByTypeAll(String type) {
        return goodsDao.getAllByTypeAll(type);
    }

    //根据用户查找他购物车里面的商品
    @Override
    public List<Goods> getAllFromShoppingCar(Integer uid) {
        return goodsDao.getAllFromShoppingCar(uid);
    }

    //将商品从购物车中删除
    @Override
    public boolean deleteShoppingCar(Integer gid,Integer uid) {
        goodsDao.deleteShoppingCar(gid,uid);
        return true;
    }

    //根据商品id从购物车中删除
    @Override
    public boolean deleteShoppingCarByGid(Integer gid) {
        goodsDao.deleteShoppingCarByGid(gid);
        return true;
    }

    //加入购物车
    @Override
    public boolean saveShoppingCar(Integer uid,Integer gid) {
        goodsDao.saveShoppingCar(uid,gid);
        return true;
    }

}
