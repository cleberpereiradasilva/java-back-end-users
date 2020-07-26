package br.com.user.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.user.model.User;

public interface UserRepository extends JpaRepository<User, String>{}
