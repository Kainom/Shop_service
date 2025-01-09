package com.kainom.shop.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kainom.dtos.ItemDTO;
import com.kainom.dtos.ShopDTO;
import com.kainom.shop.models.Shop;
import com.kainom.shop.patterns.adapter.IShopAdapter;
import com.kainom.shop.repository.ShopRepository;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductService product;

    @Autowired
    private UserService user;

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

        return shopRepository.findAllByDateGreaterThanEqual(shopDTO.date())
                .stream()
                .map(shopAdapter::adapt)
                .collect(Collectors.toList());
    }

    public List<ShopDTO> getShopsByFilter(Date dataDeInicio, Date dataDeFim, Double minValor) {
        return shopRepository.getShopByFilters(dataDeInicio, dataDeFim, minValor)
                .stream()
                .map(shopAdapter::adapt)
                .collect(Collectors.toList());
    }

    public ShopDTO findById(Long productId) {
        Optional<Shop> shop = shopRepository.findById(productId);
        return shop.map(shopAdapter::adapt).orElse(null);
    }

    public ShopDTO save(ShopDTO shop) {

        if (user.getUserByCpf(shop.userIdentifier()) == null)
            return null;

        List<ItemDTO> items = validateProducts(shop.items()) ;
        if (items == null)
            return null;

        Shop newShops = shopAdapter.adapt(shop);
        newShops.setTotal(items
                .stream()
                .map(p -> p.price())
                .reduce(0d, Double::sum));

        newShops.setDate(new Date());
        return shopAdapter.adapt(shopRepository.save(newShops));

    }

    public List<ItemDTO> validateProducts(List<ItemDTO> items) {
        Boolean isNull = items
                .stream()
                .map(product -> product.productIdentifier())
                .anyMatch(e -> product.getProductByIdentifier(e) == null);

        if (isNull)
            return null;


        List<ItemDTO> itemDTOs = items
                .stream()
                .map(item -> new ItemDTO(item.productIdentifier(),
                        product.getProductByIdentifier(item.productIdentifier()).getPreco()))
                .collect(Collectors.toList());
        return itemDTOs;
            }

}
