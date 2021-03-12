package com.tony.services.api.controller;

import com.tony.services.domain.model.OrderOfWork;
import com.tony.services.domain.services.OrderOfWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order-work")
public class OrderOfWorkController {

    @Autowired
    private OrderOfWorkService orderOfWorkService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderOfWork saveOrderOfWork(@Valid @RequestBody OrderOfWork orderOfWork) {

        return orderOfWorkService.saveWork(orderOfWork);
    }
}
