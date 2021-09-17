package com.example.bma.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "countries")
public class CountryEntity {

    @Id
    @Column(name = "country_id", nullable = false, unique = true, length = 10)
    private String countryId;

    @Column(name = "country_name", nullable = false, unique = true)
    private String countryName;

    @Column(name = "country_short_name", nullable = false, unique = true, length = 5)
    private String countryShortName;

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
