package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Restore;
import com.algaworks.algafood.domain.repository.RestoreRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RestoreService {

    private final RestoreRepository restoreRepository;

    public RestoreService(RestoreRepository restoreRepository) {
        this.restoreRepository = restoreRepository;
    }

    public List<Restore> list() {
        return restoreRepository.list();
    }
}
