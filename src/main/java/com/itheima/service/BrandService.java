package com.itheima.service;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @ClassName: BrandService
 * @Description: TODO
 * @Author: 杨振坤
 * @date: 2023/3/19 8:40
 */
public class BrandService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return
     */
    public List<Brand> selectAll() {
        /*调用BrandMapper.selectAll*/

        /*获取SQLSession*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*获取BrandMapper*/
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);


        /*调用方法*/
        List<Brand> brands = mapper.selectAll();

        sqlSession.close();
        return brands;
    }

    /**
     * 添加功能
     * @param brand
     */
    public void add(Brand brand){
        /*获取SQLSession*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*获取BrandMapper*/
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.add(brand);

        /*这个是增加操作，要有提交事务*/
        sqlSession.commit();

        sqlSession.close();
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    public Brand selectById(int id) {
        /*调用BrandMapper.selectAll*/

        /*获取SQLSession*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*获取BrandMapper*/
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);


        /*调用方法*/
        Brand brand = mapper.selectById(id);

        sqlSession.close();
        return brand;
    }

    /**
     * 修改
     * @param brand
     */
    public void update(Brand brand){
        /*获取SQLSession*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*获取BrandMapper*/
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.update(brand);

        /*这个是增加操作，要有提交事务*/
        sqlSession.commit();

        sqlSession.close();
    }

    /**
     * 修改
     * @param id
     */
    public void delete(int id){
        /*获取SQLSession*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*获取BrandMapper*/
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.delete(id);

        /*这个是删除操作，要有提交事务*/
        sqlSession.commit();

        sqlSession.close();
    }
}
