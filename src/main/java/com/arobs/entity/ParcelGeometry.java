package com.arobs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by mihail.gorgos on 25.07.2018.
 *
 * Ppentru definirea geometriei parcelei se va folosi tabelul PARCEL_COORDS.Fiecare parcela are un
 * set de coordonate aferent acestei parcele. Respectiv perimetrul va defini hotarele parcelei.
 */
@Entity
@Table(name = "parcel_geometry")
public class ParcelGeometry {

    @Id
    @Column(name = "parcel_id")
    private Long parcelId;

    @Column(name = "coordinates", length = 4096)
    private String coordinates;

    public Long getParcelId() {
        return parcelId;
    }

    public void setParcelId(Long parcelId) {
        this.parcelId = parcelId;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

}
