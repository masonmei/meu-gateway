package com.baidu.oped.iop.m4.mvc.rest.bns;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.API_V1;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import com.baidu.oped.iop.m4.mvc.dto.bns.MergedRole;
import com.baidu.oped.iop.m4.mvc.dto.bns.MergedUser;
import com.baidu.oped.iop.m4.service.bns.RoleService;
import com.baidu.oped.iop.m4.utils.PageData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Role controller.
 *
 * @author mason
 */
@RestController
@RequestMapping(API_V1 + "roles")
public class RoleController {
    private static final Logger LOG = LoggerFactory.getLogger(RoleController.class);

    private static final String USER_ID = "userId";
    private static final String NAME_PATH = "{" + NAME + "}";

    @Autowired
    private RoleService roleService;

    @RequestMapping(method = GET)
    public PageData<MergedRole> search(@RequestParam MultiValueMap<String, String> queryMap) {
        LOG.debug("Search Products.");

        return roleService.search(queryMap).getData();
    }

    @RequestMapping(method = POST)
    public MergedRole create(@RequestBody MergedRole role) {
        LOG.debug("Create role with : {}", role);
        return roleService.create(role)
                .getData();
    }

    @RequestMapping(value = NAME_PATH, method = GET)
    public MergedRole find(@PathVariable(NAME) String name) {
        LOG.debug("Find User with name: {}", name);

        return roleService.find(name)
                .getData();
    }

    @RequestMapping(value = NAME_PATH, method = PUT)
    public MergedRole update(@PathVariable(NAME) String name, @RequestBody MergedRole role) {
        LOG.debug("Update role: {} with: {}", name, role);
        return roleService.update(name, role)
                .getData();
    }

    @RequestMapping(value = NAME_PATH, method = DELETE)
    public MergedRole delete(@PathVariable(NAME) String name) {
        LOG.debug("Delete user: {}", name);
        return roleService.delete(name).getData();
    }

}
