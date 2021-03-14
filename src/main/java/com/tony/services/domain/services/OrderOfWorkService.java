package com.tony.services.domain.services;

import com.tony.services.domain.exception.DomainException;
import com.tony.services.domain.exception.EntityNotFoundException;
import com.tony.services.domain.model.Client;
import com.tony.services.domain.model.Comments;
import com.tony.services.domain.model.OrderOfWork;
import com.tony.services.domain.model.OrderOfWorkStatus;
import com.tony.services.domain.repository.ClientRepository;
import com.tony.services.domain.repository.CommentsRepository;
import com.tony.services.domain.repository.OrderOfWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class OrderOfWorkService {

    @Autowired
    private OrderOfWorkRepository orderOfWorkRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CommentsRepository commentsRepository;

    public OrderOfWork saveWork(OrderOfWork orderOfWork) {

        // Retorna um Optional para "client"
        Client client = clientRepository.findById(orderOfWork.getClient().getId())
                .orElseThrow(() -> new DomainException("Cliente pode não existir no Banco de Dados"));

        orderOfWork.setClient(client);
        orderOfWork.setStatus(OrderOfWorkStatus.Opened);
        orderOfWork.setOpeningDate(OffsetDateTime.now());

        return orderOfWorkRepository.save(orderOfWork);
    }

    // Add comentários nos serviços
    public Comments addComments(Long orderOfWorkId, String description) {

        OrderOfWork orderOfWork = orderOfWorkRepository.findById(orderOfWorkId)
                .orElseThrow(() -> new EntityNotFoundException("Ordem de serviço não encontrada"));

        Comments comments = new Comments();
        comments.setPostDate(OffsetDateTime.now());
        comments.setDescription(description);
        comments.setOrderOfWork(orderOfWork);

        return commentsRepository.save(comments);
    }
}
