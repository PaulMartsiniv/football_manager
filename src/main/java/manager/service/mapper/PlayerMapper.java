package manager.service.mapper;

import manager.dto.request.PlayerRequestDto;
import manager.dto.response.PlayerResponseDto;
import manager.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper implements RequestDtoMapper<PlayerRequestDto, Player>,
        ResponseDtoMapper<PlayerResponseDto, Player> {
    @Override
    public Player mapToModel(PlayerRequestDto dto) {
        return Player.builder()
                .fullName(dto.getFullName())
                .ageInYears(dto.getAgeInYears())
                .theNumberOfMonthsOfPlayerExperience(
                        dto.getTheNumberOfMonthsOfPlayerExperience())
                .fullAmount(dto.getFullAmount())
                .team(dto.getTeam())
                .build();
    }

    @Override
    public PlayerResponseDto toResponseDto(Player player) {
        return PlayerResponseDto.builder()
                .id(player.getId())
                .fullName(player.getFullName())
                .ageInYears(player.getAgeInYears())
                .theNumberOfMonthsOfPlayerExperience(
                        player.getTheNumberOfMonthsOfPlayerExperience())
                .fullAmount(player.getFullAmount())
                .team(player.getTeam())
                .build();
    }
}
