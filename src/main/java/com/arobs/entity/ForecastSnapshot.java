package com.arobs.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mihail.gorgos on 03.08.2018.
 */
@Entity
@Table(name = "forecast_snapshot")
public class ForecastSnapshot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "forecast_id")
    private Long forecastId;

//    @Column(name = "quantity_hectar")
//    private Double quantityHectar;
//
    @Column(name = "unit_of_measure")
    private String unitOfMeasure;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "created_at")
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getForecastId() {
        return forecastId;
    }

    public void setForecastId(Long forecastId) {
        this.forecastId = forecastId;
    }

//    public Double getQuantityHectar() {
//        return quantityHectar;
//    }
//
//    public void setQuantityHectar(Double quantityHectar) {
//        this.quantityHectar = quantityHectar;
//    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
