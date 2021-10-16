package infrastructure.repository.spec;

import org.springframework.data.jpa.domain.Specification;
import com.algaworks.algafood.domain.model.Restore;
import java.math.BigDecimal;

public class RestoreSpecs {

    public static Specification<Restore> withFreeShipping() {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("shippingFee"), BigDecimal.ZERO);
    }

    public static Specification<Restore> withSimilarName(String name) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), String.join("", "%", name, "%"));
    }
}
