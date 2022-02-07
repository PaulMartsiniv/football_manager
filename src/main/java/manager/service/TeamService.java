package manager.service;

import manager.model.Team;

public interface TeamService extends GenericService<Team> {
    void transferPlayer(Long teamId, Long playerId, Long buyingTeamId);
}
