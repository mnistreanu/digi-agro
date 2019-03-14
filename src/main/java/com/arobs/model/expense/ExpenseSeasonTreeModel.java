package com.arobs.model.expense;

import com.arobs.model.crop.CropSeasonModel;
import com.arobs.utils.NumericUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseSeasonTreeModel {

    /**
     * only root contains: season information
     */
    private CropSeasonModel cropSeasonModel;
    private String categoryName;

    /**
     * root: in frontend will be populated based on season information
     * others: categoryName
     */
    private String title;

    private Map<Integer, BigDecimal> values;
    private BigDecimal totalCost;

    private List<ExpenseSeasonTreeModel> children;

    public void append(ExpenseSeasonTreeModel model) {
        if (model.getValues() == null || model.getValues().isEmpty()) {
            return;
        }

        if (values == null) {
            values = new HashMap<>();
        }

        for (Map.Entry<Integer, BigDecimal> entry : model.getValues().entrySet()) {
            Integer periodIndex = entry.getKey();
            BigDecimal value = entry.getValue();
            values.put(periodIndex, NumericUtil.calc(BigDecimal::add, value, values.get(periodIndex)));
        }
    }

    public void calcTotalCost() {
        totalCost = BigDecimal.ZERO;
        if (values != null ) {
            for (BigDecimal periodCost : values.values()) {
                totalCost = NumericUtil.calc(BigDecimal::add, totalCost, periodCost);
            }
        }
    }

    public CropSeasonModel getCropSeasonModel() {
        return cropSeasonModel;
    }

    public void setCropSeasonModel(CropSeasonModel cropSeasonModel) {
        this.cropSeasonModel = cropSeasonModel;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Map<Integer, BigDecimal> getValues() {
        return values;
    }

    public void setValues(Map<Integer, BigDecimal> values) {
        this.values = values;
    }

    public List<ExpenseSeasonTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<ExpenseSeasonTreeModel> children) {
        this.children = children;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
