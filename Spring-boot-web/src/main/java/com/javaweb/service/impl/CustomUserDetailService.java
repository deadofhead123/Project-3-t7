package com.javaweb.service.impl;

import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
//public class CustomUserDetailService implements UserDetailsService
public class CustomUserDetailService{

    @Autowired
    private IUserService userService;

//    @Override
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//        UserDTO userDTO = userService.findOneByUserNameAndStatus(name, 1);
//        if(userDTO == null){
//            throw new UsernameNotFoundException("Username not found");
//        }
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for(RoleDTO role: userDTO.getRoles()){
//            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getCode()));
//        }
//        MyUserDetail myUserDetail = new MyUserDetail(name,userDTO.getPassword(),true,true,true,true,authorities);
//        BeanUtils.copyProperties(userDTO, myUserDetail);
//        return myUserDetail;
//    }
}
