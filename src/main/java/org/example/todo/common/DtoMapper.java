package org.example.todo.common;

import org.example.todo.todo.entity.Todo;

import java.util.List;
import java.util.function.Function;

public class DtoMapper {

    public static <T> T mapToDto(Todo todo, Function<Todo, T> mapper) {
        return mapper.apply(todo);
    }

    public static <T> List<T> toDtoList(List<Todo> todoList, Function<Todo, T> mapper) {
        return todoList.stream()
                    .map(mapper)
                    .toList();
    }
}
