package manager.dto.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import manager.model.Team;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRequestDto {
    private String fullName;
    private Integer ageInYears;
    private Integer theNumberOfMonthsOfPlayerExperience;
    private BigDecimal fullAmount;
    private Team team;
}
