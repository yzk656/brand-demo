package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @ClassName: BrandMapper
 * @Description: TODO
 * @Author: 杨振坤
 * @date: 2023/3/19 8:28
 */
public interface BrandMapper {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();


    /**
     * 添加对象
     */
    @Insert("insert into tb_brand values (null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    /**
     * 回显-查询数据
     * 根据id查询
     */
    @Select("select * from tb_brand where id=#{id}")
    @ResultMap("brandResultMap")
    Brand selectById(int i);

    /**
     * 修改数据
     */
    @Update("update tb_brand set brand_name=#{brandName},company_name=#{companyName},ordered=#{ordered},description=#{description},status=#{status} where id=#{id}")
    void update(Brand brand);

    /**
     * 删除数据
     */
    @Delete("delete from tb_brand where id=#{id}")
    void delete(int id);
}
