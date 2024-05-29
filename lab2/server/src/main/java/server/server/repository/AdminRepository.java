package server.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import server.server.entity.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
        
    Optional<Admin> findByEmail(String email);
        
    boolean existsByEmail(String email);
}

