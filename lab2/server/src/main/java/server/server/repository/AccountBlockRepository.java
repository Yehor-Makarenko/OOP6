package server.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import server.server.entity.AccountBlock;

public interface AccountBlockRepository extends JpaRepository<AccountBlock, Long> {

}
