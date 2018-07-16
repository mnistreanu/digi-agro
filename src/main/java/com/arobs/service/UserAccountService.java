package com.arobs.service;


import com.arobs.entity.Tenant;
import com.arobs.entity.TenantBranch;
import com.arobs.entity.UserAccount;
import com.arobs.interfaces.HasRepository;
import com.arobs.model.userAccount.UserAccountModel;
import com.arobs.repository.UserAccountRepository;
import com.arobs.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class UserAccountService implements HasRepository<UserAccountRepository> {

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private TenantService tenantService;
    @Autowired
    private TenantBranchService tenantBranchService;
    @Autowired
    private FileService fileService;

    public boolean validateUsername(Long id, String username) {
        if (id == -1) {
            return getRepository().countByUsername(username) == 0;
        }
        return getRepository().countByUsernameEscapeId(id, username) == 0;
    }

    public UserAccount findOne(Long id) {
        return getRepository().findOne(id);
    }

    public UserAccount findByUsername(String username) {
        return getRepository().findByUsername(username);
    }

    public UserAccountModel findModelById(Long id) {
        return new UserAccountModel(getRepository().findOne(id));
    }

    public List<UserAccount> findAdmins() {
        return getRepository().findAdmins();
    }

    public List<UserAccount> findUsers() {
        return getRepository().findUsers();
    }

    @Transactional
    public void remove(Long id) {
        getRepository().remove(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public UserAccount save(UserAccountModel model) {
        UserAccount userAccount;

        if (model.getId() == null) {
            userAccount = new UserAccount();
            userAccount.setAuthorities(authorityService.findAllByName(model.getRoleName()));
        }
        else {
            userAccount = findOne(model.getId());
        }

        copyValues(userAccount, model);
        return getRepository().save(userAccount);
    }

    private void copyValues(UserAccount userAccount, UserAccountModel model) {

        copyProfileValues(userAccount, model);

        if (model.getTenants() != null) {
            List<Tenant> tenants = tenantService.findByIds(model.getTenants());
            if (userAccount.getTenants() == null) {
                userAccount.setTenants(tenants);
            }
            else {
                userAccount.getTenants().clear();
                userAccount.getTenants().addAll(tenants);
            }
        }

        if (model.getBranches() != null) {
            List<TenantBranch> branches = tenantBranchService.findByIds(model.getBranches());
            if (userAccount.getBranches() == null) {
                userAccount.setBranches(branches);
            }
            else {
                userAccount.getBranches().clear();
                userAccount.getBranches().addAll(branches);
            }
        }
    }

    private void copyProfileValues(UserAccount userAccount, UserAccountModel model) {
        userAccount.setUsername(model.getUsername());
        userAccount.setEmail(model.getEmail());
        userAccount.setFirstName(model.getFirstName());
        userAccount.setLastName(model.getLastName());
        userAccount.setAddress(model.getAddress());
        userAccount.setPhone(model.getPhone());
        userAccount.setMobilePhone(model.getMobilePhone());

        if (model.getPassword() != null) {
            userAccount.setPassword(SecurityUtil.encryptPassword(model.getPassword()));
        }
    }

    @Override
    public UserAccountRepository getRepository() {
        return userAccountRepository;
    }

    @Transactional
    public UserAccount saveProfile(UserAccountModel model, MultipartFile file) throws IOException {
        UserAccount userAccount = findOne(model.getId());

        if (file != null) {
            String logoPath = fileService.upload(userAccount.getId(), file);
            userAccount.setLogoUrl("/file?path=" + logoPath);
        }

        copyProfileValues(userAccount, model);
        return getRepository().save(userAccount);
    }
}
