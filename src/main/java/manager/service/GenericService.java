package manager.service;

import java.util.List;

public interface GenericService<T> {
    T save(T t);

    T getById(Long id);

    List<T> findAll();

    T update(T t);

    void deleteById(Long id);
}
