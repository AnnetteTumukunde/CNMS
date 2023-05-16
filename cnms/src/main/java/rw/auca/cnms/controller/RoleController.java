package rw.auca.cnms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Role> createNewRole(@RequestBody Role role) {
        return new ResponseEntity<>(roleService.createRole(role), HttpStatus.CREATED);
    }

    @PutMapping("/update_role/{id}")
    public ResponseEntity<Role> updateExistingRole(@PathVariable("id") Long id, @RequestBody Role role) {
        return new ResponseEntity<>(roleService.updateRole(role, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete_role/{id}")
    public ResponseEntity<String> deleteExistingRole(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
        return new ResponseEntity<>("Role deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<Role> viewOneRole(@PathVariable("id") Long id) {
        return new ResponseEntity<>(roleService.getRole(id), HttpStatus.FOUND);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> viewRoles() {
        return new ResponseEntity<List<Role>>(roleService.getRoles(), HttpStatus.FOUND);
    }
}
