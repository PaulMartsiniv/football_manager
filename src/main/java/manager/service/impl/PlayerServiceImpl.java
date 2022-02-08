package manager.service.impl;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import manager.model.Player;
import manager.repository.PlayerRepository;
import manager.repository.TeamRepository;
import manager.repository.spec.SpecificationManager;
import manager.service.PlayerService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final SpecificationManager<Player> manager;
    private final PlayerRepository playerRepo;
    private final TeamRepository teamRepo;

    @Override
    public Player save(Player player) {
        return playerRepo.save(player);
    }

    @Override
    public Player saveToTeam(Long teamId, Player player) {
        player.setTeam(teamRepo.getById(teamId));
        return playerRepo.save(player);
    }

    @Override
    public Player getById(Long id) {
        return playerRepo.getById(id);
    }

    @Override
    public List<Player> findAll() {
        return playerRepo.findAll();
    }

    @Override
    public List<Player> findAll(Map<String, String> params) {
        Specification<Player> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Player> spec = manager.get(entry.getKey(),
                    entry.getValue().split(","));
            specification = specification == null
                    ? Specification.where(spec) : specification.and(spec);
        }
        return playerRepo.findAll(specification);
    }

    @Override
    public Player update(Player player) {
        return playerRepo.save(player);
    }

    @Override
    public void deleteById(Long id) {
        playerRepo.deleteById(id);
    }
}
