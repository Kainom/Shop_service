package com.kainom.shop.patterns.adapter;

import com.kainom.dtos.ItemDTO;
import com.kainom.shop.models.Item;

public interface IItemAdapter {
    ItemDTO adapt(Item item);
    Item adapt(ItemDTO itemDTO);
    
}
