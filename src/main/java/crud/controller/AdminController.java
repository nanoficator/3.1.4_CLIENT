package crud.controller;

import crud.model.Authority;
import crud.model.User;
import crud.service.AuthorityService;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {

    private UserService userService;
    private AuthorityService authorityService;
    private RestTemplate restTemplate;

    @Autowired
    public AdminController(UserService userService,
                           AuthorityService authorityService,
                           RestTemplateBuilder restTemplateBuilder) {
        this.userService = userService;
        this.authorityService = authorityService;
        this.restTemplate = restTemplateBuilder.basicAuthentication("admin", "admin").build();
    }

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        ResponseEntity<String> result = restTemplate.exchange(
                "http://localhost:8081/admin/add-user",
                HttpMethod.POST,
                new HttpEntity<User>(user, new HttpHeaders()),
                String.class
        );
        return result;
    }

    @PostMapping("/edit-user")
    public ResponseEntity<String> editUser(@RequestBody User user) {
        ResponseEntity<String> result = restTemplate.exchange(
                "http://localhost:8081/admin/edit-user",
                HttpMethod.POST,
                new HttpEntity<User>(user, new HttpHeaders()),
                String.class
        );
        return result;
    }

    @PostMapping(value = "/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        ResponseEntity<String> result = restTemplate.exchange(
                "http://localhost:8081/admin/delete-user",
                HttpMethod.POST,
                new HttpEntity<User>(user, new HttpHeaders()),
                String.class
        );
        return result;
    }

    @GetMapping("/all-users")
    public ResponseEntity<Collection<User>> getAllUsers() {
        ResponseEntity<Collection<User>> allUsers = restTemplate.exchange(
                "http://localhost:8081/admin/all-users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Collection<User>>() {}
        );
        return allUsers;
    }

    @GetMapping("/all-authorities")
    public ResponseEntity<Collection<Authority>> getAllAuthorities() {
        ResponseEntity<Collection<Authority>> allAuthorities = restTemplate.exchange(
                "http://localhost:8081/admin/all-authorities",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Collection<Authority>>() {}
        );
        return allAuthorities;
    }

    @GetMapping("/authority-by-name")
    public ResponseEntity<Authority> getAuthorityById(@RequestBody String name) {
        ResponseEntity<Authority> authority = restTemplate.exchange(
                "http://localhost:8081/admin/authority-by-name",
                HttpMethod.GET,
                null,
                Authority.class
        );
        return authority;
    }
}
