package com.arobs.model.expense;

import com.arobs.model.parcel.ParcelModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PesticideExpenseListModel {

    private Long expenseId;
    private Date expenseDate;

    private String pesticideType;
    private String pesticideName;
    private String phase;
    private String result;
    private String comments;

    private List<ParcelModel> parcels = new ArrayList<>();
    private String crop;
//    private String variety;
//    private String icon;
//    private String unitOfMeasure;
//    private Double area;
//    private Double normSow1Ha;
//    private Double normSowTotal;
//    private Double actualSown1Ha;
//    private Double sownTotal;
//    private BigDecimal unitPrice;
//    private BigDecimal totalAmount;

    private Date createdAt;
    private String createdBy;

    public PesticideExpenseListModel() {
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

    public String getPesticideType() {
        return pesticideType;
    }

    public void setPesticideType(String pesticideType) {
        this.pesticideType = pesticideType;
    }

    public String getPesticideName() {
        return pesticideName;
    }

    public void setPesticideName(String pesticideName) {
        this.pesticideName = pesticideName;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public List<ParcelModel> getParcels() {
        return parcels;
    }

    public void setParcels(List<ParcelModel> parcels) {
        this.parcels = parcels;
    }

    public String getCrop() {
        return crop;
    }

    public void setCrop(String crop) {
        this.crop = crop;
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
