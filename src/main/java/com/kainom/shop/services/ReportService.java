package com.kainom.shop.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kainom.shop.dto.ShopDTO;
import com.kainom.shop.dto.ShopReportDTO;
import com.kainom.shop.patterns.adapter.IShopAdapter;
import com.kainom.shop.repository.ReportRepository;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public ShopReportDTO getReportByDate(Date dataDeInicio, Date dataDeFim) {
        return reportRepository.getReportByDate(dataDeInicio, dataDeFim);
    }
}
