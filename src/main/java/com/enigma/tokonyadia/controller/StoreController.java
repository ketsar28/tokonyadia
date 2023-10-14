package com.enigma.tokonyadia.controller;

import com.enigma.tokonyadia.entity.Store;
import com.enigma.tokonyadia.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    public Store createNewStore(@RequestBody Store store){
        return storeService.create(store);
    }

    @GetMapping(value = "/{id}")
    public Store getStoreById(@PathVariable String id){
        return storeService.getById(id);
    }

    @GetMapping
    public List<Store> getAllStore(){
        return storeService.getAll();
    }

    @PutMapping
    @PreAuthorize("hasRole('SELLER') and @userSecurity.checkSeller(authentication, #request.getStoreId())")
    public Store updateStore(@RequestBody Store store){
        return storeService.update(store);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteStore(@PathVariable String id){
        storeService.deleteById(id);
    }
}
