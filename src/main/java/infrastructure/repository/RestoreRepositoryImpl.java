package infrastructure.repository;

import com.algaworks.algafood.domain.model.Restore;
import com.algaworks.algafood.domain.repository.CustomizeRestoreRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestoreRepositoryImpl implements CustomizeRestoreRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Restore> find(String name,
                              BigDecimal startShippingFee, BigDecimal lastShippingFee) {
        var jpql = "FROM Restore WHERE name LIKE :name and shippingFee " +
                "between :startShippingFee and :lastShippingFee";
        return entityManager.createQuery(jpql, Restore.class)
                .setParameter("name", "%"+name+"%")
                .setParameter("startShippingFee", startShippingFee)
                .setParameter("lastShippingFee", lastShippingFee)
                .getResultList();
    }
}
