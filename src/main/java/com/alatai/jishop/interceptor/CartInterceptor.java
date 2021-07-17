package com.alatai.jishop.interceptor;

import com.alatai.jishop.entity.OrderItem;
import com.alatai.jishop.entity.User;
import com.alatai.jishop.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ショッピングカートの商品数など
 *
 * @author Alatai
 * @version 1.0
 * @date 2021/07/17 21:35
 */
public class CartInterceptor implements HandlerInterceptor {

    @Autowired
    private OrderItemService orderItemService;

    /**
     * ビューのレンダリング前に
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        int cartNum = 0;

        if (user != null) {
            List<OrderItem> orderItems = orderItemService.findByUser(user);

            cartNum = orderItems.size();
        }

        session.setAttribute("cartNum", cartNum);
    }

}
