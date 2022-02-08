package manager.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import manager.dto.request.PlayerRequestDto;
import manager.dto.response.PlayerResponseDto;
import manager.model.Player;
import manager.service.PlayerService;
import manager.service.mapper.PlayerMapper;
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
@RequestMapping("/players")
public class PlayerController {
    private final PlayerMapper playerMapper;
    private final PlayerService playerService;

    public PlayerController(PlayerMapper playerMapper, PlayerService playerService) {
        this.playerMapper = playerMapper;
        this.playerService = playerService;
    }

    @PostMapping
    public PlayerResponseDto add(@RequestBody PlayerRequestDto playerRequestDto) {
        Player player = playerService.save(playerMapper.mapToModel(playerRequestDto));
        return playerMapper.toResponseDto(player);
    }

    @PostMapping("/{teamId}")
    public PlayerResponseDto addToTeam(@PathVariable Long teamId,
                                       @RequestBody PlayerRequestDto playerRequestDto) {
        Player player = playerService.saveToTeam(teamId,
                playerMapper.mapToModel(playerRequestDto));
        return playerMapper.toResponseDto(player);
    }

    @GetMapping("/{id}")
    public PlayerResponseDto getById(@PathVariable Long id) {
        return playerMapper.toResponseDto(playerService.getById(id));
    }

    @GetMapping
    public List<PlayerResponseDto> findAll(@RequestParam Map<String, String> params) {
        return playerService.findAll(params).stream()
                .map(playerMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public PlayerResponseDto update(@PathVariable Long id,
                                    @RequestBody @Valid PlayerRequestDto dto) {
        Player player = playerMapper.mapToModel(dto);
        player.setId(id);
        return playerMapper.toResponseDto(playerService.update(player));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        playerService.deleteById(id);
    }
}
