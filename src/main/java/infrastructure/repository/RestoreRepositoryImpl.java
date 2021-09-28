package infrastructure.repository;

import com.algaworks.algafood.domain.repository.CustomizeRestoreRepository;
import com.algaworks.algafood.domain.model.Restore;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import org.springframework.util.StringUtils;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.*;

@Repository
public class RestoreRepositoryImpl implements CustomizeRestoreRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Restore> find(String name,
                              BigDecimal startShippingFee, BigDecimal lastShippingFee) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restore> criteria = builder.createQuery(Restore.class);
        Root<Restore> rootRestore = criteria.from(Restore.class);

        List<Predicate> predicates = new ArrayList<Predicate>();
        if(StringUtils.hasText(name)) {
            predicates.add(builder.like(rootRestore.get("name"), name));
        }
        if(Objects.nonNull(startShippingFee)) {
            predicates.add(builder
                    .lessThanOrEqualTo(rootRestore.get("shippingFee"), startShippingFee));
        }
        if(Objects.nonNull(lastShippingFee)) {
            predicates.add(builder
                    .lessThanOrEqualTo(rootRestore.get("shippingFee"), lastShippingFee));
        }

        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restore> restoreTypedQuery = entityManager.createQuery(criteria);
        return restoreTypedQuery.getResultList();
    }
}
