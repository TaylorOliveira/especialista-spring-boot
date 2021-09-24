package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.algaworks.algafood.domain.model.Restore;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Restore, Long>, TestRepositoryQueries {

}
