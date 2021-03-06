package com.example.bma.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @Column(name = "role_id", nullable = false, unique = true, length = 10)
    private String roleId;

    @Column(name = "role_name", nullable = false, unique = true, length = 30)
    private String roleName;

    @Column(name = "role_description", nullable = false)
    private String roleDescription;
}
