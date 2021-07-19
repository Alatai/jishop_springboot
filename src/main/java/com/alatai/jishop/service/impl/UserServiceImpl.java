package com.alatai.jishop.service.impl;

import com.alatai.jishop.dao.UserDao;
import com.alatai.jishop.entity.User;
import com.alatai.jishop.service.UserService;
import com.alatai.jishop.util.PageResult;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/16 10:28
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public PageResult<User> findAll(Integer start, Integer size, Integer displayPages) {
        Pageable pageable = PageRequest.of(start, size);
        Page<User> page = userDao.findAll(pageable);

        return new PageResult<>(page, displayPages);
    }

    @Override
    public User findById(Integer id) {
        return userDao.getById(id);
    }

    @Override
    public User insert(User user) {
        return userDao.save(user);
    }

    @Override
    public User update(User user) {
        return userDao.save(user);
    }

    @Override
    public void deleteById(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    public boolean isExist(String name) {
        return findByName(name) != null;
    }

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public void register(User user) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithm = "md5";

        String encodedPassword = new SimpleHash(algorithm, user.getPassword(), salt, times).toString();

        user.setSalt(salt);
        user.setPassword(encodedPassword);

        insert(user);
    }
}
