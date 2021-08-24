package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restore;
import java.util.List;

public interface RestoreRepository {

    List<Restore> list();

    Restore search(Long id);

    Restore save(Restore kitchen);

    void remove(Restore kitchen);
}
