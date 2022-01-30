package com.between.techchallenge.repository;

import com.between.techchallenge.model.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricesRepository extends CrudRepository<Price, Long> {

    @Query(value="SELECT P.*, F.FEE_VALUE\n" +
            "FROM PRICES as P, FEES as F\n" +
            "WHERE P.PRICE_LIST = F.PRICE_LIST AND P.BRAND_ID = :brandId AND PRODUCT_ID = :productId AND P.START_DATE <= parsedatetime(:applicationDate, 'yyyy-MM-dd-hh.mm.ss') AND P.END_DATE >= parsedatetime(:applicationDate, 'yyyy-MM-dd-hh.mm.ss') \n" +
            "ORDER BY P.PRIORITY DESC\n" +
            "LIMIT 1;", nativeQuery=true)
    Price getPricesByBrandProductAndDate(String brandId, String productId, String applicationDate);
}