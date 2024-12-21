package com.kainom.shop.dto;

import java.util.Date;
import java.util.List;

import com.kainom.shop.models.Item;

public record ShopDTO(
        long id,
        String userIdentifier,
        Double total,
        List<ItemDTO> items,
        Date date) {
}
