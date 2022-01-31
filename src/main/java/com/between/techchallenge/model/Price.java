package com.between.techchallenge.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.util.Date;

@Data
@Entity
@Table(name = "PRICES")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer brandId;
    private Date startDate;
    private Date endDate;
    @ManyToOne
    @JoinColumn(name = "PRICE_LIST")
    private Fee fees;
    private Integer productId;
    private Integer priority;
    private Double price;
    private String curr;

    public Price() {

    }

    public Integer getId() {
        return id;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Fee getFees() {
        return fees;
    }

    public Integer getPriority() {
        return priority;
    }

    public Double getPrice() {
        return price;
    }

    public String getCurr() {
        return curr;
    }
}
