package com.laborotoryproject.weather.parser.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@Builder
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String login;
    private String password;
    private String position;

    @Enumerated(EnumType.STRING)
    private Role role;
}
