package rw.auca.cnms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.auca.cnms.model.Child;
import rw.auca.cnms.model.ChildGrowth;
import rw.auca.cnms.service.IChildGrowthService;

import java.util.List;

@RestController
@RequestMapping("/child_growth")
public class ChildGrowthController {

    @Autowired
    private IChildGrowthService childGrowthService;

    @PostMapping("/user/new_child_growth")
    public ResponseEntity<ChildGrowth> saveNewChildGrowth(@RequestBody ChildGrowth childGrowth) {
        return new ResponseEntity<>(childGrowthService.addChildGrowth(childGrowth), HttpStatus.CREATED);
    }

    @PutMapping("/user/update_child_growth/{id}")
    public ResponseEntity<ChildGrowth> updateExistingChildGrowth(@PathVariable("id") Long id, @RequestBody ChildGrowth childGrowth) {
        return new ResponseEntity<>(childGrowthService.updateChildGrowth(childGrowth, id), HttpStatus.OK);
    }

    @DeleteMapping("/user/delete_child_growth/{id}")
    public ResponseEntity<String> deleteExistingChildGrowth(@PathVariable("id") Long id) {
        childGrowthService.deleteChildGrowth(id);
        return new ResponseEntity<>("Child growth deleted successfully!", HttpStatus.OK);
    }

    @RequestMapping(value = { "/user/view_child_growth/{id}", "/admin/view_child_growth/{id}" }, method = RequestMethod.GET)
    public ResponseEntity<ChildGrowth> viewOneChildGrowth(@PathVariable("id") Long id) {
        return new ResponseEntity<>(childGrowthService.findOneChildGrowth(id), HttpStatus.FOUND);
    }

    @RequestMapping(value = { "/user/view_child_growth", "/admin/view_child_growth" }, method = RequestMethod.GET)
    public ResponseEntity<List<ChildGrowth>> viewChildGrowth() {
        return new ResponseEntity<>(childGrowthService.findAllChildGrowth(), HttpStatus.FOUND);
    }
}
