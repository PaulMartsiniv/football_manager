package manager.init;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import manager.model.Player;
import manager.model.Team;
import manager.service.PlayerService;
import manager.service.TeamService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInit implements ApplicationRunner {
    private final PlayerService playerService;
    private final TeamService teamService;

    @Override
    public void run(ApplicationArguments args) {
        saveAll();
    }

    private void saveAll() {
        List<Player> manchesterPlayers = getManchesterPlayers();
        List<Player> liverpoolPlayers = getLiverpoolPlayers();
        Team manchesterTeam = getManchesterTeam();
        Team liverpoolTeam = getLiverpoolTeam();
        manchesterPlayers.forEach(player -> {
            player.setTeam(manchesterTeam);
            playerService.save(player);
        });
        liverpoolPlayers.forEach(player -> {
            player.setTeam(liverpoolTeam);
            playerService.save(player);
        });
    }

    private Team getLiverpoolTeam() {
        Team liverpool = Team.builder()
                .name("Liverpool F.C.")
                .commissionFromTheTeam(0.08F)
                .budget(BigDecimal.valueOf(60_000_000))
                .country("England")
                .town("Liverpool")
                .build();
        return teamService.save(liverpool);
    }

    private Team getManchesterTeam() {
        Team manchester = Team.builder()
                .name("Manchester United F.C.")
                .commissionFromTheTeam(0.09F)
                .budget(BigDecimal.valueOf(100_000_000))
                .country("England")
                .town("Manchester")
                .build();
        return teamService.save(manchester);
    }

    private List<Player> getManchesterPlayers() {
        return List.of(
                Player.builder()
                        .fullName("Frederico Rodrigues de Paula Santos ")
                        .ageInYears(28)
                        .theNumberOfMonthsOfPlayerExperience(33)
                        .build(),
                Player.builder()
                        .fullName("Cristiano Ronaldo dos Santos Aveiro")
                        .ageInYears(36)
                        .theNumberOfMonthsOfPlayerExperience(55)
                        .build(),
                Player.builder()
                        .fullName("Juan Manuel Mata García")
                        .ageInYears(33)
                        .theNumberOfMonthsOfPlayerExperience(39)
                        .build(),
                Player.builder()
                        .fullName("Bruno Miguel Borges Fernandes")
                        .ageInYears(27)
                        .theNumberOfMonthsOfPlayerExperience(32)
                        .build(),
                Player.builder()
                        .fullName("Paul Labile Pogba")
                        .ageInYears(28)
                        .theNumberOfMonthsOfPlayerExperience(35)
                        .build()
        );
    }

    private List<Player> getLiverpoolPlayers() {
        return List.of(
                Player.builder()
                        .fullName("Alisson Ramses Becker")
                        .ageInYears(29)
                        .theNumberOfMonthsOfPlayerExperience(23)
                        .build(),
                Player.builder()
                        .fullName("Fábio Henrique Tavares")
                        .ageInYears(28)
                        .theNumberOfMonthsOfPlayerExperience(30)
                        .build(),
                Player.builder()
                        .fullName("Virgil van Dijk")
                        .ageInYears(30)
                        .theNumberOfMonthsOfPlayerExperience(31)
                        .build(),
                Player.builder()
                        .fullName("Mohamed Salah Hamed Mahrous Ghaly")
                        .ageInYears(28)
                        .theNumberOfMonthsOfPlayerExperience(32)
                        .build(),
                Player.builder()
                        .fullName("Diogo José Teixeira da Silva")
                        .ageInYears(25)
                        .theNumberOfMonthsOfPlayerExperience(25)
                        .build()
        );
    }
}
