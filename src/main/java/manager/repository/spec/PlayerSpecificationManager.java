package manager.repository.spec;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import manager.model.Player;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PlayerSpecificationManager implements SpecificationManager<Player> {
    private final Map<String, SpecificationProvider<Player>> providersMap;

    public PlayerSpecificationManager(List<SpecificationProvider<Player>> list) {
        this.providersMap = list.stream()
                .collect(Collectors.toMap(SpecificationProvider::getFilterKey,
                        Function.identity()));
    }

    @Override
    public Specification<Player> get(String filterKey, String[] params) {
        if (!providersMap.containsKey(filterKey)) {
            throw new RuntimeException("Key " + filterKey + " is no supported for data filtering");
        }
        return providersMap.get(filterKey).getSpecification(params);
    }
}
