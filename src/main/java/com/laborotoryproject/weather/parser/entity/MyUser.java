package com.laborotoryproject.weather.parser.entity;

import com.laborotoryproject.weather.parser.entity.enums.Permissions;
import com.laborotoryproject.weather.parser.entity.enums.Role;
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

    @Enumerated(EnumType.STRING)
    private Permissions permissions;
}
