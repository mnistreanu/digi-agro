package com.arobs.model.expense;

import com.arobs.model.parcel.ParcelModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SowingExpenseModel {

    private Long expenseId;
    private Date expenseDate;

    private List<Long> parcels = new ArrayList<>();

    private String crop;
    private String variety;
    private String icon;
    private String unitOfMeasure;
    private Double area;
    private Double normSown1Ha;
    private Double actualSown1Ha;
    private BigDecimal unitPrice;

    private Date createdAt;
    private String createdBy;

    public SowingExpenseModel() {
    }

    public Double getNormSownTotal() {
        return this.area * this.normSown1Ha;
    }

    public BigDecimal getTotalAmount() {
        return BigDecimal.valueOf(unitPrice.doubleValue() * this.getActualSownTotal());
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Date getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }

    public List<Long> getParcels() {
        return parcels;
    }

    public void setParcels(List<Long> parcels) {
        this.parcels = parcels;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String cropVariety) {
        this.variety = cropVariety;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getNormSown1Ha() {
        return normSown1Ha;
    }

    public void setNormSown1Ha(Double normSown1Ha) {
        this.normSown1Ha = normSown1Ha;
    }

    public Double getActualSown1Ha() {
        return actualSown1Ha;
    }

    public void setActualSown1Ha(Double actualSown1Ha) {
        this.actualSown1Ha = actualSown1Ha;
    }

    public Double getActualSownTotal() {
        return this.area * this.actualSown1Ha;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

}
