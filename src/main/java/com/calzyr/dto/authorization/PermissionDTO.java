package com.calzyr.dto.authorization;

import com.calzyr.entity.authorization.Permission;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionDTO {
    private int permission_id;
    private String permission_name;
    private String permission_description;

    public PermissionDTO(Permission permission){
        this.permission_id = permission.getPermissionId();
        this.permission_name = permission.getPermissionName();
        this.permission_description = permission.getPermissionDescription();
    }
}
