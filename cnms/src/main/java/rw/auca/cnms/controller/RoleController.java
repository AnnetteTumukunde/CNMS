package rw.auca.cnms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.auca.cnms.model.Role;
import rw.auca.cnms.service.IRoleService;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping("/new_role")
    public String createNewRole(@ModelAttribute Role role) {
        try {
            roleService.createRole(role);
            return "redirect:/Role";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/update_role/{id}")
    public String updateExistingRole(@PathVariable("id") Long id, @ModelAttribute Role role) {
        try {
            roleService.updateRole(role, id);
            return "redirect:/Role";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete_role/{id}")
    public String deleteExistingRole(@PathVariable("id") Long id) {
        try {
            roleService.deleteRole(id);
            return "redirect:/Role";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/role/{id}")
    public String viewOneRole(@PathVariable("id") Long id) {
        try {
            roleService.getRole(id);
            return "ViewRole";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/roles")
    public String viewRoles(Model model) {
        try {
            model.addAttribute(roleService.getRoles());
            return "Role";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
