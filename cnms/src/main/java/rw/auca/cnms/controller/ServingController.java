package rw.auca.cnms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.auca.cnms.model.Serving;
import rw.auca.cnms.service.IServingService;

import java.util.List;

@RestController
@RequestMapping("/serving")
public class ServingController {

    @Autowired
    private IServingService servingService;

    @PostMapping("/admin/new_serving")
    public ResponseEntity<Serving> createNewServing(@RequestBody Serving serving) {
        return new ResponseEntity<>(servingService.createServing(serving), HttpStatus.CREATED);
    }

    @PutMapping("/admin/update_serving/{id}")
    public ResponseEntity<Serving> updateExistingServing(@PathVariable("id") Long id, @RequestBody Serving serving) {
        return new ResponseEntity<>(servingService.updateServing(serving, id), HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete_serving/{id}")
    public ResponseEntity<String> deleteExistingServing(@PathVariable("id") Long id) {
        servingService.deleteServing(id);
        return new ResponseEntity<>("Serving successfully deleted!", HttpStatus.OK);
    }

    @RequestMapping(value = { "/user/view_serving/{id}", "/admin/view_serving/{id}" }, method = RequestMethod.GET)
    public ResponseEntity<Serving> viewOneServing(@PathVariable("id") Long id) {
        return new ResponseEntity<>(servingService.findOneServing(id), HttpStatus.FOUND);
    }

    @RequestMapping(value = { "/user/view_servings", "/admin/view_servings" }, method = RequestMethod.GET)
    public ResponseEntity<List<Serving>> viewServings() {
        return new ResponseEntity<List<Serving>>(servingService.findAllServing(), HttpStatus.FOUND);
    }


}
