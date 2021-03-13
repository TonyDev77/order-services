package com.tony.services.api.controller;

import com.tony.services.api.repsentationModel.OrderOfWorkDTO;
import com.tony.services.domain.model.OrderOfWork;
import com.tony.services.domain.repository.OrderOfWorkRepository;
import com.tony.services.domain.services.OrderOfWorkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order-work")
public class OrderOfWorkController {

    @Autowired
    private OrderOfWorkService orderOfWorkService;
    @Autowired
    private OrderOfWorkRepository orderOfWorkRepository;
    @Autowired
    private ModelMapper modelMapper; // configurado via annotation em ModelMapperConfig.java

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderOfWorkDTO saveOrderOfWork(@Valid @RequestBody OrderOfWork orderOfWork) {

        return mapToModel(orderOfWorkService.saveWork(orderOfWork));
    }

    @GetMapping
    public List<OrderOfWorkDTO> findAllOrderOfWork() {

        return mapToCollectionModel(orderOfWorkRepository.findAll());
    }

    @GetMapping("/{orderOfWorkId}")
    public ResponseEntity<OrderOfWorkDTO> findByIdOrderOfWork(@PathVariable Long orderOfWorkId) {

        Optional<OrderOfWork> orderOfWork =  orderOfWorkRepository.findById(orderOfWorkId);

        if (orderOfWork.isPresent()) {
            OrderOfWorkDTO orderOfWorkDTO = mapToModel(orderOfWork.get());
            return ResponseEntity.ok(orderOfWorkDTO);
        }
        return ResponseEntity.notFound().build();
    }

    // mapeia uma entity p/ DTO
    private OrderOfWorkDTO mapToModel(OrderOfWork orderOfWork) {
        return modelMapper.map(orderOfWork, OrderOfWorkDTO.class);
    }

    // mapeia uma coleção de entity p/ DTO
    private List<OrderOfWorkDTO> mapToCollectionModel(List<OrderOfWork> orderOfWorks) {
        return orderOfWorks.stream()
                .map(x -> mapToModel(x))
                .collect(Collectors.toList());
    }

}
