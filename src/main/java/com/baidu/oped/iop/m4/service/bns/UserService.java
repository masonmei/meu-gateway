package com.baidu.oped.iop.m4.service.bns;

import static com.baidu.oped.iop.m4.utils.ProjectConstans.Config.NAME;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_DELETE;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_GET;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_POST;
import static com.baidu.oped.iop.m4.utils.ProjectConstans.Version.V1_PUT;

import com.baidu.oped.iop.m4.custom.security.AppUserDetails;
import com.baidu.oped.iop.m4.mvc.dto.bns.MergedUser;
import com.baidu.oped.iop.m4.utils.PageData;
import com.baidu.oped.sia.boot.common.BaseResponse;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;
import java.util.Map;

/**
 * User Client for bns application.
 *
 * @author mason
 */
@FeignClient(name = "bnsUserClient", url = "${project.service-man-url}")
public interface UserService {

    String USERS_MAPPING_PREFIX = "users";
    String NAME_PATH = "/{" + NAME + "}";

    /**
     * Search users operation.
     *
     * @param queryMap query param map
     * @return user page
     */
    @RequestLine(V1_GET + USERS_MAPPING_PREFIX)
    BaseResponse<PageData<MergedUser>> search(@QueryMap Map<String, List<String>> queryMap);

    /**
     * Create user operation.
     *
     * @param user user
     * @return create user
     */
    @RequestLine(V1_POST + USERS_MAPPING_PREFIX)
    BaseResponse<MergedUser> create(MergedUser user);

    /**
     * Find user operation.
     *
     * @param username user name
     * @return user
     */
    @RequestLine(V1_GET + USERS_MAPPING_PREFIX + NAME_PATH)
    BaseResponse<MergedUser> find(@Param(NAME) String username);

    /**
     * Find user operation.
     *
     * @param username user name
     * @return user
     */
    @RequestLine(V1_GET + USERS_MAPPING_PREFIX + NAME_PATH + "?plat={platform}")
    BaseResponse<AppUserDetails> me(@Param(NAME) String username, @Param("platform") String platform);


    /**
     * Update user operation.
     *
     * @param username user name
     * @param user     user
     * @return updated user
     */
    @RequestLine(V1_PUT + USERS_MAPPING_PREFIX + NAME_PATH)
    BaseResponse<MergedUser> update(@Param(NAME) String username, MergedUser user);

    /**
     * Delete user operation.
     *
     * @param username user name
     * @return deleted user
     */
    @RequestLine(V1_DELETE + USERS_MAPPING_PREFIX + NAME_PATH)
    BaseResponse<MergedUser> delete(@Param(NAME) String username);
}
