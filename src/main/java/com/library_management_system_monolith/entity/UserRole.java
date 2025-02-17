package com.library_management_system_monolith.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "USER_ROLES")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Jika tidak ada PK gabungan, bisa dibuat field surrogate

    @Column(name = "user_id")
    private Integer userId;  // Foreign key ke USERS

    @Column(name = "role_id")
    private Integer roleId;  // Foreign key ke ROLES

    public UserRole() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
