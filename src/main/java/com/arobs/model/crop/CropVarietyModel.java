package com.arobs.model.crop;

import com.arobs.entity.Crop;
import com.arobs.entity.CropSubculture;
import com.arobs.entity.CropVariety;

import java.io.Serializable;

public class CropVarietyModel implements Serializable {

//    private Long cropCategoryId;
//    private Long cropId;
    private Long id;
    private Long cropSubcultureId;
//    private String cropNameRo;
//    private String cropNameRu;
    private String nameRo;
    private String nameRu;
    private String descriptionRo;
    private String descriptionRu;

    public CropVarietyModel() {
    }

    public CropVarietyModel(CropVariety cropVariety) {
        this.id = cropVariety.getId();
        CropSubculture cropSubculture = cropVariety.getCropSubculture();
        if (cropSubculture != null) {
            this.cropSubcultureId = cropSubculture.getId();
        }
//        this.cropNameRo = cropSubculture.getNameRo();
//        this.cropNameRu = cropSubculture.getNameRu();
//        this.cropCategoryId = cropSubculture.getCropCategoryId();
        this.nameRo = cropVariety.getNameRo();
        this.nameRu = cropVariety.getNameRu();
        this.descriptionRo = cropVariety.getDescriptionRo();
        this.descriptionRu = cropVariety.getDescriptionRu();
//        this.seedConsumptionHa = cropVariety.getSeedConsumptionHa();
//        this.unitOfMeasure = cropVariety.getUnitOfMeasure();
    }

//    public Long getCropCategoryId() {
//        return cropCategoryId;
//    }
//
//    public void setCropCategoryId(Long cropCategoryId) {
//        this.cropCategoryId = cropCategoryId;
//    }
//
//    public Long getCropId() {
//        return cropId;
//    }
//
//    public void setCropId(Long cropId) {
//        this.cropId = cropId;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCropSubcultureId() {
        return cropSubcultureId;
    }

    public void setCropSubcultureId(Long cropSubcultureId) {
        this.cropSubcultureId = cropSubcultureId;
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

//
//    public String getCropNameRo() {
//        return cropNameRo;
//    }
//
//    public void setCropNameRo(String cropNameRo) {
//        this.cropNameRo = cropNameRo;
//    }
//
//    public String getCropNameRu() {
//        return cropNameRu;
//    }
//
//    public void setCropNameRu(String cropNameRu) {
//        this.cropNameRu = cropNameRu;
//    }
}
