package com.lic.service.impl;

import com.lic.mapper.AdminMapper;
import com.lic.pojo.Admin;
import com.lic.pojo.AdminExample;
import com.lic.pojo.AdminWithBLOBs;
import com.lic.result.EgoResult;
import com.lic.service.AdminService;
import com.lic.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    AdminMapper adminMapper;

//    @Autowired
//    JedisPool jedisPool;

    public EgoResult register(AdminWithBLOBs admin) {
        if (admin != null) {
            EgoResult result = checkUser(admin);
            if (result.getStatus() != 200) {
                return result;
            }
            //注册时间
            admin.setAddTime((int) new Date().getTime() / 1000);
            //获取盐
            String salt = Md5Util.genSalt(6);
            admin.setEcSalt(salt);
            //密码加密
            String md5 = Md5Util.md5WithSalt(admin.getPassword(), salt);
            admin.setPassword(md5);
            admin.setNavList("000");
            //保存至数据库
            adminMapper.insertSelective(admin);
            return EgoResult.success();
        }
        return EgoResult.fail();
    }

    @Override
    public EgoResult sign(String username, String password) {
        Jedis jedis = new Jedis("192.168.245.128",6379);
        EgoResult result = null;
        AdminExample example = new AdminExample();
        example.createCriteria().andUserNameEqualTo(username);
        //根据用户名查询数据库
        List<Admin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() == 1) {
            //合理
            Admin admin = adminList.get(0);
            //盐
            String salt = admin.getEcSalt();
            String passwordDb = admin.getPassword();
            //验证密码
            String md5 = Md5Util.md5WithSalt(password.trim(), salt.trim());
            if (md5.equals(passwordDb)) {
                //密码验证通过
                result = EgoResult.success();
                //生成ticket
                String ticket = UUID.randomUUID().toString();
                result.setMessage(ticket);
                admin.setEcSalt(null);
                //ticket为key,admin我value保存至redis
                jedis.set(SerializationUtils.serialize(ticket),
                        SerializationUtils.serialize(admin));
                return result;
            }
            result = EgoResult.fail();
            result.setMessage("用户名或密码错误");
        } else if (adminList != null && adminList.size() > 1) {
            logger.error("数据有误，根据同一用户名，查询出多条数据" + username);
        } else {
            result = EgoResult.fail();
            result.setMessage("用户名或密码错误");
        }
        return EgoResult.fail();
    }

    @Override
    public Admin validate(String ticket) {
        Jedis jedis = new Jedis("192.168.245.128",6379);
        Admin admin = null;
        byte[] adminBytes = jedis.get(SerializationUtils.serialize(ticket));
        if(adminBytes != null && adminBytes.length>0){
            admin = (Admin) SerializationUtils.deserialize(adminBytes);
        }
        return admin;
    }

    /**
     * 检测合法性
     */
    private EgoResult checkUser(AdminWithBLOBs admin) {
        EgoResult result = EgoResult.success();
        AdminExample example = new AdminExample();
        //指定条件
        if (admin.getEmail() != null) {
            example.createCriteria().andEmailEqualTo(admin.getEmail());
            List<Admin> adminList = adminMapper.selectByExample(example);
            if (adminList != null && adminList.size() > 0) {
                result = EgoResult.fail();
                result.setMessage("该邮箱已经被注册");
                return result;
            }
        }
        if (admin.getUserName() != null) {
            example.createCriteria().andUserNameEqualTo(admin.getUserName());
            List<Admin> adminList = adminMapper.selectByExample(example);
            if (adminList != null && adminList.size() > 0) {
                result.setMessage("该用户名已经被占用");
                return result;
            }
        }
        return result;
    }
}
