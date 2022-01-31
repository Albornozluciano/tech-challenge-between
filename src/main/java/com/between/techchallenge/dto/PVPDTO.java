package com.between.techchallenge.dto;

import com.between.techchallenge.model.Price;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PVPDTO {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("total")
    private Double total;

    public PVPDTO(Integer id, Double total) {
        this.id = id;
        this.total = total;
    }

    public PVPDTO() {

    }

    PVPDTO buildFrom(Price price) {
        this.id = price.getId();
        this.total = price.getPrice();
        return this;
    }
}
