package com.noticeBoard.app.repositories;

import com.noticeBoard.app.model.Message;
import com.noticeBoard.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> getAllBySenderOrderByCreatedDesc(User user);

    List<Message> getAllByReceiverOrderByCreatedDesc(User user);
}
