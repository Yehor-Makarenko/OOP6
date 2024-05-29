package server.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import server.server.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByCreditCardId(Long cardId);

  @Modifying
  @Transactional
  @Query("UPDATE Account a SET a.balance = :balance WHERE a.id = :accountId")
  int updateBalance(Long accountId, double balance);

  @Modifying
  @Transactional
  @Query("UPDATE Account a SET a.isBlocked = true WHERE a.id = :accountId")
  int blockAccount(Long accountId);

  @Modifying
  @Transactional
  @Query("UPDATE Account a SET a.isBlocked = false WHERE a.id = :accountId")
  int unblockAccount(Long accountId);
}
