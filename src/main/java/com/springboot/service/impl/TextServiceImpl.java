package com.springboot.service.impl;

import com.springboot.mapper.TextMapper;
import com.springboot.service.TextService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author ChenKang
 * @date 2023/2/22 15:53
 */
@Service
public class TextServiceImpl implements TextService {
    @Resource
    TextMapper textMapper;

    @Override
    public List<Map<String, String>> selectList() {
        return textMapper.selectList();
    }
}
