package com.ssm.dao;

import com.ssm.domain.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodsDao {

    @Select("select * from goods join business on goods.bid = business.id where goods.uid = 0 and goods.id not in (select gid from shoppingcar where uid = #{uid})")
    @Results({                                 //映射查询结果集到实体类属性
            @Result(property = "id",column = "id"),
            @Result(property = "price",column = "price"),
            @Result(property = "name",column = "name"),
            @Result(property = "type",column = "type"),
            @Result(property = "goodsPic",column = "goodsPic"),
            @Result(property = "description",column = "description"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "bid",column = "bid"),
            @Result(property = "delivery",column = "delivery"),
            @Result(property = "businessname",column = "bid",one = @One(select = "com.ssm.dao.GoodsDao.getBusinessName"))//通过bid查找商家昵称
    })
    public List<Goods> getAll(Integer uid);    //根据使用者查找现在他能加入购物车的商品

    @Select("select * from goods join business on goods.bid = business.id")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "price",column = "price"),
            @Result(property = "name",column = "name"),
            @Result(property = "type",column = "type"),
            @Result(property = "goodsPic",column = "goodsPic"),
            @Result(property = "description",column = "description"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "bid",column = "bid"),
            @Result(property = "delivery",column = "delivery"),
            @Result(property = "businessname",column = "bid",one = @One(select = "com.ssm.dao.GoodsDao.getBusinessName")),
            @Result(property = "username",column = "uid",one = @One(select = "com.ssm.dao.GoodsDao.getUserName"))
    })
    public List<Goods> getAll2();    //查找所有商品

    @Select("select * from goods join business on goods.bid = business.id where type = #{type} and uid = 0 and goods.id not in (select gid from shoppingcar where uid = #{uid})")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "price",column = "price"),
            @Result(property = "name",column = "name"),
            @Result(property = "type",column = "type"),
            @Result(property = "goodsPic",column = "goodsPic"),
            @Result(property = "description",column = "description"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "bid",column = "bid"),
            @Result(property = "delivery",column = "delivery"),
            @Result(property = "businessname",column = "bid",one = @One(select = "com.ssm.dao.GoodsDao.getBusinessName")),
            @Result(property = "username",column = "uid",one = @One(select = "com.ssm.dao.GoodsDao.getUserName"))
    })
    public List<Goods> getAllByType(String type);    //根据商品类型查找现在能购买的商品

    @Select("select * from goods join business on goods.bid = business.id where type = #{type}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "price",column = "price"),
            @Result(property = "name",column = "name"),
            @Result(property = "type",column = "type"),
            @Result(property = "goodsPic",column = "goodsPic"),
            @Result(property = "description",column = "description"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "bid",column = "bid"),
            @Result(property = "delivery",column = "delivery"),
            @Result(property = "businessname",column = "bid",one = @One(select = "com.ssm.dao.GoodsDao.getBusinessName")),
            @Result(property = "username",column = "uid",one = @One(select = "com.ssm.dao.GoodsDao.getUserName"))
    })
    public List<Goods> getAllByTypeAll(String type);//根据商品类型查找所有的商品（包括已经售出的）

    @Select("select * from goods join business on goods.bid = business.id where bid = #{bid}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "price",column = "price"),
            @Result(property = "name",column = "name"),
            @Result(property = "type",column = "type"),
            @Result(property = "goodsPic",column = "goodsPic"),
            @Result(property = "description",column = "description"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "bid",column = "bid"),
            @Result(property = "delivery",column = "delivery"),
            @Result(property = "businessname",column = "bid",one = @One(select = "com.ssm.dao.GoodsDao.getBusinessName")),
            @Result(property = "username",column = "uid",one = @One(select = "com.ssm.dao.GoodsDao.getUserName"))
    })
    public List<Goods> getAllWithBusiness(Integer bid);        //根据商家查找其名下的所有商品

    @Select("select * from goods join user on goods.uid = user.id where uid = #{uid}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "price",column = "price"),
            @Result(property = "name",column = "name"),
            @Result(property = "type",column = "type"),
            @Result(property = "goodsPic",column = "goodsPic"),
            @Result(property = "description",column = "description"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "bid",column = "bid"),
            @Result(property = "delivery",column = "delivery"),
            @Result(property = "businessname",column = "bid",one = @One(select = "com.ssm.dao.GoodsDao.getBusinessName")),
            @Result(property = "username",column = "uid",one = @One(select = "com.ssm.dao.GoodsDao.getUserName"))
    })
    public List<Goods> getAllWithUser(Integer uid);           //根据用户查找他的购买的商品

    @Select("select * from goods join shoppingcar on goods.id = gid where shoppingcar.uid = #{uid}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "price",column = "price"),
            @Result(property = "name",column = "name"),
            @Result(property = "type",column = "type"),
            @Result(property = "goodsPic",column = "goodsPic"),
            @Result(property = "description",column = "description"),
            @Result(property = "uid",column = "uid"),
            @Result(property = "bid",column = "bid"),
            @Result(property = "delivery",column = "delivery"),
            @Result(property = "businessname",column = "bid",one = @One(select = "com.ssm.dao.GoodsDao.getBusinessName")),
            @Result(property = "username",column = "uid",one = @One(select = "com.ssm.dao.GoodsDao.getUserName"))
    })
    public List<Goods> getAllFromShoppingCar(Integer uid);       //根据用户查找他购物车里面的商品

    //将商品从购物车中删除
    @Delete("delete from shoppingcar where gid=#{gid} and uid=#{uid}")
    public boolean deleteShoppingCar(@Param("gid") Integer gid,@Param("uid") Integer uid);

    //根据商品id从购物车中删除
    @Delete("delete from shoppingcar where gid=#{gid}")
    public boolean deleteShoppingCarByGid(Integer gid);

    //通过商家的id找他的名字
    @Select("select name from business where id = #{bid}")
    public String getBusinessName(Integer bid);

    //按id删除商品
    @Delete("delete from goods where id=#{id}")
    public boolean delete(Integer id);

    //插入商品
    @Insert("insert into goods(name,price,type,goodsPic,description,bid) values(#{name},#{price},#{type},#{goodsPic},#{description},#{bid})")
    public boolean save(Goods goods);

    //加入购物车
    @Insert("insert into shoppingcar(uid,gid) values(#{uid},#{gid})")
    public boolean saveShoppingCar(@Param("uid") Integer uid, @Param("gid") Integer gid);

    //修改
    @Update("update goods set name=#{name},price=#{price},goodsPic=#{goodsPic},description=#{description},uid=#{uid},bid=#{bid},delivery=#{delivery},type=#{type} where id=#{id}")
    public boolean update(Goods goods);

    //查找除了某id以外的全部
    @Select("select * from goods where id != #{id}")
    public List<Goods> getAllExcepte(Integer id);

    //按名字模糊查找
    @Select("select * from goods where name like CONCAT('%', #{name}, '%')")
    public List<Goods> getByName(String name);

    //按id查找商品
    @Select("select * from goods where id=#{id}")
    public Goods getById(Integer id);

    //根据用户id查找它的名字
    @Select("select username from user where id = #{uid}")
    public String getUserName(Integer uid);

    //根据商家名字查找它的id
    @Select("select id from business where name= #{name}")
    public int getBusinessId(String name);
}
