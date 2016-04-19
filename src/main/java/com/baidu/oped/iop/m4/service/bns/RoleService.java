package com.baidu.oped.iop.m4.service.bns;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_DELETE;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_GET;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_POST;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_PUT;

import com.baidu.oped.iop.m4.mvc.dto.bns.MergedRole;
import com.baidu.oped.iop.m4.utils.PageData;
import com.baidu.oped.sia.boot.common.BaseResponse;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;
import java.util.Map;

/**
 * Role Client for bns application.
 *
 * @author mason
 */
@FeignClient(name = "bnsRoleClient", url = "${project.service-man-url}")
public interface RoleService {

    String ROLES_MAPPING_PREFIX = "roles";
    String NAME_PATH = "/{" + NAME + "}";

    /**
     * Search roles of the login
     *
     * @param queryMap query param map
     * @return role page
     */
    @RequestLine(V1_GET + ROLES_MAPPING_PREFIX)
    BaseResponse<PageData<MergedRole>> search(@QueryMap Map<String, List<String>> queryMap);

    /**
     * Create role operation.
     *
     * @param role role
     * @return create role
     */
    @RequestLine(V1_POST + ROLES_MAPPING_PREFIX)
    BaseResponse<MergedRole> create(MergedRole role);

    /**
     * Find role operation.
     *
     * @param name role name
     * @return role
     */
    @RequestLine(V1_GET + ROLES_MAPPING_PREFIX + NAME_PATH)
    BaseResponse<MergedRole> find(@Param(NAME) String name);

    /**
     * Update role operation.
     *
     * @param name role name
     * @param role role
     * @return updated role
     */
    @RequestLine(V1_PUT + ROLES_MAPPING_PREFIX + NAME_PATH)
    BaseResponse<MergedRole> update(@Param(NAME) String name, MergedRole role);

    /**
     * Delete role operation.
     *
     * @param name role name
     * @return deleted role
     */
    @RequestLine(V1_DELETE + ROLES_MAPPING_PREFIX + NAME_PATH)
    BaseResponse<MergedRole> delete(@Param(NAME) String name);
}
