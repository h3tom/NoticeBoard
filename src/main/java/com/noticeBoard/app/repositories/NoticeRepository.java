package com.noticeBoard.app.repositories;

import com.noticeBoard.app.model.Category;
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
    List<Notice> findAllWhereEndDateInFutureOrderByCreatedDesc(@Param("endDate") LocalDate localDate);

    @Query("SELECT n FROM Notice n JOIN n.categories c WHERE c = :category " +
            "AND n.endDate >= :endDate ORDER BY n.created DESC")
    List<Notice> findAllByCategoriesWhereEndDateInFutureOrderByCreatedDesc(
            @Param("category") Category category, @Param("endDate") LocalDate localDate);

}
