package mike.businesscards.service;

import mike.businesscards.dao.UserDaoImpl;
import mike.businesscards.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mike on 10/05/2015.
 */
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserDaoImpl userDaoImpl;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDaoImpl.getUserByEmail(email);
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getMail(), user.getPassword(), roles);
    }
}
