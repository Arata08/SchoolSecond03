package com.ssm.controller;

import com.ssm.domain.Goods;
import com.ssm.domain.Info;
import com.ssm.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    //图片上传
    @PostMapping("/upload")
    public Result upload(@RequestParam(value = "file") MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + "_" + originalFileName; // 使用UUID重命名文件以避免冲突
        String uploadDir = "D:\\Documents\\JavaProjects\\school-second\\SchoolSecond03\\src\\main\\webapp\\pages\\upload";
        File dir = new File(uploadDir);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(dir, fileName));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        temp.imageUrl = "../upload/" + fileName; // 设置相对路径
        return null;
    }


    //图片相对路径存入数据库，保存商品
    @PostMapping
    public Result save(@RequestBody Goods goods) throws IOException {
        boolean flag = false;
        goods.setDelivery("未售出");
        goods.setGoodsPic(temp.imageUrl);
        if(goods.getGoodsPic()==null){
            goods.setGoodsPic("../image/R-C.jpg");   //如果没有图片的路径则存下默认路径
        }
        flag = goodsService.save(goods);
        temp.imageUrl = null;
        System.out.println(goods);
        return new Result(flag ? Code.SAVE_OK:Code.SAVE_ERR,flag);
    }

    //更新商品信息
    @PutMapping
    public Result update(@RequestBody Goods goods) throws IOException {
        boolean flag = false;
        if(!goods.getDelivery().equals("未售出")){                 //已经售出的商品无法修改
            return new Result(Code.DELIVERY_OK,false,"商品已售出，无法修改！");
        }
        if(temp.imageUrl==null){                       //如果图片路径为空，获取图片路径
            temp.imageUrl = goods.getGoodsPic();
        }
        goods.setGoodsPic(temp.imageUrl);
        flag = goodsService.update(goods);
        temp.imageUrl = null;
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,flag);
    }

    //删除商品
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        Goods goods = goodsService.getById(id);
        if(!goods.getDelivery().equals("未售出")){    //已经售出的商品无法删除
            return new Result(Code.DELETE_ERR,false,"商品已售出，无法下架！");
        }
        boolean flag = goodsService.delete(id);       //从数据库里面删除
        goodsService.deleteShoppingCarByGid(id);        //从购物车里面删除
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

    //从购物车里面删除商品
    @DeleteMapping("/deleteFromShoppingCar/{gid}/{uid}")
    public Result deleteFromShoppingCar(@PathVariable Integer gid,@PathVariable Integer uid) {
        boolean flag = goodsService.deleteShoppingCar(gid,uid);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

    //按id查找商品
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Goods goods = goodsService.getById(id);
        Integer code = goods != null ? Code.GET_OK : Code.GET_ERR;
        String msg = goods !=null ? "" : "数据查询失败，请重试!";
        return new Result(code, goods,msg);
    }

    //购买
    @PostMapping("/shopping")
    public Result shopping(@RequestBody Info info) {
        Goods goods = goodsService.getById(info.getId());
        if (goods.getDelivery().equals("未售出")) {
            goods.setUid(info.getUid());
            goods.setDelivery("未发货");               //购买成功
        } else {
            return new Result(Code.BUY_ERR,false,"来晚了,商品已售出");
        }
        boolean flag = goodsService.update(goods);
        goodsService.deleteShoppingCar(info.getId(),info.getUid());
        Integer code = flag != false ? Code.BUY_OK : Code.BUY_ERR;
        String msg = flag != false ? "" : "购买失败，请重试!";
        return new Result(code,goods,msg);
    }

    //加入购物车
    @PostMapping("/shoppingCar")
    public Result shoppingCar(@RequestBody Info info) {
        System.out.println(info);
        //先查询是否已经存在购物车里
        if (goodsService.getShoppingCar(info.getUid(),info.getGid())!=null){
            return new Result(Code.CAR_ERR,false,"购物车中已存在该商品");
        }
        System.out.println(goodsService.getShoppingCar(info.getUid(),info.getGid()));
        boolean flag = goodsService.saveShoppingCar(info.getUid(),info.getGid());
        Integer code = flag ? Code.CAR_OK : Code.CAR_ERR;
        String msg = flag ? "" : "加入失败，请重试!";
        return new Result(code,null,msg);
    }

    //根据使用者查找现在他能加入购物车的商品
    @GetMapping("/getAll/{id}")
    public Result getAll(@PathVariable Integer id) {
        List<Goods> goodsList = goodsService.getAll(id);
        Integer code = goodsList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = goodsList !=null ? "" : "数据查询失败，请重试!";
        return new Result(code,goodsList,msg);
    }

    //查找所有商品
    @GetMapping("/getAll2")
    public Result getAll2() {
        List<Goods> goodsList = goodsService.getAll2();
        Integer code = goodsList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = goodsList !=null ? "" : "数据查询失败，请重试!";
        return new Result(code,goodsList,msg);
    }

    //根据用户查找他购物车里面的商品
    @GetMapping("/getAllFromShoppingCar/{uid}")
    public Result getAllFromShoppingCar(@PathVariable Integer uid) {
        List<Goods> goodsList = goodsService.getAllFromShoppingCar(uid);
        Integer code = goodsList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = goodsList !=null ? "" : "数据查询失败，请重试!";
        return new Result(code,goodsList,msg);
    }

    //根据商家查找其名下的所有商品
    @GetMapping("/getAllWithBusiness/{id}")
    public Result getAllWithBusiness(@PathVariable Integer id){
        List<Goods> allWithBusiness = goodsService.getAllWithBusiness(id);
        Integer code = allWithBusiness != null ? Code.GET_OK : Code.GET_ERR;
        String msg = allWithBusiness !=null ? "" : "数据查询失败，请重试!";
        return new Result(code,allWithBusiness,msg);
    }

    //根据用户查找他的购买的商品
    @GetMapping("/getAllWithUser/{id}")
    public Result getAllWithUser(@PathVariable Integer id){
        List<Goods> allWithUser = goodsService.getAllWithUser(id);
        Integer code = allWithUser != null ? Code.GET_OK : Code.GET_ERR;
        String msg = allWithUser !=null ? "" : "数据查询失败，请重试!";
        return new Result(code,allWithUser,msg);
    }

    //根据商品类型查找现在能购买的商品
    @GetMapping("/getAllByType/{type}")
    public Result getAllByType(@PathVariable String type){
        List<Goods> allByType = goodsService.getAllByType(type);
        Integer code = allByType != null ? Code.GET_OK : Code.GET_ERR;
        String msg = allByType !=null ? "" : "数据查询失败，请重试!";
        return new Result(code,allByType,msg);
    }

    //根据商品类型查找所有的商品（包括已经售出的）
    @GetMapping("/getAllByTypeAll/{type}")
    public Result getAllByTypeAll(@PathVariable String type){
        List<Goods> allByType = goodsService.getAllByTypeAll(type);
        Integer code = allByType != null ? Code.GET_OK : Code.GET_ERR;
        String msg = allByType !=null ? "" : "数据查询失败，请重试!";
        return new Result(code,allByType,msg);
    }

    //发货
    @GetMapping("/delivery/{id}")
    public Result delivery(@PathVariable Integer id){
        Goods goods = goodsService.getById(id);
        if(goods.getDelivery().equals("已发货")){
            return new Result(Code.DELIVERY_ERR,goods,"该商品已发货!");
        } else if (goods.getDelivery().equals("未发货")){
            goods.setDelivery("已发货");
        } else if (goods.getDelivery().equals("已收货")){
            return new Result(Code.DELIVERY_ERR,goods,"该商品已收货!");
        } else {
            return new Result(Code.DELIVERY_ERR,goods,"该商品未售出!");
        }
        boolean flag = goodsService.update(goods);
        Integer code = flag != false ? Code.DELIVERY_OK : Code.DELIVERY_ERR;
        String msg = flag != false ? "" : "发货失败，请重试!";
        return new Result(code,goods,msg);
    }

    //收货
    @GetMapping("/orderGet/{id}")
    public Result orderGet(@PathVariable Integer id) {
        Goods goods = goodsService.getById(id);
        if (goods.getDelivery().equals("已发货")) {
            goods.setDelivery("已收货");
        } else if (goods.getDelivery().equals("未发货")) {
            return new Result(Code.DELIVERY_ERR, goods, "该商品未发货!");
        } else if (goods.getDelivery().equals("已收货")) {
            return new Result(Code.DELIVERY_ERR, goods, "该商品已收货!");
        }
        boolean flag = goodsService.update(goods);
        Integer code = flag != false ? Code.DELIVERY_OK : Code.DELIVERY_ERR;
        String msg = flag != false ? "" : "收货失败，请重试!";
        return new Result(code, goods, msg);
    }

    //订单取消
    @GetMapping("/orderBack/{id}")
    public Result orderBack(@PathVariable Integer id) {
        Goods goods = goodsService.getById(id);
        goods.setDelivery("未售出");
        goods.setUid(0);
        boolean flag = goodsService.update(goods);
        Integer code = flag != false ? Code.ORDERBACK_OK : Code.ORDERBACK_ERR;
        String msg = flag != false ? "" : "退货失败，请重试!";
        return new Result(code, goods, msg);
    }
}
