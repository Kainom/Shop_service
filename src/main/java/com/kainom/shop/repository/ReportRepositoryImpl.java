package com.kainom.shop.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.kainom.dtos.ShopReportDTO;
import com.kainom.shop.models.Shop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
@Primary
public class ReportRepositoryImpl implements ReportRepository {

    @PersistenceContext
    EntityManager enetityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<Shop> getShopByFilters(Date dataInicio, Date dataFim, Double minValue) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT s");
        sb.append(" FROM shop s");
        sb.append(" WHERE s.date > :dataInicio ");

        if (dataFim != null) {
            sb.append("AND s.date <= :dataFim ");

        }
        if (minValue != null) {
            sb.append("AND s.total <= :minValue ");
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
        System.out.println(dataFim);
        sb.append("SELECT COUNT(sp.id), SUM(sp.total), AVG(sp.total) ");
        sb.append("FROM shop sp ");
        sb.append("WHERE sp.date BETWEEN :dataInicio AND :dataFim ");
        // sb.append("WHERE sp.date BETWEEN :dataInicio AND :dataFim ");
        // sb.append("GROUP BY FUNCTION('DATE', sp.date)");

        Query query = enetityManager.createQuery(sb.toString());

        query.setParameter("dataInicio", dataInicio);
        query.setParameter("dataFim", dataFim);

        @SuppressWarnings("unchecked")
        List<Object[]> resultList = query.getResultList();

        // Verifica se a lista está vazia
        if (resultList.isEmpty()) {
            // Retorna valores padrão ou nulos
            return new ShopReportDTO(0, 0.0, 0.0);
        }

        // Obtém o primeiro resultado
        Object[] result = resultList.get(0);

        Integer count = ((Long) result[0]).intValue();
        Double total = (Double) result[1];
        Double mean = (Double) result[2];

        return new ShopReportDTO(count, total, mean);
    }

}
