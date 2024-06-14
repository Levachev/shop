package com.example.shop.controller;

import com.example.shop.entity.Manufacturer;
import com.example.shop.repo.ManufacturerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ManufacturerRepo manufacturerRepo;


    @GetMapping("/menu")
    public ModelAndView getMenu(){
        return new ModelAndView("menu.html");
    }

    @GetMapping("/page/{entity}/{operation}")
    public ModelAndView crud(@PathVariable String entity, @PathVariable String operation){
        return new ModelAndView(entity+"_"+operation+".html");
    }

    @PostMapping("/manufacturer/add")
    public void addManufacturer(@RequestBody Manufacturer manufacturer){
        manufacturerRepo.save(manufacturer);
    }

    @PostMapping("/manufacturer/update")
    public void updateManufacturer(@RequestBody Manufacturer manufacturer){
        manufacturerRepo.save(manufacturer);
    }

    @GetMapping("/manufacturer/delete")
    public void deleteManufacturer(@RequestParam Long id){
        manufacturerRepo.deleteById(id);
    }

    @GetMapping("/manufacturer/get")
    public void getManufacturer(@RequestParam Long id){
        manufacturerRepo.findById(id);
    }

}
