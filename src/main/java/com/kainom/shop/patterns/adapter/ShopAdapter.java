package com.kainom.shop.patterns.adapter;

import java.util.stream.Collectors;

import com.kainom.shop.dto.ShopDTO;
import com.kainom.shop.models.Shop;

public class ShopAdapter implements IShopAdapter {

    IItemAdapter itemAdapter;

    public ShopAdapter(IItemAdapter itemAdapter) {
        this.itemAdapter = itemAdapter;
    }

    @Override
    public ShopDTO adapt(Shop shop) {
        return new ShopDTO(
                shop.getId(),
                shop.getUserIdentifier(),
                shop.getTotal(),
                shop.getItems().stream().map(e -> itemAdapter.adapt(e)).collect(Collectors.toList()),
                shop.getDate()
        );

    }

    @Override
    public Shop adapt(ShopDTO shopDTO) {

        return Shop
                .builder()
                .date(shopDTO.date())
                .items(shopDTO.items().stream().map(e -> itemAdapter.adapt(e)).collect(Collectors.toList()))
                .total(shopDTO.total())
                .userIdentifier(shopDTO.userIdentifier())
                .build();
    }

}
