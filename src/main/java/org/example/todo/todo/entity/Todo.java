package org.example.todo.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.todo.common.BaseEntity;
import org.example.todo.user.entity.User;


@Getter
@Setter
@Entity(name = "todo")
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 자동 생성
    private Long todoId;

    @Column(nullable = false, unique = true) // null 허용 안함
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    protected Todo() {
    }

    private Todo(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }

    public static Todo create(String username, String title, String contents) {
        return new Todo(username, title, contents);
    }
}
