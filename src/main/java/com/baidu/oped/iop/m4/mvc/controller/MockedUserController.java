package com.baidu.oped.iop.m4.mvc.controller;

import static com.baidu.oped.sia.boot.utils.Constrains.Profile.DEV;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.baidu.oped.iop.m4.custom.security.AppAuthority;
import com.baidu.oped.iop.m4.custom.security.AppUserDetails;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * Product controller for testing purpose.
 *
 * @author mason
 */
@RestController
@RequestMapping("users")
@Profile(DEV)
public class MockedUserController {

    public static final Set<String> ROLES = new HashSet<>();

    static {
        ROLES.add("TestRole");
    }

    @RequestMapping(value = "{userName}", method = GET)
    public AppUserDetails myInfo(@PathVariable("userName") String userName) {
        AppUserDetails appUserDetails = new AppUserDetails();
        appUserDetails.setName(userName);
        Set<AppAuthority> roles = new HashSet<>();
        AppAuthority role = new AppAuthority("productName", ROLES.iterator()
                .next());
        roles.add(role);
        appUserDetails.setRoles(roles);
        return appUserDetails;
    }

}
