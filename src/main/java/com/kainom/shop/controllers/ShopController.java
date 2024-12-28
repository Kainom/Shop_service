package com.kainom.shop.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.kainom.shop.dto.ShopDTO;
import com.kainom.shop.dto.ShopReportDTO;
import com.kainom.shop.patterns.adapter.IShopAdapter;
import com.kainom.shop.services.ReportService;
import com.kainom.shop.services.ShopService;

@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ReportService reportService;

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

    @GetMapping("/search")
    public List<ShopDTO> getShopsByFilter(
            @RequestParam(name = "dataInicio", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
            @DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam(name = "dataFim", required = false) Date dataFim,
            @RequestParam(name = "minValor", required = false) Double minValor) {

        return shopService.getShopsByFilter(dataInicio, dataFim, minValor);
    }

    @GetMapping("/{Id}")
    public ShopDTO getShop(@PathVariable("Id") Long Id) {
        return shopService.findById(Id);
    }

    @GetMapping("/report")
    public ShopReportDTO getReportByDate(
            @RequestParam(name = "dataInicio", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataInicio,
            @RequestParam(name = "dataFim", required = true) @DateTimeFormat(pattern = "dd/MM/yyyy") Date dataFim) {
        return reportService.getReportByDate(dataInicio, dataFim);
    }

    @GetMapping("/error")
    public String error() {
        throw new RuntimeException("Error occurred!");
    }

    @PostMapping("/")
    public ShopDTO addShop(@RequestBody ShopDTO shop) {
        return shopService.save(shop);
    }

}
