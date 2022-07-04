package kata.crud.kata313.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import kata.crud.kata313.model.Role;
import kata.crud.kata313.model.User;
import kata.crud.kata313.repositry.RoleRepository;
import kata.crud.kata313.repositry.UserRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void save(User user) {

        userRepository.save(user);
    }


    public User show(Long id) {
        return userRepository.findById(id).orElse(null);

    }

    public void update(User updatedUser) {
        userRepository.save(updatedUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    public List<User> index() {
        return userRepository.findAll();
    }


    public User findByUserEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findByUserEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("user %s not found", email));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    public List<Role> listRoles() {
        return roleRepository.findAll();
    }
}

