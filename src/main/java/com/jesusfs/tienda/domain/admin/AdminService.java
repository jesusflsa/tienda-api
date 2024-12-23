package com.jesusfs.tienda.domain.admin;

import com.jesusfs.tienda.domain.admin.dto.CreateAdminDTO;
import com.jesusfs.tienda.domain.admin.dto.UpdateAdminDTO;

import java.util.List;

public interface AdminService {
    List<Admin> getAdmins();
    Admin getAdminById(Long id);
    Admin createAdmin(CreateAdminDTO userDTO);
    void deleteAdmin(Long id);
    Admin updateAdmin(Long id, UpdateAdminDTO userDTO);
}
