package com.ssm.service;

import com.ssm.config.SpringConfig;
import com.ssm.dao.GoodsDao;
import com.ssm.domain.Business;
import com.ssm.domain.Goods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class CourseServiceTest {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Test
    public void GetAll(){
        List<Goods> all = goodsService.getAll(1);
        System.out.println(all);
    }

    @Test
    public void Save(){
        Goods goods = new Goods();
        goods.setName("小米");
        goods.setPrice(1000);
        goods.setType("手机");
        goods.setDescription("性价比");
        goods.setGoodsPic("img/1.jpg");
        goodsService.save(goods);
        System.out.println("保存成功");
    }

    @Test
    public void Delete(){
        goodsService.delete(4);
        System.out.println("删除成功");
    }

    @Test
    public void getById(){
        Goods goods = goodsService.getById(1);
        System.out.println(goods);
    }

    @Test
    public void getAllExcepte(){
        List<Goods> allExcepte = goodsService.getAllExcepte(1);
        System.out.println(allExcepte);
    }

    @Test
    public void getByName(){
        List<Goods> byName = goodsService.getByName("雨");
        System.out.println(byName);
    }

    @Test
    public void Update(){
        Goods goods = new Goods();
        goods.setId(5);
        goods.setName("小米");
        goods.setPrice(1888);
        goods.setType("手机");
        goods.setDescription("性价比!!!");
        goods.setGoodsPic("img/1.jpg");
        goodsService.update(goods);
        System.out.println("修改成功");
    }

    @Test
    public void selectBusinessByUsername (){
        Business business = new Business();
        business.setUsername("奸商");
        business.setPassword("123456");
        Business business1 = businessService.login(business);
        System.out.println(business1);
    }

    @Test
    public void insertBusiness (){
        Business business = new Business();
        business.setUsername("奸商2");
        business.setPassword("123456");
        businessService.register(business);
    }

    @Test
    public void getAllWithBusiness(){
        List<Goods> allWithBusiness = goodsService.getAllWithBusiness(1);
        System.out.println(allWithBusiness);
    }

    @Test
    public void getAllWithUser(){
        List<Goods> allWithUser = goodsService.getAllWithUser(1);
        System.out.println(allWithUser);
    }

    @Test
    public void getBusinessId(){
        System.out.println(goodsService.getBusinessId("222"));
    }

    @Test
    public void getAll2(){
        System.out.println(goodsService.getAll2());
    }

    @Test
    public void getByIdForUser(){
        System.out.println(userService.selectUserById(1));
    }

    @Test
    public void getAllFromShoppingCar(){
        System.out.println(goodsService.getAllFromShoppingCar(1));
    }

    @Test
    public void saveShoppingCar(){
        System.out.println(goodsService.saveShoppingCar(1,1));
    }

    @Test
    public void deleteShoppingCar(){
        System.out.println(goodsService.deleteShoppingCar(21,2));
    }

    @Test
    public void selectAdminByUsername(){
        System.out.println(adminService.selectAdminByUsername("admin"));
    }

    @Test
    public void selectAll(){
        System.out.println(adminService.selectAll());
    }
}
