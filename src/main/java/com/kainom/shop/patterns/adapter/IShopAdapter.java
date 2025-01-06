package com.kainom.shop.patterns.adapter;

import com.kainom.dtos.ShopDTO;
import com.kainom.shop.models.Shop;

public interface IShopAdapter {
    ShopDTO adapt(Shop shop);
    Shop adapt(ShopDTO shopDTO);

    
}
