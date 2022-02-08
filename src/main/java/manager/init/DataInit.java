package manager.init;

import java.math.BigDecimal;
import java.time.LocalDate;
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
                        .birthDate(LocalDate.of(1994, 1, 1))
                        .monthOfExperience(33)
                        .build(),
                Player.builder()
                        .fullName("Cristiano Ronaldo dos Santos Aveiro")
                        .birthDate(LocalDate.of(1993, 1, 1))
                        .monthOfExperience(55)
                        .build(),
                Player.builder()
                        .fullName("Juan Manuel Mata García")
                        .birthDate(LocalDate.of(1992, 1, 1))
                        .monthOfExperience(39)
                        .build(),
                Player.builder()
                        .fullName("Bruno Miguel Borges Fernandes")
                        .birthDate(LocalDate.of(1991, 1, 1))
                        .monthOfExperience(32)
                        .build(),
                Player.builder()
                        .fullName("Paul Labile Pogba")
                        .birthDate(LocalDate.of(1990, 1, 1))
                        .monthOfExperience(35)
                        .build()
        );
    }

    private List<Player> getLiverpoolPlayers() {
        return List.of(
                Player.builder()
                        .fullName("Alisson Ramses Becker")
                        .birthDate(LocalDate.of(1989, 1, 1))
                        .monthOfExperience(23)
                        .build(),
                Player.builder()
                        .fullName("Fábio Henrique Tavares")
                        .birthDate(LocalDate.of(1990, 1, 1))
                        .monthOfExperience(30)
                        .build(),
                Player.builder()
                        .fullName("Virgil van Dijk")
                        .birthDate(LocalDate.of(1991, 1, 1))
                        .monthOfExperience(31)
                        .build(),
                Player.builder()
                        .fullName("Mohamed Salah Hamed Mahrous Ghaly")
                        .birthDate(LocalDate.of(1992, 1, 1))
                        .monthOfExperience(32)
                        .build(),
                Player.builder()
                        .fullName("Diogo José Teixeira da Silva")
                        .birthDate(LocalDate.of(1993, 1, 1))
                        .monthOfExperience(25)
                        .build()
        );
    }
}
