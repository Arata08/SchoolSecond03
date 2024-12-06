package com.ssm.service;

import com.ssm.domain.Goods;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface GoodsService {
    //查找现在能购买的商品
    public List<Goods> getAll(Integer uid);

    //按id删除
    public boolean delete(Integer id);
    //保存

    public boolean save(Goods goods);

    //修改
    public boolean update(Goods goods);

    //查找除了某id以外的全部
    public List<Goods> getAllExcepte(Integer id);

    //按名字模糊查找
    public List<Goods> getByName(String name);

    //按id查找
    public Goods getById(Integer id);

    List<Goods> getAllWithBusiness(Integer bid);

    List<Goods> getAllWithUser(Integer uid);

    int getBusinessId(String username);

    List<Goods> getAll2();

    List<Goods> getAllByType(String type);

    List<Goods> getAllByTypeAll(String type);

    List<Goods> getAllFromShoppingCar(Integer uid);

    boolean deleteShoppingCar(Integer gid,Integer uid);

    boolean deleteShoppingCarByGid(Integer gid);

    boolean saveShoppingCar(Integer uid,Integer gid);

}
