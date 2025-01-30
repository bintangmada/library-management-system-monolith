package com.library_management_system_monolith.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "settings")
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String settingKey;
    private String settingValue;
    private String description;
    
    // Getter dan Setter
    public Setting() {
    }

    public Setting(String settingKey, String settingValue, String description) {
        this.settingKey = settingKey;
        this.settingValue = settingValue;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSettingKey() {
        return settingKey;
    }

    public void setSettingKey(String settingKey) {
        this.settingKey = settingKey;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
