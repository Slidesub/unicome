package org.unicome.data.domain.mysql.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {
    @Select({"SELECT * FROM `USER` WHERE id = #{id}"})
    @ResultMap("baseUserMap")
    User findById(int id);

    @Select({"SELECT * FROM `USER` LIMIT #{index}, #{size}"})
    @ResultMap("baseUserMap")
    List<User> findAll(@Param("index") int pageIndex, @Param("size") int pageSize);

    @Select({"SELECT u.* FROM `USER` AS u,  `USER_GROUP` AS ug WHERE u.id = ug.user_id and ug.group_id = #{groupId}"})
    @ResultMap("baseUserMap")
    List<User> findAllByGroupId(@Param("groupId") int groupId);

    @Select({"SELECT u.* FROM `USER` AS u WHERE u.${column} = #{value} ORDER BY u.id"})
    @ResultMap("userMap")
    User findByColumn(@Param("column") String column, @Param("value") String value);
}
