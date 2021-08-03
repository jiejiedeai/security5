package com.demo.security.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.security.domain.mapper.RoleUserMapper;
import com.demo.security.domain.model.RoleUser;
import com.demo.security.domain.service.RoleUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qp
 * @since 2021-08-03
 */
@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements RoleUserService {

}
