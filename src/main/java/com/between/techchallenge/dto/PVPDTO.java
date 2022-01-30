package com.between.techchallenge.dto;

import com.between.techchallenge.model.Price;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PVPDTO {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("total")
    private Double total;

    PVPDTO buildFrom(Price price) {
        this.id = price.getId();
        this.total = price.getPrice();
        return this;
    }
}
