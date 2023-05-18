package rw.auca.cnms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.auca.cnms.model.Role;
import rw.auca.cnms.service.IRoleService;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping("/new_role")
    public String createNewRole(@ModelAttribute Role role) {
        roleService.createRole(role);
        return "redirect:/Role";
    }

    @PutMapping("/update_role/{id}")
    public String updateExistingRole(@PathVariable("id") Long id, @ModelAttribute Role role) {
        roleService.updateRole(role, id);
        return "redirect:/Role";
    }

    @DeleteMapping("/delete_role/{id}")
    public String deleteExistingRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
        return "redirect:/Role";
    }

    @GetMapping("/role/{id}")
    public String viewOneRole(@PathVariable("id") Long id) {
        roleService.getRole(id);
        return "ViewRole";
    }

    @GetMapping("/roles")
    public String viewRoles(Model model) {
        model.addAttribute(roleService.getRoles());
        return "Role";
    }
}
