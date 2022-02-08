package manager.service;

import java.util.List;
import java.util.Map;
import manager.model.Player;

public interface PlayerService extends GenericService<Player> {
    List<Player> findAll(Map<String, String> params);
}
