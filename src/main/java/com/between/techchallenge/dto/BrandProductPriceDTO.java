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

    private BrandProductPriceDTO(BrandProductPriceDTOBuilder builder) {
        this.productId = builder.productId;
        this.brandId = builder.brandId;
        this.pricing = builder.pricing;
    }

    public static class BrandProductPriceDTOBuilder
    {
        private Integer productId;
        private Integer brandId;
        private PricingDTO pricing;

        public BrandProductPriceDTOBuilder() {

        }

        public BrandProductPriceDTOBuilder withProductId(Integer productId) {
            this.productId = productId;
            return this;
        }

        public BrandProductPriceDTOBuilder withBrandId(Integer brandId) {
            this.brandId = brandId;
            return this;
        }

        public BrandProductPriceDTOBuilder withPricingBuilder(PricingDTO.PricingDTOBuilder pricingDTO) {
            this.pricing = pricingDTO.build();
            return this;
        }

        public BrandProductPriceDTO build() {
            return new BrandProductPriceDTO(this);
        }
    }
}
