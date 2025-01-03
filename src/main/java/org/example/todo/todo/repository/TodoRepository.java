package org.example.todo.todo.repository;

import org.example.todo.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public interface TodoRepository extends JpaRepository<Todo, Long> {
    /**
     * RUD를 위한 TODO 테이블 조회 메서드
     * @param id
     * @return 생략
     */
    default Todo findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exit id =" + id));
    }
}
