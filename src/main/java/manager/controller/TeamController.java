package manager.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import manager.dto.request.TeamRequestDto;
import manager.dto.response.TeamResponseDto;
import manager.model.Team;
import manager.service.TeamService;
import manager.service.mapper.TeamMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/teams")
public class TeamController {
    private final TeamMapper teamMapper;
    private final TeamService teamService;

    @PostMapping
    public TeamResponseDto add(@RequestBody TeamRequestDto playerRequestDto) {
        Team team = teamService.save(teamMapper.mapToModel(playerRequestDto));
        return teamMapper.toResponseDto(team);
    }

    @GetMapping("/{id}")
    public TeamResponseDto getById(@PathVariable Long id) {
        return teamMapper.toResponseDto(teamService.getById(id));
    }

    @GetMapping
    public List<TeamResponseDto> findAll() {
        return teamService.findAll().stream()
                .map(teamMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public TeamResponseDto update(@PathVariable Long id,
                                  @RequestBody @Valid TeamRequestDto dto) {
        Team team = teamMapper.mapToModel(dto);
        team.setId(id);
        return teamMapper.toResponseDto(teamService.update(team));
    }

    @PutMapping("/transfer")
    public void transfer(@RequestParam Long fromTeamId,
                                    @RequestParam Long playerId,
                                    @RequestParam Long toTeamId) {
        teamService.transferPlayer(fromTeamId, playerId, toTeamId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teamService.deleteById(id);
    }
}
