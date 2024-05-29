package server.server.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import server.server.entity.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {  
  List<CreditCard> findByClientId(Long clientId);
    
  Optional<CreditCard> findByCardNumber(String cardNumber);
}
