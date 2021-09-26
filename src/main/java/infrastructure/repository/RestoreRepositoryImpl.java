package infrastructure.repository;

import com.algaworks.algafood.domain.repository.CustomizeRestoreRepository;
import com.algaworks.algafood.domain.model.Restore;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Repository
public class RestoreRepositoryImpl implements CustomizeRestoreRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Restore> find(String name,
                              BigDecimal startShippingFee, BigDecimal lastShippingFee) {
        var jpql = new StringBuilder();
        var parameters = new HashMap<String, Object>();
        jpql.append("from Restore where 0 = 0 ");
        if(StringUtils.hasLength(name)) {
            jpql.append("and name like :name ");
            parameters.put("name", "%"+name+"%");
        }
        if(Objects.nonNull(startShippingFee)) {
            jpql.append("and shippingFee >= :startShippingFee ");
            parameters.put("startShippingFee", startShippingFee);
        }
        if(Objects.nonNull(lastShippingFee)) {
            jpql.append("and shippingFee <= :lastShippingFee");
            parameters.put("lastShi" +
                    "ppingFee", lastShippingFee);
        }
        TypedQuery<Restore> query = entityManager.createQuery(jpql.toString(), Restore.class);
        parameters.forEach(query::setParameter);
        return query.getResultList();
    }
}
