package manager.service.mapper;

public interface ResponseDtoMapper<D, T> {
    D toResponseDto(T t);
}
