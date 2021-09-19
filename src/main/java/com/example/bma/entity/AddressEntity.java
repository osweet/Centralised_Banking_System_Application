package com.example.bma.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "addresses")
public class AddressEntity {

    @Id
    @Column(name = "address_id", nullable = false, unique = true, length = 10)
    private String addressId;

    @Column(name = "address_building_number", length = 3)
    private Integer addressBuildingNumber;

    @Column(name = "address_line_1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "address_line_3")
    private String addressLine3;

    @Column(name = "address_landmark", nullable = false)
    private String addressLandmark;

    @ManyToOne
    @JoinColumn(name = "address_city", nullable = false)
    private CityEntity addressCity;

    @Column(name = "address_zip_code", nullable = false, length = 10)
    private String addressZipCode;

    @Column(name = "address_verified", nullable = false)
    private Boolean addressVerified;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_updated_on", nullable = false)
    private Date lastUpdatedOn;

    @Column(name = "last_updated_by", nullable = false)
    private String lastUpdatedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_on", nullable = false)
    private Date createdOn;

    @Column(name = "created_by", nullable = false)
    private String createdBy;
}
