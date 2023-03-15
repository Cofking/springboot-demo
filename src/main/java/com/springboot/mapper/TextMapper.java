package com.springboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author ChenKang
 * @date 2023/2/22 15:55
 */
public interface TextMapper {
    @Select("select * from exam")
    public List<Map<String, String>> selectList();

    @Insert("insert into exam values ('测试','测试',50)")
    Integer insertOne();
}
