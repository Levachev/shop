package com.example.shop.controller;

import com.example.shop.DTO.InputManufacturerProductDTO;
import com.example.shop.DTO.ManufacturerProductDTO;
import com.example.shop.DTO.UpdateManufacturerProductDTO;
import com.example.shop.mapper.ManufacturerProductMapper;
import com.example.shop.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {
    @Autowired
    @Qualifier("firstImpl")
    private ManufacturerService manufacturerService;

    @GetMapping("/show")
    public List<ManufacturerProductDTO> show(@RequestParam Long id, @RequestParam(defaultValue = "1") int page){
        return manufacturerService.show(id, page).stream()
                .map(ManufacturerProductMapper::toManufacturerProductDTO)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id){
        manufacturerService.delete(id);
    }

    @PutMapping("/add")
    public void addNewProduct(@RequestBody InputManufacturerProductDTO productDTO){
        manufacturerService.addNewProduct(productDTO);
    }

    @PostMapping("/update")
    public void updateProduct(@RequestBody UpdateManufacturerProductDTO productDTO,
                              @RequestParam Long id){
        manufacturerService.updateProduct(id, productDTO);
    }

    @GetMapping("/get")
    public ManufacturerProductDTO getById(@RequestParam Long id){
        var tmp =  manufacturerService.getById(id);

        return tmp.map(ManufacturerProductMapper::toManufacturerProductDTO).orElse(null);
    }

}
