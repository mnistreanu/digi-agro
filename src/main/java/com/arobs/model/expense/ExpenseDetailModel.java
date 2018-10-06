package com.arobs.model.expense;


import com.arobs.entity.ExpenseDetail;
import com.arobs.enums.ExpenseDetailType;

public class ExpenseDetailModel {

    private Long id;

    private ExpenseDetailType itemKey;
    private String itemValue;

    public ExpenseDetailModel(ExpenseDetail entity) {
        id = entity.getId();
        itemKey = entity.getItemKey();
        itemValue = entity.getItemValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExpenseDetailType getItemKey() {
        return itemKey;
    }

    public void setItemKey(ExpenseDetailType itemKey) {
        this.itemKey = itemKey;
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }
}
