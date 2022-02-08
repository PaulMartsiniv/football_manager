package manager.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponseDto {
    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private Integer monthOfExperience;
    private BigDecimal salary;
    private TeamResponseDto team;
}
