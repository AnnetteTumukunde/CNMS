package rw.auca.cnms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.auca.cnms.model.Serving;
import rw.auca.cnms.model.Users;
import rw.auca.cnms.service.*;

import java.security.Principal;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
    public String showSignup(Model model) {
        try {
            model.addAttribute("users", new Users());
            return "Signup";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/signup")
    public String signupUser(@ModelAttribute("users") Users users) {
        try {
            userService.createUser(users);
            return "redirect:/dashboard";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/login")
    public String showLogin() {
        try {
            return "Login";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String adminHome(Model model, Principal principal) {
        try {
            String username = principal.getName();
            model.addAttribute("username", username);
            return "Dashboard";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String userHome() {
        try {
            return "index";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = {"/user/update_user/{id}"}, method = RequestMethod.PUT)
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") Users users) {
        try {
            userService.updateUser(users, id);
            return "EditUser";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/admin/deactivate_user/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        try {
            userService.deleteUser(id);
            return "/users";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/admin/view_user/{id}")
    public String findUser(@PathVariable("id") Long id) {
        try {
            userService.getUser(id);
            return "ViewUser";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/users")
    public String findAllUsers(Model model, @RequestParam(defaultValue = "0") int page) {
        try {
            int pageSize = 5;
            Pageable pageable = PageRequest.of(page, pageSize);

            Page<Users> usersPage = userService.getPaginatedUsers(pageable);
            List<Users> users = usersPage.getContent();
            model.addAttribute("usersList", users);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", usersPage.getTotalPages());
            return "Users";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/search")
    public String SearchUser(@RequestParam("search") String name, Model model) {
        try {
            List<Users> users=userService.searchByName(name);
            model.addAttribute("users", users);
            return "/users";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

}
