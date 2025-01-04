package org.example.todo.common;

import org.example.todo.todo.entity.Todo;

import java.util.function.Function;

public class DtoMapper {

    public static <T> T mapToDto(Todo todo, Function<Todo, T> mapper) {
        return mapper.apply(todo);
    }
}
