package infrastructure.repository.spec;

import org.springframework.data.jpa.domain.Specification;
import com.algaworks.algafood.domain.model.Restore;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;

public class RestoreWithFreeShippingSpec implements Specification<Restore> {

    @Override
    public Predicate toPredicate(Root<Restore> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("shippingFee"), BigDecimal.ZERO);
    }
}
