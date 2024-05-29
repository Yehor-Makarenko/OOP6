package server.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import server.server.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
  List<Payment> findByAccountId(Long accountId);
}

