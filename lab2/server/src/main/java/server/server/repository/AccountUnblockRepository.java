package server.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import server.server.entity.AccountUnblock;

public interface AccountUnblockRepository extends JpaRepository<AccountUnblock, Long> {    
}
