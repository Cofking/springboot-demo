package com.springboot.service;

import java.util.List;
import java.util.Map;

/**
 * @author ChenKang
 * @date 2023/2/22 15:52
 */

public interface TextService {
    List<Map<String, String>> selectList();
}
