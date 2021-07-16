package com.alatai.jishop.dao;

import com.alatai.jishop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/16 10:26
 */
public interface UserDao extends JpaRepository<User, Integer> {

}
