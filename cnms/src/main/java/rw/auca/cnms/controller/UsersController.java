package rw.auca.cnms.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.auca.cnms.model.Users;
import rw.auca.cnms.service.IUserService;

import java.util.Arrays;
import java.util.List;

//@RestController
//@RequestMapping("/api")
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
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "Login";
    }

    @PostMapping("/login")
    public String loginExistingUser(@RequestParam String email, @RequestParam String password, Model model) {
        Users users = userService.loginUser(email, password);
        if (users != null) {
            if (users.getRoles().equals(Arrays.asList("ADMIN"))) {
                return "redirect:/dashboard";
            } else {
                return "redirect:/home";
            }
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "Login";
        }
    }

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String adminHome() {
        return "Dashboard";
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String userHome() {
        return "Home";
    }

    @PutMapping("/user/update_user/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable("id") Long id, @RequestBody Users users) {
        return new ResponseEntity<>(userService.updateUser(users, id), HttpStatus.OK);
    }

    @DeleteMapping("/admin/deactivate_user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deactivated successfully!", HttpStatus.OK);
    }

    @GetMapping("/admin/view_user/{id}")
    public ResponseEntity<Users> findUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.FOUND);
    }

    @GetMapping("/admin/view_all_users")
    public ResponseEntity<List<Users>> findAllUsers() {
        return new ResponseEntity<List<Users>>(userService.getUsers(), HttpStatus.FOUND);
    }

}
