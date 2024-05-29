package server.server.repository;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import server.server.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
  Optional<Client> findByEmail(String email);

  boolean existsByEmail(String email);
}
