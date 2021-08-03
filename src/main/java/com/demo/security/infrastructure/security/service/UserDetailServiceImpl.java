package com.demo.security.infrastructure.security.service;

import com.demo.security.domain.mapper.UserMapper;
import com.demo.security.domain.model.Role;
import com.demo.security.domain.vo.UserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 登录认证逻辑
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserRoleVo> userOptional = Optional.ofNullable(userMapper.searchUserIsExists(username));
        userOptional.orElseThrow(() -> new UsernameNotFoundException(username));
        UserRoleVo userInfo = userOptional.get();
        User user= new User(userInfo.getUsername(),userInfo.getPassword(),
                getAuthorities(userInfo.getRoleList()));
        return user;
    }

    /**
     * 将用户角色转换成Security需要的
     * @param roleList
     * @return
     */
    private Collection<GrantedAuthority> getAuthorities(List<Role> roleList){
        if(!CollectionUtils.isEmpty(roleList)){
            String [] roleNames = roleList.stream()
                    .map(role -> role.getId().toString())
                    .collect(Collectors.toList())
                    .toArray(new String[roleList.size()]);
            return AuthorityUtils.createAuthorityList(roleNames);
        }else{
            return AuthorityUtils.createAuthorityList();
        }
    }
}
