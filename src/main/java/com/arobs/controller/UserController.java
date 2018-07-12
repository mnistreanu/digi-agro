package com.arobs.controller;

import com.arobs.entity.UserAccount;
import com.arobs.model.userAccount.UserAccountLightModel;
import com.arobs.model.userAccount.UserAccountModel;
import com.arobs.service.AuthService;
import com.arobs.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<UserAccountLightModel>> getModels() {

        List<UserAccount> userAccounts;
        if (authService.isSuperAdminAdmin()) {
            userAccounts = userAccountService.findAdmins();
        }
        else {
            userAccounts = userAccountService.findUsers();
        }

        List<UserAccountLightModel> models = userAccounts.stream().map(UserAccountLightModel::new).collect(Collectors.toList());

        return ResponseEntity.ok(models);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserAccountModel> getModel(@PathVariable Long id) {
        return ResponseEntity.ok(userAccountService.findModelById(id));
    }

    @RequestMapping(value = "/checkUsernameUnique", method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkUsernameUnique(@RequestParam("id") Long id, @RequestParam("username") String username) {
        return ResponseEntity.ok(userAccountService.checkUsernameUnique(id, username));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<UserAccountModel> save(@RequestBody UserAccountModel model) {
        return ResponseEntity.ok(new UserAccountModel(userAccountService.save(model)));
    }

    @RequestMapping(value = "/saveProfile", method = RequestMethod.POST)
    public ResponseEntity<UserAccountModel> saveProfile(@RequestBody UserAccountModel model) {
        return ResponseEntity.ok(new UserAccountModel(userAccountService.saveProfile(model)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN','ROLE_ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long id) {
        userAccountService.remove(id);
    }

}
