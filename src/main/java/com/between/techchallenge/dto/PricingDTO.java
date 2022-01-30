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

    PricingDTO buildFrom(Price price) {
        this.currencyId = price.getCurr();
        this.perceivedValue = new PVPDTO().buildFrom(price);
        this.fee = new FeeDTO().buildFrom(price);
        this.applicationFrom = DateUtils.fromDateToString(price.getStartDate());
        this.applicationTo = DateUtils.fromDateToString(price.getEndDate());
        return this;
    }
}
