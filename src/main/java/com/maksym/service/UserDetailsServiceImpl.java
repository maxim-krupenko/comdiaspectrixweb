package com.maksym.service;

import com.maksym.model.HospitalStaff;
import com.maksym.model.UserRole;
import com.maksym.repositories.HospitalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    HospitalStaffRepository hospitalStaffRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        HospitalStaff hospitalStaff = hospitalStaffRepository.findByLogin(username);
        List<GrantedAuthority> authorities =
                buildUserAuthority(hospitalStaff.getUserRole());

        return buildUserForAuthentication(hospitalStaff, authorities);

    }

    private User buildUserForAuthentication(HospitalStaff hospitalStaff,
                                            List<GrantedAuthority> authorities) {
        return new User(hospitalStaff.getLogin(), hospitalStaff.getPass(),
                true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(UserRole userRole) {

        Set<GrantedAuthority> setAuths = new HashSet<>();

        // Build user's authorities
        setAuths.add(new SimpleGrantedAuthority(userRole.getValue()));

        return new ArrayList<>(setAuths);
    }
}
