package com.library_management_system_monolith.payload;

public class RoleDto {

    private int roleId;
    private String roleName;

    public RoleDto() {
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
