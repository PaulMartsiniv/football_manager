package manager.service.mapper;

import lombok.AllArgsConstructor;
import manager.dto.request.TeamRequestDto;
import manager.dto.response.TeamResponseDto;
import manager.model.Team;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TeamMapper implements RequestDtoMapper<TeamRequestDto, Team>,
        ResponseDtoMapper<TeamResponseDto, Team> {
    private final PlayerMapper playerMapper;

    @Override
    public Team mapToModel(TeamRequestDto dto) {
        return Team.builder()
                .name(dto.getName())
                .commissionFromTheTeam(dto.getCommissionFromTheTeam())
                .budget(dto.getBudget())
                .country(dto.getCountry())
                .town(dto.getTown())
                .build();
    }

    @Override
    public TeamResponseDto toResponseDto(Team team) {
        return TeamResponseDto.builder()
                .id(team.getId())
                .name(team.getName())
                .commissionFromTheTeam(team.getCommissionFromTheTeam())
                .budget(team.getBudget())
                .country(team.getCountry())
                .town(team.getTown())
                .build();
    }
}
