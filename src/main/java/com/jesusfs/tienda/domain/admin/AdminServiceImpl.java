package com.jesusfs.tienda.domain.admin;

import com.jesusfs.tienda.domain.user.UserServiceImpl;
import com.jesusfs.tienda.domain.admin.dto.CreateAdminDTO;
import com.jesusfs.tienda.domain.admin.dto.UpdateAdminDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private AdminRepository adminRepository;
    private UserServiceImpl userService;

    @Override
    public List<Admin> getAdmins() {
        return adminRepository.findByActiveTrue();
    }

    @Override
    public Admin getAdminById(Long id) {
        Optional<Admin> opAdmin = adminRepository.findByIdAndActiveTrue(id);
        if (opAdmin.isEmpty()) throw new RuntimeException("Admin not exists.");

        return opAdmin.get();
    }

    @Override
    public Admin createAdmin(CreateAdminDTO adminDTO) {
        userService.validateUsername(adminDTO.username());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Admin admin = new Admin();
        admin.setUsername(adminDTO.username());
        admin.setPassword(passwordEncoder.encode(adminDTO.password()));
        return adminRepository.save(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        Admin admin = getAdminById(id);
        admin.setActive(false);
        adminRepository.save(admin);
    }

    @Override
    public Admin updateAdmin(Long id, UpdateAdminDTO adminDTO) {
        Admin admin = getAdminById(id);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        admin.setUsername(adminDTO.username());
        admin.setPassword(passwordEncoder.encode(adminDTO.password()));

        return adminRepository.save(admin);
    }
}
