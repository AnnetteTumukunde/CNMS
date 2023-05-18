package rw.auca.cnms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.auca.cnms.model.Users;
import rw.auca.cnms.service.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = {"/signup"}, method = RequestMethod.GET)
    public String showSignup(Model model) {
        model.addAttribute("users", new Users());
        return "Signup";
    }

    @PostMapping("/signup")
    public String signupUser(@ModelAttribute("users") Users users) {
        userService.createUser(users);
        return "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "Login";
    }

//    @PostMapping("/login")
//    public String loginExistingUser(@RequestParam String email, @RequestParam String password, Model model) {
//        Users users = userService.loginUser(email, password);
//        if (users != null) {
//            if (users.getRole().getName().equals("ADMIN")) {
//                return "redirect:/dashboard";
//            } else {
//                return "redirect:/home";
//            }
//        } else {
//            model.addAttribute("error", "Invalid username or password");
//            return "Login";
//        }
//    }

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String adminHome() {
        return "Dashboard";
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String userHome() {
        return "index";
    }

    @PutMapping("/user/update_user/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") Users users) {
        userService.updateUser(users, id);
        return "EditUser";
    }

    @DeleteMapping("/admin/deactivate_user/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "/users";
    }

    @GetMapping("/admin/view_user/{id}")
    public String findUser(@PathVariable("id") Long id) {
        userService.getUser(id);
        return "ViewUser";
    }

    @GetMapping("/users")
    public String findAllUsers(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Users> usersPage = userService.getPaginatedUsers(pageable);
        List<Users> users = usersPage.getContent();
        model.addAttribute("usersList", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", usersPage.getTotalPages());
        return "Users";
    }

    @GetMapping("/search")
    public String SearchUser(@RequestParam("search") String name, Model model) {
        List<Users> users=userService.searchByName(name);
        model.addAttribute("users", users);
        return "/users";
    }

}
