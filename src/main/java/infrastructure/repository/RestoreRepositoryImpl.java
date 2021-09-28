package infrastructure.repository;

import com.algaworks.algafood.domain.repository.CustomizeRestoreRepository;
import com.algaworks.algafood.domain.model.Restore;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestoreRepositoryImpl implements CustomizeRestoreRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Restore> find(String name,
                              BigDecimal startShippingFee, BigDecimal lastShippingFee) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restore> restoreCriteriaQuery = criteriaBuilder.createQuery(Restore.class);
        restoreCriteriaQuery.from(Restore.class);
        TypedQuery<Restore> restoreTypedQuery = entityManager.createQuery(restoreCriteriaQuery);
        return restoreTypedQuery.getResultList();
    }
}
