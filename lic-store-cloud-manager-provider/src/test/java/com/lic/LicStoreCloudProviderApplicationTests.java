package com.lic;

import com.google.gson.Gson;
import com.lic.mapper.GoodsMapper;
import com.lic.pojo.Admin;
import com.lic.pojo.Goods;
import com.lic.pojo.GoodsExample;
import com.lic.result.*;
import com.lic.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LicStoreCloudProviderApplicationTests {

    @Autowired
    GoodsCategoryService goodsCategoryService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsAttributeService goodsAttributeService;

    @Autowired
    SearchService searchService;

    @Autowired
    JedisPool jedisPool;

    @Autowired
    AdminService adminService;

    @Autowired
    CartService cartService;
    @Test
    public void select() {
        List<TreeResult> treeResults = goodsCategoryService.findByPId((short) 1);
        System.out.println(treeResults);
    }

    @Test
    public void save() {
        Goods goods = new Goods();
        goods.setBrandId((short) 1);
        goods.setGoodsName("111");
        goods.setCatId(1);
        goods.setCostPrice(BigDecimal.valueOf(11.00));
        EgoResult res = goodsService.save(goods);
        System.out.println(res.getStatus());
    }

    @Test
    public void selectPage() {
//        PageHelper.startPage(1,10);
        GoodsExample goodsExample = new GoodsExample();
        EasyuiPageResult result = goodsAttributeService.findByPage(1, 10);
        System.out.println(result);
    }

    @Test
    public void redisTest() {
        Jedis jedis = jedisPool.getResource();
        jedis.set("username", "lichai");
        String username = jedis.get("username");
        jedis.close();
        System.out.println(username);
    }

    @Test
    public void search() {
        EgoPageInfo pageInfo = searchService.search(0, 10, "手机");
        System.out.println(new Gson().toJson(pageInfo));

    }

    @Test
    public void sign() {
        EgoResult result = adminService.sign("liulu", "123456");
        //票据a75b5be7-a9a2-4c2d-b3ac-7d0fdbaed732
        System.out.println("票据"+result.getMessage());
    }

    @Test
    public void validate(){
        String ticket = "39234d74-880c-4e9c-b3bb-6b2a46bc8984";
        Admin admin = adminService.validate(ticket);
        System.out.println(admin);
    }

    @Test
    public void findByPage(){
        EgoPageInfo pageInfo = cartService.findByPage(1,10,"770a8402-b9f9-4d53-922e-224045603373");
        System.out.println(new Gson().toJson(pageInfo));
    }

}
