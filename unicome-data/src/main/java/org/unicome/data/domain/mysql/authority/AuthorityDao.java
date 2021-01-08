package org.unicome.data.domain.mysql.authority;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AuthorityDao {
    @Select({"SELECT * FROM `AUTHORITY` WHERE id = #{id}"})
    @ResultMap("baseAuthorityMap")
    Authority findById(@Param("id") int id);

    @Select({"SELECT * FROM `AUTHORITY` LIMIT #{index}, #{size}"})
    @ResultMap("baseAuthorityMap")
    List<Authority> findAll(@Param("index") int pageIndex, @Param("size") int pageSize);

    @Select({"SELECT g.* FROM `AUTHORITY` AS a, `ROLE_AUTHORITY` AS ra WHERE a.id = ra.authority_id and ra.role_id = #{roleId}"})
    @ResultMap("baseAuthorityMap")
    List<Authority> findAllByRoleId(@Param("roleId") int roleId);
}
