package manager.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    private final TeamRepository teamRepo;
    private final PlayerRepository playerRepo;

    @Override
    public Team save(Team team) {
        return teamRepo.save(team);
    }

    @Override
    public Team getById(Long id) {
        return teamRepo.getById(id);
    }

    @Override
    public List<Team> findAll() {
        return teamRepo.findAll();
    }

    @Override
    public Team update(Team team) {
        return teamRepo.save(team);
    }

    @Override
    public void deleteById(Long id) {
        teamRepo.deleteById(id);
    }

    @Override
    public void transferPlayer(Long fromTeamId, Long playerId, Long toTeamId) {
        AmountCalculation calculation = new AmountCalculation();
        Team from = teamRepo.getById(fromTeamId);
        Team to = teamRepo.getById(toTeamId);
        Player player = playerRepo.getById(playerId);
        BigDecimal amount = calculation.getTransferAmount(
                player.getMonthOfExperience(),
                (LocalDate.now().getYear() - player.getBirthDate().getYear()),
                from.getCommissionFromTheTeam());

        playerRepo.updateAmountAndTeamId(playerId, amount, toTeamId);
        teamRepo.updateBudget(fromTeamId, from.getBudget().add(amount));
        teamRepo.updateBudget(toTeamId, to.getBudget().subtract(amount));
    }
}
