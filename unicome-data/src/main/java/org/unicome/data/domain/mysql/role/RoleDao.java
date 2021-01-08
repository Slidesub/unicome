package org.unicome.data.domain.mysql.role;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleDao {
    @Select({"SELECT * FROM `ROLE` WHERE id = #{id}"})
    @ResultMap("baseRoleMap")
    Role findById(@Param("id") int id);

    @Select({"SELECT * FROM `ROLE` LIMIT #{index}, #{size}"})
    @ResultMap("baseRoleMap")
    List<Role> findAll(@Param("index") int pageIndex, @Param("size") int pageSize);

    @Select({"SELECT r.* FROM `ROLE` AS r, `USER_ROLE` AS ur WHERE r.id = ur.role_id and ur.user_id = #{userId}"})
    @ResultMap("baseRoleMap")
    List<Role> findAllByUserId(@Param("userId") int userId);

    @Select({"SELECT r.* FROM `ROLE` AS r, `GROUP_ROLE` AS gr WHERE r.id = gr.role_id and gr.group_id = #{groupId}"})
    @ResultMap("baseRoleMap")
    List<Role> findAllByGroupId(@Param("groupId") int groupId);
}
