package com.alatai.jishop.service;

import com.alatai.jishop.entity.User;
import com.alatai.jishop.util.PageResult;

import java.util.List;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/16 10:27
 */
public interface UserService {

    List<User> findAll();

    PageResult<User> findAll(Integer start, Integer size, Integer displayPages);

    User findById(Integer id);

    User insert(User user);

    User update(User user);

    void deleteById(Integer id);

    boolean isExist(String name);

    User findByName(String name);

    void register(User user);
}
