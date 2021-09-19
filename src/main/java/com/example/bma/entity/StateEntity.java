package com.example.bma.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "states")
public class StateEntity {

    @Id
    @Column(name = "state_id", nullable = false, unique = true, length = 10)
    private String stateId;

    @Column(name = "state_name", nullable = false)
    private String stateName;

    @ManyToOne
    @JoinColumn(name = "country", nullable = false)
    private CountryEntity country;

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
