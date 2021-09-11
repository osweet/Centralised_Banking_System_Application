package com.example.bma.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "banking_operations")
public class BankingOperationEntity {

    @Id
    @Column(name = "operation_id", nullable = false, unique = true, length = 10)
    private String operationId;

    @Column(name = "operation_name", nullable = false, unique = true, length = 30)
    private String operationName;

    @Column(name = "operation_description", nullable = false)
    private String operationDescription;

    @OneToMany(mappedBy = "tokenOperationId")
    private Set<TokenEntity> operationTokens;

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
