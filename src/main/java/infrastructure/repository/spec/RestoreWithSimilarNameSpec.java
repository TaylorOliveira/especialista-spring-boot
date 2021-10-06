package infrastructure.repository.spec;

import org.springframework.data.jpa.domain.Specification;
import com.algaworks.algafood.domain.model.Restore;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestoreWithSimilarNameSpec implements Specification<Restore> {

    private String name;

    @Override
    public Predicate toPredicate(Root<Restore> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("name"), String.format("%s%%", name));
    }
}
