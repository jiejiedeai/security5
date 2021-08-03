package com.demo.security.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.security.domain.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qp
 * @since 2021-08-03
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询用户id查询角色列表
     * @param userId
     * @return
     */
    List<Role> searchUserRoles(
            @Param("userId") Integer userId);

}
