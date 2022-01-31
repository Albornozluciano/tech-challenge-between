package com.between.techchallenge.repository;

import com.between.techchallenge.model.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricesRepository extends CrudRepository<Price, Long> {

    /**
     * Retrieves a single price by brand, product and an application date.
     * @param brandId - Brand Identifier
     * @param productId - Product Identifier
     * @param applicationDate - Date with pattern yyyy-MM-dd-HH.mm.ss
     * @return A single price between start_date and end_date columns. If there is more than 1 row, the priority will
     * decide which row is the correct price.
     **/
    @Query(value="SELECT P.*, F.FEE_VALUE\n" +
            "FROM PRICES as P, FEES as F\n" +
            "WHERE P.PRICE_LIST = F.PRICE_LIST AND P.BRAND_ID = :brandId AND PRODUCT_ID = :productId AND P.START_DATE <= parsedatetime(:applicationDate, 'yyyy-MM-dd-hh.mm.ss') AND P.END_DATE >= parsedatetime(:applicationDate, 'yyyy-MM-dd-hh.mm.ss') \n" +
            "ORDER BY P.PRIORITY DESC\n" +
            "LIMIT 1;", nativeQuery=true)
    Price getPricesByBrandProductAndDate(String brandId, String productId, String applicationDate);
}