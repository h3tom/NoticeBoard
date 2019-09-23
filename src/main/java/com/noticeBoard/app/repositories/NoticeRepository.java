package com.noticeBoard.app.repositories;

import com.noticeBoard.app.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query("SELECT n FROM Notice n WHERE n.endDate >= :endDate ORDER BY n.created DESC")
    List<Notice> findBookByCategoryOrderedByTitle(@Param("endDate") LocalDate localDate);
}
