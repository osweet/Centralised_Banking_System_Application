package com.example.bma.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "branches")
public class BranchEntity {

    @Id
    @Column(name = "branch_id", nullable = false, unique = true, length = 10)
    private String branchId;

    @Column(name = "branch_name",nullable = false, unique = true)
    private String branchName;

    @Column(name = "branch_contact", nullable = false, unique = true, length = 12)
    private Long branchContact;

    @Column(name = "branch_email", nullable = false, unique = true, length = 50)
    private String branchEmail;

    @ManyToOne
    @JoinColumn(name = "bank_id", nullable = false)
    private BankEntity bank;

    @OneToOne
    @JoinColumn(name = "branch_address", nullable = false)
    private AddressEntity branchAddress;

    @OneToMany(mappedBy = "tokenBranch")
    private Set<TokenEntity> branchTokens;

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
