package com.library_management_system_monolith.entity;
import com.library_management_system_monolith.repository.RolePrivilegeRepository;
import com.library_management_system_monolith.repository.RoleRepository;
import com.library_management_system_monolith.repository.UserRoleRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private final User user;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final RolePrivilegeRepository rolePrivilegeRepository;

    public UserDetails(User user, UserRoleRepository userRoleRepository, RoleRepository roleRepository, RolePrivilegeRepository rolePrivilegeRepository) {
        this.user = user;
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
        this.rolePrivilegeRepository = rolePrivilegeRepository;
    }

    public UserDetails(User user) {
        this.user = user;
        this.userRoleRepository = null; // Tetap inisialisasi, tapi null jika tidak digunakan
        this.roleRepository = null;
        this.rolePrivilegeRepository = null;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // Ambil semua role_id dari tabel USER_ROLES berdasarkan user_id
        List<Integer> roleIds = userRoleRepository.getRoleIdsByUserId(user.getUserId());

        for (Integer roleId : roleIds) {
            // Ambil nama role berdasarkan role_id
            String roleName = roleRepository.getRoleNameById(roleId);
            if (roleName != null) {
                authorities.add(new SimpleGrantedAuthority(roleName.toUpperCase()));

                // Ambil daftar privilege berdasarkan role_id
                List<String> privileges = rolePrivilegeRepository.getPrivilegesByRoleId(roleId);
                for (String privilege : privileges) {
                    authorities.add(new SimpleGrantedAuthority(privilege.toUpperCase()));
                }
            }
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // Menggunakan email sebagai username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getActive();
    }
}

