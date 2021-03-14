package com.tony.services.api.controller;

import com.tony.services.api.repsentationModel.CommentsDTO;
import com.tony.services.api.repsentationModel.InputCommentsDTO;
import com.tony.services.domain.exception.EntityNotFoundException;
import com.tony.services.domain.model.Comments;
import com.tony.services.domain.model.OrderOfWork;
import com.tony.services.domain.repository.OrderOfWorkRepository;
import com.tony.services.domain.services.OrderOfWorkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order-work/{orderWorkId}/comments")
public class CommentsController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderOfWorkService orderOfWorkService;
    @Autowired
    private OrderOfWorkRepository orderOfWorkRepository;

    @RequestMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentsDTO saveComments(@PathVariable Long orderWorkId, @Valid @RequestBody InputCommentsDTO inputCommentsDTO) {

        Comments comments = orderOfWorkService.addComments(orderWorkId, inputCommentsDTO.getDescription());
        
        return mapToModel(comments);
    }

    @GetMapping
    public List<CommentsDTO> findAllComments(@PathVariable Long orderWorkId) {

        OrderOfWork orderOfWork = orderOfWorkRepository.findById(orderWorkId)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada!"));

        return mapToCollectionModel(orderOfWork.getComments());
    }

    // Converte Entity p/ DTO
    private CommentsDTO mapToModel(Comments comments) {

        return modelMapper.map(comments, CommentsDTO.class);
    }

    // Converte Entities p/ DTOs
    private List<CommentsDTO> mapToCollectionModel(List<Comments> comments) {

        return comments.stream()
                .map(x -> mapToModel(x))
                .collect(Collectors.toList());
    }
}
