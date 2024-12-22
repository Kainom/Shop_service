package com.kainom.shop.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kainom.shop.dto.ShopDTO;
import com.kainom.shop.models.Shop;
import com.kainom.shop.patterns.adapter.IShopAdapter;
import com.kainom.shop.repository.ShopRepository;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    private IShopAdapter shopAdapter;

    private ShopService(IShopAdapter shopAdapter) {
        this.shopAdapter = shopAdapter;
    }

    public List<ShopDTO> getAll() {
        return shopRepository.findAll()
                .stream()
                .map(shopAdapter::adapt)
                .collect(Collectors.toList());

    }

    public List<ShopDTO> getByUser(String userIdentifier) {
        return shopRepository.findAllByUserIdentifier(userIdentifier)
                .stream()
                .map(shopAdapter::adapt)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO) {
        return shopRepository.findAllByDateGreaterThanEquals(shopDTO.date())
                .stream()
                .map(shopAdapter::adapt)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(Long productId) {
        Optional<Shop> shop = shopRepository.findById(productId);
        return shop.map(shopAdapter::adapt).orElse(null);
    }

    public ShopDTO save(ShopDTO shop) {
        Double total = shop.items()
                .stream()
                .map(x -> x.price())
                .reduce((Double) 0d, Double::sum);
        Shop newShop = shopAdapter.adapt(shop);
        newShop.setTotal(total);
        return shopAdapter.adapt(shopRepository.save(newShop));

    }

}
