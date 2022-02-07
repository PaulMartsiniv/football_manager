package manager.repository;

import java.math.BigDecimal;
import manager.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Modifying
    @Query("UPDATE Team t SET t.budget =: budget WHERE t.id =: id")
    void updateBudget(Long id, BigDecimal budget);
}
