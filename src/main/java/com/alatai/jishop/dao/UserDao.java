package com.alatai.jishop.dao;

import com.alatai.jishop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserDao
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/16 10:26
 */
public interface UserDao extends JpaRepository<User, Integer> {

    User findByName(String name);

}
