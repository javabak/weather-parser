package com.laborotoryproject.weather.parser.entity;


import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.laborotoryproject.weather.parser.entity.Permissions.WEATHER_DELETE;
import static com.laborotoryproject.weather.parser.entity.Permissions.WEATHER_GET;

public enum Role {
    ADMIN(Set.of(WEATHER_DELETE, WEATHER_GET)),
    USER(Set.of(WEATHER_GET));


    private final Set<Permissions> permissionsSet;

    Role(Set<Permissions> permissionsSet) {
        this.permissionsSet = permissionsSet;
    }

    public Set<Permissions> getPermissionsSet() {
        return permissionsSet;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissionsGrantedAuthorities = getPermissionsSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissionsGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissionsGrantedAuthorities;
    }
}
