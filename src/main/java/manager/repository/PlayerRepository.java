package manager.repository;

import java.math.BigDecimal;
import manager.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>,
        JpaSpecificationExecutor<Player> {
    @Modifying
    @Query("UPDATE Player p "
            + "SET p.fullAmount =: fullAmount, p.team =: ToTeamId "
            + "WHERE p.id =: id")
    void updateAmountAndTeamId(Long id, BigDecimal fullAmount, Long toTeamId);
}
