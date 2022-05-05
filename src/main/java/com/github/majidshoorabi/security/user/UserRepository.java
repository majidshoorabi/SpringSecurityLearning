package com.github.majidshoorabi.security.user;

import com.github.majidshoorabi.security.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author majid.shoorabi
 * @created 2022-05-May
 * @project peysaz
 */

public interface UserRepository extends JpaRepository<User, Long> {

}
