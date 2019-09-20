package com.noticeBoard.app.repositories;

import com.noticeBoard.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Long countByEmail(String email);

    Long countByUsername(String userName);
}
