package manager.dto.response;

import java.math.BigDecimal;
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
public class TeamResponseDto {
    private Long id;
    private String name;
    private Float commissionFromTheTeam;
    private BigDecimal budget;
    private String country;
    private String town;
}
