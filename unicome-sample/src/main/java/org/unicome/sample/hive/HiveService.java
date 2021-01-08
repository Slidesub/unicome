package org.unicome.sample.hive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HiveService {

    @Autowired
    @Qualifier("hiveTemplate")
    private JdbcTemplate hiveTemplate;

    @Cacheable(value="sample")
    public String sample() {
        hiveTemplate.execute("use myhive");
        List tables = hiveTemplate.queryForList("show tables");
        return (String) tables.parallelStream().map(item -> {
            Map<String, String> b = (LinkedCaseInsensitiveMap<String>)item;
            return b.get("database") + "." + b.get("tableName");
        }).collect(Collectors.joining(","));
    }
}
