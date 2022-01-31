package com.between.techchallenge.dto;

import com.between.techchallenge.model.Price;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FeeDTO {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("total")
    private Double total;

    public FeeDTO(Integer id, Double total) {
        this.id = id;
        this.total = total;
    }

    public FeeDTO() {

    }

    FeeDTO buildFrom(Price price) {
        this.id = price.getFees().getPriceList();
        this.total = price.getFees().getFeeValue();
        return this;
    }
}
