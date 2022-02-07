package manager.repository.spec.filter;

import javax.persistence.criteria.CriteriaBuilder;
import manager.model.Player;
import manager.repository.spec.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;

public class PlayerNameSpecification implements SpecificationProvider<Player> {
    private static final String FILTER_KEY = "nameIn";
    private static final String FIELD_NAME = "name";

    @Override
    public Specification<Player> getSpecification(String[] params) {
        return (root, query, cb) -> {
            CriteriaBuilder.In<String> predicate = cb.in(root.get(FIELD_NAME));
            for (String value : params) {
                predicate.value(value);
            }
            return cb.and(predicate, predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
