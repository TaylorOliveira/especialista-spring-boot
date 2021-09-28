package infrastructure.repository;

import com.algaworks.algafood.domain.repository.CustomizeRestoreRepository;
import com.algaworks.algafood.domain.model.Restore;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestoreRepositoryImpl implements CustomizeRestoreRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Restore> find(String name,
                              BigDecimal startShippingFee, BigDecimal lastShippingFee) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restore> criteria = builder.createQuery(Restore.class);
        Root<Restore> rootRestore = criteria.from(Restore.class);

        Predicate predicateName = builder.like(rootRestore.get("name"), name);
        Predicate predicateStartSf =
                builder.greaterThanOrEqualTo(rootRestore.get("shippingFee"), startShippingFee);
        Predicate predicateLastSf =
                builder.lessThanOrEqualTo(rootRestore.get("shippingFee"), lastShippingFee);

        criteria.where(predicateName, predicateStartSf, predicateLastSf);

        TypedQuery<Restore> restoreTypedQuery = entityManager.createQuery(criteria);
        return restoreTypedQuery.getResultList();
    }
}
