package com.demo.security.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.security.domain.mapper.UserMapper;
import com.demo.security.domain.model.User;
import com.demo.security.domain.service.UserService;
import com.demo.security.infrastructure.base.JsonResult;
import com.demo.security.interfaces.dto.CommonPageDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qp
 * @since 2021-08-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public JsonResult<IPage<User>> searchUserPage(CommonPageDTO commonPageDTO) {
        Page<User> page = new Page<>(commonPageDTO.getPage(),commonPageDTO.getSize());
        IPage<User> userPage = userMapper.selectUserByPage(page,commonPageDTO.getSearchKey());
        return JsonResult.success(userPage);
    }

    @Override
    public JsonResult<List<User>> searchUserList(String searchKey) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(searchKey),User::getName,searchKey);
        List<User> users = userMapper.selectList(queryWrapper);
        return JsonResult.success(users);
    }
}
