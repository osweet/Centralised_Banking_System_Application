package com.example.bma.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "banks")
public class BankEntity {

    @Id
    @Column(name = "bank_id", nullable = false, unique = true, length = 10)
    private String bankId;

    @Column(name = "bank_name", nullable = false, unique = true)
    private String bankName;

    @Column(name = "bank_email", nullable = false, unique = true, length = 50)
    private String bankEmail;

    @OneToOne
    @JoinColumn(name = "bank_address", nullable = false)
    private AddressEntity bankAddress;

    @OneToMany(mappedBy = "bank")
    private Set<BranchEntity> bankBranches;

    @OneToMany(mappedBy = "tokenBank")
    private Set<TokenEntity> bankTokens;

    @Temporal(TemporalType.DATE)
    @Column(name = "last_updated_on", nullable = false)
    private Date lastUpdatedOn;

    @Column(name = "last_updated_by", nullable = false)
    private String lastUpdatedBy;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_on", nullable = false, updatable = false)
    private Date createdOn;

    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;
}
