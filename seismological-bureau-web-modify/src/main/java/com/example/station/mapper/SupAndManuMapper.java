package com.example.station.mapper;

import com.example.station.entity.Supplier;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SupAndManuMapper {
    //新增供应商
    @Insert("insert into  supplier ( name, code, address, zip, contact, phone, email) values (#{name},#{code},#{address},#{zip},#{contact},#{phone},#{email})")
    int addNewSupplier(Supplier supplier);
    //验证是否为唯一的编号
    @Select("select count(*) from supplier where code=#{code} ")
    int FindCode(String code);
    //删除供应商
    @Delete("delete from supplier where code=#{code}")
    int deleteSupplier(String code);
    //修改信息
    @Update("update supplier set name =#{name},address=#{address},zip=#{zip},contact=#{contact},phone=#{phone},email=#{email} where code=#{code}")
    int updateSupplier(Supplier supplier);
    //查找全部的供应商
    @Select("select * from supplier")
    List<Supplier> getAllSupplier();
//    下面是制造商
    //新增供应商
    @Insert("insert into  manufacturer ( name, code, address, zip, contact, phone, email) values (#{name},#{code},#{address},#{zip},#{contact},#{phone},#{email})")
    int addNewManu(Supplier supplier);
    //验证是否为唯一的编号
    @Select("select count(*) from manufacturer where code=#{code} ")
    int FindCode2(String code);
    //删除供应商
    @Delete("delete from manufacturer where code=#{code}")
    int deleteManu(String code);
    //修改信息
    @Update("update manufacturer set name =#{name},address=#{address},zip=#{zip},contact=#{contact},phone=#{phone},email=#{email} where code=#{code}")
    int updateManu(Supplier supplier);
    //查找全部的供应商
    @Select("select * from manufacturer")
    List<Supplier> getAllManu();
}
