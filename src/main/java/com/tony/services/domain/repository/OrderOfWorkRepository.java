package com.tony.services.domain.repository;

import com.tony.services.domain.model.Client;
import com.tony.services.domain.model.OrderOfWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderOfWorkRepository extends JpaRepository<OrderOfWork, Long> {

}
