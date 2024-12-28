package com.kainom.shop.repository;

import java.util.Date;
import java.util.List;

import com.kainom.shop.dto.ShopReportDTO;
import com.kainom.shop.models.Shop;

public interface ReportRepository {
    public List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Double minValue);
    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim);

}
