package com.example.station.mapper;

import com.example.station.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Delete("delete from user where username = #{username}")
    int deleteUser(String username);

    @Select("select * from user where username = #{username}")
    User getOneUser(String username);

    @Select("select count(username) from user where username = #{username}")
    int selectUsername(String username);

    @Insert("insert into user(username, password, staff, authority, area_level, region_id, create_time, state) " +
            "values (#{username}, #{password}, #{staff}, #{authority}, #{area_level}, #{region_id}, #{create_time}, #{state})")
    int addUser(User user);

    @Insert("insert into user(username, password, staff, authority, area_level, region_id, create_time, state) " +
            "values (#{username}, ’123456‘, #{staff}, 1,1, 1, #{create_time}, 0")
    int addUserbyName(String UserName,String staff);

    @Update("update user set authority=#{authority}, area_level=#{area_level}, region_id=#{region_id},staff=#{staff} where id=#{id}")
    int updateUserAuthority(int id,String staff,int authority, int area_level, int region_id);

    @Update("update user set state=#{state} where id=#{id}")
    int modifyUserState(int id, int state);

    @Update("update user set password=#{password} where id=#{id}")
    int updatePassword(int id, String password);

    @Update("update user set password=#{password} where id=#{id}")
    int modifyPassword(int id, String password);

    @Update("update user set staff=#{staff} where id=#{id}")
    int updateStaff(int id, String staff);

    @Update("update user set staff=#{staff} where username=#{username}")
    int updateStaffbyusername(String username, String staff);

    @Select("select id,staff,authority,area_level,region_id,create_time,state from user where id>1 order by id asc limit #{n},12")
    List<User> getUser(int n);

    @Select("select id,staff,authority,area_level,region_id,create_time,state from user where region_id=#{region_id} order by id asc limit #{n},12")
    List<User> getUserByRegion(int n, int region_id);

    @Select("select id,staff,authority,area_level,region_id,create_time,state from user where id>1 order by id asc")
    List<User> getUserWithoutPage();

    @Select("select id,staff,authority,area_level,region_id,create_time,state from user where region_id=#{region_id} order by id asc")
    List<User> getUserWithoutPageByRegion(int region_id);

    @Select("select ifnull(count(id),0) from user")
    int getUserMount();

    @Select("select ifnull(count(id),0) from user where region_id=#{region_id}")
    int getUserMountByRegion(int region_id);

    @Select("select id,staff,authority,area_level,region_id from user where id = #{id}")
    User getPersonalInformation(int id);
}
