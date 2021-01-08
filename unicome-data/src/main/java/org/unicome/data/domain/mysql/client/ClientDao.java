package org.unicome.data.domain.mysql.client;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClientDao {

    @Select({"SELECT * FROM `CLIENT` WHERE id = #{id}"})
    @ResultMap("baseClientMap")
    Client findById(@Param("id") int id);

    @Select({"SELECT * FROM `CLIENT` WHERE client_id = #{clientId}"})
    @ResultMap("baseClientMap")
    Client findByClientId(@Param("clientId") String clientId);
}
