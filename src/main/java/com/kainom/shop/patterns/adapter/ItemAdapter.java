package com.kainom.shop.patterns.adapter;

import org.springframework.stereotype.Component;

import com.kainom.dtos.ItemDTO;
import com.kainom.shop.models.Item;

@Component
public class ItemAdapter implements IItemAdapter {

    @Override
    public ItemDTO adapt(Item item) {
        return new ItemDTO(item.getProductIdentifier(), item.getPrice());
    }

    @Override
    public Item adapt(ItemDTO itemDTO) {
        return Item
                .builder()
                .productIdentifier(itemDTO.productIdentifier())
                .price(itemDTO.price())
                .build();
    }

}
