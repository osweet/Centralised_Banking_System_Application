package com.example.bma.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @Column(name = "user_id", nullable = false, unique = true, length = 10)
    private String userId;

    @Column(name = "user_first_name", nullable = false, length = 20)
    private String userFirstName;

    @Column(name = "user_middle_name", length = 20)
    private String userMiddleName;

    @Column(name = "user_last_name", nullable = false, length = 20)
    private String userLastName;

    @Column(name = "user_contact", nullable = false, unique = true, length = 10)
    private Long userContact;

    @Column(name = "user_email", nullable = false, unique = true, length = 50)
    private String userEmail;

    @Column(name = "user_password", nullable = false)
    private String userPassword;

    @Column(name = "user_enabled", nullable = false)
    private Boolean userEnabled = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "tokenUser", fetch = FetchType.LAZY)
    private Set<TokenEntity> userTokens = new HashSet<>();

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
