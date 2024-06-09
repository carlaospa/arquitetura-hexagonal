package com.carlaospa.arquiteturahexagonal.infrastructure.adapter.out.persistence;

import com.carlaospa.arquiteturahexagonal.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataJpaUserRepository extends JpaRepository<UserJpaEntity, Long> {
}
