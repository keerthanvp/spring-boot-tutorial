package com.vpk.tutorial.springboottutorial.model;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Size(min = 2,max = 20)
    private String name;

    @Positive
    private Integer age;

    @OneToMany(mappedBy = "user")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Post> posts;
}
