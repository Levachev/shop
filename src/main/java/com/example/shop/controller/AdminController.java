package com.example.shop.controller;

import com.example.shop.DTO.ManufacturerProductDTO;
import com.example.shop.entity.Manufacturer;
import com.example.shop.mapper.ManufacturerProductMapper;
import com.example.shop.repo.ManufacturerProductRepo;
import com.example.shop.repo.ManufacturerRepo;
import com.example.shop.service.AdminService;
import com.example.shop.service.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    @Qualifier("firstImpl")
    private AdminService adminService;

    @GetMapping("/delete/manufacturer_product")
    public void deleteManufacturerProduct(@RequestParam Long id){
        adminService.deleteManufacturerProduct(id);
    }

    @GetMapping("/show/manufacturer_product")
    public List<ManufacturerProductDTO> show(
            @RequestParam(required = false, defaultValue = "1") int page){
        return adminService.show(page).stream()
                .map(ManufacturerProductMapper::toManufacturerProductDTO)
                .collect(Collectors.toList());
    }
}
