package com.between.techchallenge.dto;

import com.between.techchallenge.model.Price;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BrandProductPriceDTO {
    @JsonProperty("product_id")
    private Integer productId;
    @JsonProperty("brand_id")
    private Integer brandId;
    @JsonProperty("pricing")
    private PricingDTO pricing;

    public BrandProductPriceDTO(Integer productId, Integer brandId, PricingDTO pricing) {
        this.productId = productId;
        this.brandId = brandId;
        this.pricing = pricing;
    }


    public BrandProductPriceDTO() {
    }

    public BrandProductPriceDTO buildFrom(Price price) {
        this.productId = price.getProductId();
        this.brandId = price.getBrandId();
        this.pricing = new PricingDTO().buildFrom(price);
        return this;
    }
}
