package rw.auca.cnms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.auca.cnms.model.Child;
import rw.auca.cnms.repository.IChildRepository;
import rw.auca.cnms.service.IChildService;

import java.util.List;

@RestController
@RequestMapping("/child")
public class ChildController {

    @Autowired
    private IChildService childService;

    @PostMapping("/user/new_child")
    public ResponseEntity<Child> registerNewChild(@RequestBody Child child) {
        return new ResponseEntity<>(childService.registerChild(child), HttpStatus.CREATED);
    }

    @PutMapping("/user/update_child/{id}")
    public ResponseEntity<Child> updateExistingChild(@PathVariable("id") Long id, @RequestBody Child child) {
        return new ResponseEntity<>(childService.updateChild(child, id), HttpStatus.OK);
    }

    @DeleteMapping("/user/delete_child/{id}")
    public ResponseEntity<String> deleteExistingChild(@PathVariable("id") Long id) {
        childService.deleteChild(id);
        return new ResponseEntity<>("Child successfully deleted!", HttpStatus.OK);
    }

    @RequestMapping(value = { "/user/view_child/{id}", "/admin/view_child/{id}" }, method = RequestMethod.GET)
    public ResponseEntity<Child> viewOneChild(@PathVariable("id") Long id) {
        return new ResponseEntity<>(childService.findOneChild(id), HttpStatus.FOUND);
    }

    @RequestMapping(value = { "/user/view_children", "/admin/view_children" })
    public ResponseEntity<List<Child>> viewChildren() {
        return new ResponseEntity<>(childService.findAllChildren(), HttpStatus.FOUND);
    }
}
