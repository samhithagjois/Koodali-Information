package koodali.service;

import koodali.model.AdminPermissions;
import koodali.model.Administrator;
import koodali.repository.AdminRepository;
import koodali.service.exceptions.AdminNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService extends PersonService<Administrator> {
    //createAdmin
    //checkCredentials

    private final AdminRepository adminRepo;

    public AdministratorService(AdminRepository adminRepo) {
        super(adminRepo);
        this.adminRepo = adminRepo;
    }

    public Administrator findByID(String adminID) {
        return adminRepo
                .findById(adminID)
                .orElseThrow(AdminNotFoundException::new);
    }

    public Administrator addAdminRights(Administrator admin, AdminPermissions permission) {
        if (!admin.getPermissions().contains(permission)) {
            admin.addPermission(permission);
            return admin;
        }
        return admin;
    }

    public Administrator removeAdminRights(Administrator admin, AdminPermissions permission) {
        if (admin.getPermissions().contains(permission)) {
            admin.getPermissions().remove(permission);
            return admin;
        }
        return admin;
    }

}
