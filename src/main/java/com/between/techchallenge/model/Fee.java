package com.between.techchallenge.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "FEES")
public class Fee {
    @Id
    private Integer priceList;
    private Double feeValue;

    public Fee() {

    }

    public Integer getPriceList() {
        return priceList;
    }

    public Double getFeeValue() {
        return feeValue;
    }
}
