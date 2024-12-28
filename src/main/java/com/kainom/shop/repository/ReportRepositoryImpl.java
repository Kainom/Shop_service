package com.kainom.shop.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.kainom.shop.dto.ShopReportDTO;
import com.kainom.shop.models.Shop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

public class ReportRepositoryImpl implements ReportRepository {

    @PersistenceContext
    EntityManager enetityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Double minValue) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT s");
        sb.append(" FROM shop s");
        sb.append(" WHERE s.date > :dataInicio");

        if (dataFim != null) {
            sb.append("and s.date <= :dataFim ");

        }
        if (minValue != null) {
            sb.append("and s.total <= :minValue ");
        }

        Query query = enetityManager.createQuery(sb.toString());

        query.setParameter("dataInicio", dataInicio);

        if (dataFim != null) {
            query.setParameter("dataFim", dataFim);
        }
        if (minValue != null) {
            query.setParameter("minValue", minValue);
        }

        return (List<Shop>) query.getResultList();
    }

    @Override
    public ShopReportDTO getReportByDate(Date dataInicio, Date dataFim) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT COUNT(sp.id),SUM(sp.total),AVG(sp.total)");
        sb.append(" FROM shop sp");
        sb.append("BETWEEN :dataInicio AND :dataFim");
        // sb.append("GROUP BY DATE(sp.date)");

        Query query = enetityManager.createQuery(sb.toString());

        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);

        Object[] resultList = (Object[]) query.getSingleResult();

        Integer count = ((BigInteger) resultList[0]).intValue();
        Double total = (Double) resultList[1];
        Double mean = (Double) resultList[2];

        return new ShopReportDTO(count, total, mean);
    }

}
