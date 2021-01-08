package org.unicome.data.domain.mysql.group;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupDao {
    @Select({"SELECT * FROM `GROUP` WHERE id = #{id}"})
    @ResultMap("baseGroupMap")
    Group findById(@Param("id") int id);

    @Select({"SELECT * FROM `GROUP` LIMIT #{index}, #{size}"})
    @ResultMap("baseGroupMap")
    List<Group> findAll(@Param("index") int pageIndex, @Param("size") int pageSize);

    @Select({"SELECT g.* FROM `GROUP` AS g, `USER_GROUP` AS ug WHERE g.id = ug.group_id and ug.user_id = #{userId}"})
    @ResultMap("baseGroupMap")
    List<Group> findAllByUserId(@Param("userId") int userId);
}