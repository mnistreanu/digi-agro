package com.arobs.model.expense;

import com.arobs.model.CropModel;
import com.arobs.model.chemicals.FertilizerModel;
import com.arobs.model.chemicals.PesticideModel;
import com.arobs.model.parcel.ParcelModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FertilizerExpenseListModel {

    private Long expenseId;
    private Date expenseDate;

    private FertilizerModel fertilizerModel;
    private String phase;
    private String result;
    private String comments;

    private List<ParcelModel> parcels = new ArrayList<>();
    private CropModel cropModel;

    private Date createdAt;
    private String createdBy;

    public FertilizerExpenseListModel() {
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


    public FertilizerModel getFertilizerModel() {
        return fertilizerModel;
    }

    public void setFertilizerModel(FertilizerModel fertilizerModel) {
        this.fertilizerModel = fertilizerModel;
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

    public CropModel getCropModel() {
        return cropModel;
    }

    public void setCropModel(CropModel cropModel) {
        this.cropModel = cropModel;
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
