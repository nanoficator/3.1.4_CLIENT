package crud.controller;

import crud.model.Authority;
import crud.model.User;
import crud.service.AuthorityService;
import crud.service.AuthorityServiceREST;
import crud.service.UserService;
import crud.service.UserServiceREST;
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

    @Autowired
    public AdminController(UserService userService,
                           AuthorityService authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        String result = userService.addUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/edit-user")
    public ResponseEntity<String> editUser(@RequestBody User user) {
        String result = userService.editUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/delete-user")
    public ResponseEntity<String> deleteUser(@RequestBody User user) {
        String result = userService.deleteUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all-users")
    public ResponseEntity<Collection<User>> getAllUsers() {
        Collection<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/all-authorities")
    public ResponseEntity<Collection<Authority>> getAllAuthorities() {
        Collection<Authority> allAuthorities = authorityService.getAllAuthorities();
        return new ResponseEntity<>(allAuthorities, HttpStatus.OK);
    }

    @GetMapping("/authority-by-name")
    public ResponseEntity<Authority> getAuthorityById(@RequestBody String name) {
        Authority authority = authorityService.getAuthorityByName(name);
        return new ResponseEntity<>(authority, HttpStatus.OK);
    }
}
