package com.kainom.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.kainom.shop.dto.ShopDTO;
import com.kainom.shop.patterns.adapter.IShopAdapter;
import com.kainom.shop.services.ShopService;

@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;
    private IShopAdapter shopAdapter;

    private ShopController(IShopAdapter shopAdapter) {
        this.shopAdapter = shopAdapter;
    }

    @GetMapping("/")
    public List<ShopDTO> getShops() {
        return shopService.getAll();
    }

    @GetMapping("/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShops(@PathVariable("userIdentifier") String userIdentifier) {
        return shopService.getByUser(userIdentifier);
    }

    @GetMapping("/shopByDate")
    public List<ShopDTO> getShops(@RequestBody ShopDTO shopDTO) {
        return shopService.getByDate(shopDTO);
    }

    @GetMapping("/{Id}")
    public ShopDTO getShop(@PathVariable("Id") Long Id) {
        return shopService.findById(Id);
    }

    @PostMapping("/")
    public ShopDTO addShop(@RequestBody ShopDTO shop) {
        return shopService.save(shop);
    }
}
