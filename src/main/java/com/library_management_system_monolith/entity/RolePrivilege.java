package com.library_management_system_monolith.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ROLE_PRIVILEGES")
public class RolePrivilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Field surrogate untuk primary key

    @Column(name = "role_id")
    private Integer roleId;  // Foreign key ke ROLES

    @Column(name = "privilege_id")
    private Integer privilegeId;  // Foreign key ke PRIVILEGES

    public RolePrivilege() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }
}
