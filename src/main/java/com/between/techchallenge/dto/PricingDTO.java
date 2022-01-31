package com.between.techchallenge.dto;

import com.between.techchallenge.model.Price;
import com.between.techchallenge.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PricingDTO {
    @JsonProperty("currency_id")
    private String currencyId;
    @JsonProperty("perceived_value")
    private PVPDTO perceivedValue;
    @JsonProperty("fee")
    private FeeDTO fee;
    @JsonProperty("application_from")
    private String applicationFrom;
    @JsonProperty("application_to")
    private String applicationTo;

    public PricingDTO(PricingDTOBuilder pricingDTOBuilder) {
        this.currencyId = pricingDTOBuilder.currencyId;
        this.perceivedValue = pricingDTOBuilder.perceivedValue;
        this.fee = pricingDTOBuilder.fee;
        this.applicationFrom = pricingDTOBuilder.applicationFrom;
        this.applicationTo = pricingDTOBuilder.applicationTo;
    }

    public PricingDTO() {

    }

    PricingDTO buildFrom(Price price) {
        this.currencyId = price.getCurr();
        this.perceivedValue = new PVPDTO().buildFrom(price);
        this.fee = new FeeDTO().buildFrom(price);
        this.applicationFrom = DateUtils.fromDateToString(price.getStartDate());
        this.applicationTo = DateUtils.fromDateToString(price.getEndDate());
        return this;
    }

    public static class PricingDTOBuilder
    {
        private String currencyId;
        private PVPDTO perceivedValue;
        private FeeDTO fee;
        private String applicationFrom;
        private String applicationTo;

        public PricingDTOBuilder() {

        }

        public PricingDTOBuilder withCurrencyId(String currencyId) {
            this.currencyId = currencyId;
            return this;
        }

        public PricingDTOBuilder withPerceivedValue(PVPDTO perceivedValue) {
            this.perceivedValue = perceivedValue;
            return this;
        }

        public PricingDTOBuilder withPerceivedValue(Integer id, Double value) {
            this.perceivedValue = new PVPDTO(id, value);
            return this;
        }

        public PricingDTOBuilder withFee(FeeDTO fee) {
            this.fee = fee;
            return this;
        }

        public PricingDTOBuilder withFee(Integer id, Double value) {
            this.fee = new FeeDTO(id, value);
            return this;
        }

        public PricingDTOBuilder withApplicationFrom(String applicationFrom) {
            this.applicationFrom = applicationFrom;
            return this;
        }

        public PricingDTOBuilder withApplicationTo(String applicationTo) {
            this.applicationTo = applicationTo;
            return this;
        }

        public PricingDTO build() {
            return new PricingDTO(this);
        }
    }
}
