package com.github.majidshoorabi.security.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author majid.shoorabi
 * @created 2022-05-May
 * @project peysaz
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private Boolean enabled = Boolean.TRUE;

    @ElementCollection(targetClass = UserRole.class)
    @CollectionTable(name = "authorities",
            joinColumns = @JoinColumn(name = "email", referencedColumnName = "email"))
    @Enumerated(EnumType.STRING)
    private List<UserRole> authorities;

}