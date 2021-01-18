package com.maids.sales.controllers.v1;

import com.maids.sales.api.v1.contract.sales.CreateOrderDTO;
import com.maids.sales.api.v1.contract.sales.OrderResultDTO;
import com.maids.sales.api.v1.contract.sales.UpdateOrderDTO;
import com.maids.sales.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SalesController {
    private SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/sales")
    public List<OrderResultDTO> findAll() {

        return this.salesService.findAll();
    }

    @PostMapping("/sales")
    public OrderResultDTO add(@RequestBody CreateOrderDTO order) {

        return this.salesService.saveOrder(order);

    }

    @PutMapping("/sales")
    public OrderResultDTO updateOrder(@RequestBody UpdateOrderDTO order) {

        return this.salesService.updateOrder(order);


    }
}
