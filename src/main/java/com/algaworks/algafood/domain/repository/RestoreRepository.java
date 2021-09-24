package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.algaworks.algafood.domain.model.Restore;
import org.springframework.stereotype.Repository;

@Repository
public interface RestoreRepository extends JpaRepository<Restore, Long> {
}
