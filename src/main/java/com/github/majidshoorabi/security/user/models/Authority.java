package com.github.majidshoorabi.security.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "Authorities")
public class Authority {

    @Id
    private String email;
    private String authority;
}
