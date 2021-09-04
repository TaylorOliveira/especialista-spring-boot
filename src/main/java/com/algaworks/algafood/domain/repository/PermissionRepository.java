package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Permission;
import java.util.List;

public interface PermissionRepository {

    List<Permission> list();
    Permission search(Long id);
    Permission save(Permission city);
    void delete(Permission city);
}
