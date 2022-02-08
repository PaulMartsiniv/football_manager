package manager.service.impl;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import manager.model.Player;
import manager.model.Team;
import manager.repository.PlayerRepository;
import manager.repository.TeamRepository;
import manager.service.TeamService;
import manager.util.AmountCalculation;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamDao;
    private final PlayerRepository playerDao;

    @Override
    public Team save(Team team) {
        return teamDao.save(team);
    }

    @Override
    public Team getById(Long id) {
        return teamDao.getById(id);
    }

    @Override
    public List<Team> findAll() {
        return teamDao.findAll();
    }

    @Override
    public Team update(Team team) {
        return teamDao.save(team);
    }

    @Override
    public void deleteById(Long id) {
        teamDao.deleteById(id);
    }

    @Override
    public void transferPlayer(Long fromTeamId, Long playerId, Long toTeamId) {
        AmountCalculation calculation = new AmountCalculation();
        Team from = teamDao.getById(fromTeamId);
        Team to = teamDao.getById(toTeamId);
        Player player = playerDao.getById(playerId);
        BigDecimal amount = calculation.getTransferAmount(
                player.getTheNumberOfMonthsOfPlayerExperience(),
                player.getAgeInYears(),
                from.getCommissionFromTheTeam());

        playerDao.updateAmountAndTeamId(playerId, amount, toTeamId);
        teamDao.updateBudget(fromTeamId, from.getBudget().add(amount));
        teamDao.updateBudget(toTeamId, to.getBudget().subtract(amount));
    }
}
