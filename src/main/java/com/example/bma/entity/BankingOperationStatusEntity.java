package com.example.bma.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "banking_operation_statuses")
public class BankingOperationStatusEntity {

    @Id
    @Column(name = "status_id", nullable = false, unique = true, length = 10)
    private String statusId;

    @Column(name = "status_name", nullable = false, unique = true, length = 30)
    private String statusName;

    @Column(name = "status_description", nullable = false)
    private String statusDescription;

    @OneToMany(mappedBy = "tokenOperationStatus")
    private Set<TokenEntity> statusTokens;

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
