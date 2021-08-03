package com.demo.security.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.security.domain.model.User;
import com.demo.security.domain.vo.UserRoleVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author qp
 * @since 2021-08-03
 */
public interface UserMapper extends BaseMapper<User> {

    IPage<User> selectUserByPage(
            Page<User> page,
            @Param("searchKey")String searchKey);

    UserRoleVo searchUserIsExists(String username);
}
