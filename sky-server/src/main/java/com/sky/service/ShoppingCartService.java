package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    // 添加到购物车
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    // 添加到购物车
    List<ShoppingCart> showShoppingCart();

    //清空购物车所有商品
    void cleanShoppingCart();

    // 从购物车中删除
    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
