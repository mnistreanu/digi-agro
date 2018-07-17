package com.arobs.controller;

import com.arobs.entity.UserAccount;
import com.arobs.model.userAccount.UserAccountListModel;
import com.arobs.model.userAccount.UserAccountModel;
import com.arobs.service.AuthService;
import com.arobs.service.UserAccountService;
import com.arobs.utils.StaticUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<UserAccountListModel>> getModels() {

        List<UserAccount> userAccounts;
        if (authService.isSuperAdminOrAdmin()) {
            userAccounts = userAccountService.findAdmins();
        }
        else {
            userAccounts = userAccountService.findUsers();
        }

        List<UserAccountListModel> models = userAccounts.stream().map(UserAccountListModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserAccountModel> getModel(@PathVariable Long id) {
        return ResponseEntity.ok(userAccountService.findModelById(id));
    }

    @RequestMapping(value = "/validate-username", method = RequestMethod.GET)
    public ResponseEntity<Boolean> validateUsername(@RequestParam("id") Long id, @RequestParam("username") String username) {
        return ResponseEntity.ok(userAccountService.validateUsername(id, username));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<UserAccountModel> save(@RequestBody UserAccountModel model) {
        return ResponseEntity.ok(new UserAccountModel(userAccountService.save(model)));
    }

    @RequestMapping(value = "/save-profile", method = RequestMethod.POST)
    public ResponseEntity<UserAccountModel> saveProfile(@RequestParam("model") String modelJson,
                                                        @RequestParam("file") Optional<MultipartFile> fileOptional) throws IOException {

        MultipartFile file = null;
        if (fileOptional.isPresent()) {
            file = fileOptional.get();
        }

        UserAccountModel model = StaticUtil.gson.fromJson(modelJson, UserAccountModel.class);

        return ResponseEntity.ok(new UserAccountModel(userAccountService.saveProfile(model, file)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        userAccountService.remove(id);
    }

}
