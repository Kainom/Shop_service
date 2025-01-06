package com.kainom.shop.repository;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.kainom.shop.dto.ShopReportDTO;
import com.kainom.shop.models.Shop;

@Repository
public interface ReportRepository {
    public List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Double minValue);
    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim);

}
