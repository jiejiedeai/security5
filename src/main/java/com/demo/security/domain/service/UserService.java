package com.demo.security.domain.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.security.domain.model.User;
import com.demo.security.infrastructure.base.JsonResult;
import com.demo.security.interfaces.dto.CommonPageDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qp
 * @since 2021-08-03
 */
public interface UserService extends IService<User> {

    JsonResult<IPage<User>> searchUserPage(CommonPageDTO commonPageDTO);

    JsonResult<List<User>> searchUserList(String searchKey);
}
