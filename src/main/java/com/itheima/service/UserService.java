package com.itheima.service;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * @ClassName: UserService
 * @Description: TODO
 * @Author: 杨振坤
 * @date: 2023/3/20 14:51
 */
public class UserService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 登录方法
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        /*1. 获取SQLSession*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*2. 获取UserMapper*/
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        /*3. 调用方法*/
        User user = mapper.select(username, password);

        /*4. 释放资源*/
        sqlSession.close();
        return user;
    }

    /**
     * 注册方法
     * @param user
     * @return
     */
    public boolean register(User user) {
        /*1. 获取SQLSession*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*2. 获取UserMapper*/
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        /*3. 调用方法*/
        User u=mapper.selectByUsername(user.getUsername());

        if (u == null) {
            /*用户名不存在*/
            mapper.add(user);
            sqlSession.commit();
            return true;
        }
        /*关闭资源*/
        sqlSession.close();

        return u==null;
    }
}
