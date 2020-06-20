package crud.service;

import crud.config.ServerConfig;
import crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Service
public class UserServiceREST implements UserService, UserDetailsService {

    private RestTemplate restTemplate;
    private ServerConfig serverConfig;

    @Autowired
    public UserServiceREST(RestTemplateBuilder restTemplateBuilder,
                           ServerConfig serverConfig) {
        this.restTemplate = restTemplateBuilder.basicAuthentication("admin", "admin").build();
        this.serverConfig = serverConfig;
    }



    @Override
    public Collection<User> getAllUsers() {
        ResponseEntity<Collection<User>> responseEntity = restTemplate.exchange(
                serverConfig.getServerAddress() + "/admin/all-users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Collection<User>>() {}
        );
        return responseEntity.getBody();
    }

    @Override
    public String addUser(User user) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                serverConfig.getServerAddress() + "/admin/add-user",
                HttpMethod.POST,
                new HttpEntity<>(user, new HttpHeaders()),
                String.class
        );
        return responseEntity.getBody();
    }

    @Override
    public String deleteUser(User user) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                serverConfig.getServerAddress() + "/admin/delete-user",
                HttpMethod.POST,
                new HttpEntity<>(user, new HttpHeaders()),
                String.class
        );
        return responseEntity.getBody();
    }

    @Override
    public String editUser(User user) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                serverConfig.getServerAddress() + "/admin/edit-user",
                HttpMethod.POST,
                new HttpEntity<>(user, new HttpHeaders()),
                String.class
        );
        return responseEntity.getBody();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ResponseEntity<User> responseEntity = restTemplate.exchange(
                serverConfig.getServerAddress() + "/admin/user-by-username",
                HttpMethod.POST,
                new HttpEntity<>(s, new HttpHeaders()),
                User.class
        );
        return responseEntity.getBody();
    }
}
