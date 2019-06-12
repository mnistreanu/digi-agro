package com.arobs.model.crop;

import com.arobs.entity.crop.Crop;
import com.arobs.entity.crop.CropSubculture;

import java.io.Serializable;

public class CropSubcultureModel implements Serializable {

    private Long cropCategoryId;
    private Long cropId;
    private String cropNameRo;
    private String cropNameRu;
    private Long id;
    private String nameRo;
    private String nameRu;
    private String descriptionRo;
    private String descriptionRu;

    public CropSubcultureModel() {
    }

    public CropSubcultureModel(CropSubculture subCrop) {
        this.id = subCrop.getId();
        Crop crop = subCrop.getCrop();
        if (crop != null) {
            this.cropId = crop.getId();
            this.cropCategoryId = crop.getCropCategoryId();
            this.cropNameRo = crop.getNameRo();
            this.cropNameRu = crop.getNameRu();
        }
        this.nameRo = subCrop.getNameRo();
        this.nameRu = subCrop.getNameRu();
        this.descriptionRo = subCrop.getDescriptionRo();
        this.descriptionRu = subCrop.getDescriptionRu();
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

    public String getCropNameRo() {
        return cropNameRo;
    }

    public void setCropNameRo(String cropNameRo) {
        this.cropNameRo = cropNameRo;
    }

    public String getCropNameRu() {
        return cropNameRu;
    }

    public void setCropNameRu(String cropNameRu) {
        this.cropNameRu = cropNameRu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
