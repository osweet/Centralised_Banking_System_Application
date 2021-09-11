package com.example.bma.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tokens")
public class TokenEntity {

    @Id
    @Column(name = "token_id", nullable = false, unique = true, length = 10)
    private String tokenId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "token_timestamp", nullable = false)
    private Date tokenTimestamp;

    @ManyToOne
    @JoinColumn(name = "token_user", nullable = false)
    private UserEntity tokenUser;

    @ManyToOne
    @JoinColumn(name = "token_bank", nullable = false)
    private BankEntity tokenBank;

    @ManyToOne
    @JoinColumn(name = "token_branch", nullable = false)
    private BranchEntity tokenBranch;

    @ManyToOne
    @JoinColumn(name = "token_operation", nullable = false)
    private BankingOperationEntity tokenOperationId;

    @ManyToOne
    @JoinColumn(name = "token_operation_status", nullable = false)
    private BankingOperationStatusEntity tokenOperationStatus;
}
