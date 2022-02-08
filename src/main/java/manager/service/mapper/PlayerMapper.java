package manager.service.mapper;

import lombok.AllArgsConstructor;
import manager.dto.request.PlayerRequestDto;
import manager.dto.response.PlayerResponseDto;
import manager.model.Player;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PlayerMapper implements RequestDtoMapper<PlayerRequestDto, Player>,
        ResponseDtoMapper<PlayerResponseDto, Player> {
    private final TeamMapper teamMapper;

    @Override
    public Player mapToModel(PlayerRequestDto dto) {
        return Player.builder()
                .fullName(dto.getFullName())
                .birthDate(dto.getBirthDate())
                .monthOfExperience(
                        dto.getMonthOfExperience())
                .salary(dto.getSalary())
                .team(teamMapper.mapToModel(dto.getTeam()))
                .build();
    }

    @Override
    public PlayerResponseDto toResponseDto(Player player) {
        return PlayerResponseDto.builder()
                .id(player.getId())
                .fullName(player.getFullName())
                .birthDate(player.getBirthDate())
                .monthOfExperience(player.getMonthOfExperience())
                .salary(player.getSalary())
                .team(teamMapper.toResponseDto(player.getTeam()))
                .build();
    }
}
