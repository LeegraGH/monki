package com.example.monki.services;

import com.example.monki.models.Product;
import com.example.monki.models.Warehouse;
import com.example.monki.repositories.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public Integer getCountOfProduct(Product product){
        Warehouse warehouse = warehouseRepository.getByProduct(product);
        return warehouse.getCount();
    }

    public void reduceProduct(Product product){
        Warehouse warehouse = warehouseRepository.getByProduct(product);
        if (warehouse.getCount()>0){
            warehouse.setCount(warehouse.getCount()-1);
            warehouseRepository.save(warehouse);
        }
    }

    public void increaseProduct(Product product){
        Warehouse warehouse = warehouseRepository.getByProduct(product);
        warehouse.setCount(warehouse.getCount()+1);
        warehouseRepository.save(warehouse);
    }
}
