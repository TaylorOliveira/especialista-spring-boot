package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restore;
import java.util.List;

public interface TestRepositoryQueries {

    List<Restore> find(String name);
}
