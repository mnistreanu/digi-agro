package com.arobs.model.crop;

import com.arobs.entity.crop.Crop;
import com.arobs.entity.crop.CropCategory;
import com.arobs.entity.crop.CropSubculture;
import com.arobs.entity.crop.CropVariety;

import java.io.Serializable;
import java.util.List;

public class CropVarietyTreeModel implements Serializable {

    private Long cropCategoryId;
    private Long cropId;
    private Long cropSubcultureId;
    private Long cropVarietyId;
    private String nameRo;
    private String nameRu;
    private String descriptionRo;
    private String descriptionRu;

    private List<CropVarietyTreeModel> children;

    public CropVarietyTreeModel() {
    }

    public CropVarietyTreeModel(CropCategory category) {
        this.cropCategoryId = category.getId();
        this.nameRo = category.getNameRo();
        this.nameRu = category.getNameRu();
    }

    public CropVarietyTreeModel(Crop crop) {
        this.cropId = crop.getId();
        this.nameRo = crop.getNameRo();
        this.nameRu = crop.getNameRu();
    }

    public CropVarietyTreeModel(CropSubculture subculture) {
        this.cropId = subculture.getId();
        this.nameRo = subculture.getNameRo();
        this.nameRu = subculture.getNameRu();
    }

    public CropVarietyTreeModel(CropVariety cropVariety) {
        if (cropVariety.getCropSubculture() != null) {
            this.cropSubcultureId = cropVariety.getCropSubculture().getId();
        }
        this.cropVarietyId = cropVariety.getId();
        this.nameRo = cropVariety.getNameRo();
        this.nameRu = cropVariety.getNameRu();
        this.descriptionRo = cropVariety.getDescriptionRo();
        this.descriptionRu = cropVariety.getDescriptionRu();
    }

    public Long getCropCategoryId() {
        return cropCategoryId;
    }

    public void setCropCategoryId(Long cropCategoryId) {
        this.cropCategoryId = cropCategoryId;
    }

    public Long getCropId() {
        return cropId;
    }

    public void setCropId(Long cropId) {
        this.cropId = cropId;
    }

    public Long getCropSubcultureId() {
        return cropSubcultureId;
    }

    public void setCropSubcultureId(Long cropSubcultureId) {
        this.cropSubcultureId = cropSubcultureId;
    }

    public Long getCropVarietyId() {
        return cropVarietyId;
    }

    public void setCropVarietyId(Long cropVarietyId) {
        this.cropVarietyId = cropVarietyId;
    }

    public String getNameRo() {
        return nameRo;
    }

    public void setNameRo(String nameRo) {
        this.nameRo = nameRo;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getDescriptionRo() {
        return descriptionRo;
    }

    public void setDescriptionRo(String descriptionRo) {
        this.descriptionRo = descriptionRo;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public void setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
    }

    public List<CropVarietyTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<CropVarietyTreeModel> children) {
        this.children = children;
    }
}
