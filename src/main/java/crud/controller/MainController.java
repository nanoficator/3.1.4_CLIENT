package crud.controller;

import crud.model.User;
import crud.service.AuthorityServiceREST;
import crud.service.UserServiceREST;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
public class MainController {

    private UserServiceREST userService;
    private AuthorityServiceREST authorityService;

    public MainController(UserServiceREST userService,
                          AuthorityServiceREST authorityService) {
        this.userService = userService;
        this.authorityService = authorityService;
    }

    @GetMapping
    public String mainPage(Model model) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean isAdmin = authUser.getAuthorities().contains(authorityService.getAuthorityByName("ROLE_ADMIN"));
        boolean isUser = authUser.getAuthorities().contains(authorityService.getAuthorityByName("ROLE_USER"));
        model.addAttribute("authUser", authUser);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isUser", isUser);
        return "mainPage";
    }

}
