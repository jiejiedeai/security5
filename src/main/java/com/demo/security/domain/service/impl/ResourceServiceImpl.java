package com.demo.security.domain.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.security.domain.mapper.ResourceMapper;
import com.demo.security.domain.model.Resource;
import com.demo.security.domain.service.ResourceService;
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
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

}
