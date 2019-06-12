package com.arobs.entity.parcel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by mihail.gorgos on 25.07.2018.
 * <p>
 * Ppentru definirea geometriei parcelei se va folosi tabelul PARCEL_COORDS.Fiecare parcela are un
 * set de coordonate aferent acestei parcele si centrul geografic. Respectiv perimetrul va defini hotarele parcelei.
 */
@Entity
@Table(name = "parcel_geometry")
public class ParcelGeometry {

    @Id
    @Column(name = "parcel_id")
    private Long parcelId;

    @Column(name = "coordinates", length = 4096)
    private String coordinates;

    @Column(name = "lat_center", precision = 10, scale = 6)
    private BigDecimal latCenter;

    @Column(name = "lon_center", precision = 10, scale = 6)
    private BigDecimal lonCenter;

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

    public BigDecimal getLatCenter() {
        return latCenter;
    }

    public void setLatCenter(BigDecimal latCenter) {
        this.latCenter = latCenter;
    }

    public BigDecimal getLonCenter() {
        return lonCenter;
    }

    public void setLonCenter(BigDecimal lonCenter) {
        this.lonCenter = lonCenter;
    }
}
