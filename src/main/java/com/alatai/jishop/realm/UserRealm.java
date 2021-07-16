package com.alatai.jishop.realm;

import com.alatai.jishop.entity.User;
import com.alatai.jishop.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Shiroフレームワークで、ユーザのパスワードを暗号化する
 *
 * @author Alatai
 * @version 1.0
 * @date 2021/07/16 20:43
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 権限を与える
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * 検査
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String name = token.getPrincipal().toString();
        User user = userService.findByName(name);
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();

        return new SimpleAuthenticationInfo(name, passwordInDB, ByteSource.Util.bytes(salt), getName());
    }
}
