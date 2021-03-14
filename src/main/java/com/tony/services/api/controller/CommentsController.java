package com.tony.services.api.controller;

import com.tony.services.api.repsentationModel.CommentsDTO;
import com.tony.services.api.repsentationModel.InputCommentsDTO;
import com.tony.services.domain.model.Comments;
import com.tony.services.domain.services.OrderOfWorkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order-work/{orderWorkId}/comments")
public class CommentsController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderOfWorkService orderOfWorkService;

    @RequestMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentsDTO saveComments(@PathVariable Long orderWorkId, @Valid @RequestBody InputCommentsDTO inputCommentsDTO) {

        Comments comments = orderOfWorkService.addComments(orderWorkId, inputCommentsDTO.getDescription());
        
        return mapToModel(comments);
    }

    // Converte Entity p/ DTO
    private CommentsDTO mapToModel(Comments comments) {

        return modelMapper.map(comments, CommentsDTO.class);
    }
}
