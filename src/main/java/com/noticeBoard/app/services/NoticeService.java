package com.noticeBoard.app.services;

import com.noticeBoard.app.dto.CategoryDTO;
import com.noticeBoard.app.dto.NoticeDTO;
import com.noticeBoard.app.model.Notice;
import com.noticeBoard.app.repositories.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NoticeService {

    private NoticeRepository noticeRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public List<NoticeDTO> getAll() {
        List<Notice> notices = noticeRepository.findBookByCategoryOrderedByTitle(LocalDate.now());
        return notices.stream().map(notice -> {
            NoticeDTO noticeDTO = new NoticeDTO();

            noticeDTO.setId(notice.getId());
            noticeDTO.setTitle(notice.getTitle());
            noticeDTO.setContent(notice.getContent());
            noticeDTO.setOwner(notice.getUser().getUsername());
            noticeDTO.setCreated(notice.getCreated());
            noticeDTO.setEndDate(notice.getEndDate());

            if (notice.getPhoto() != null) {
                noticeDTO.setImage(Base64.getEncoder().encodeToString(notice.getPhoto()));
            }

            noticeDTO.setCategories(notice.getCategories().stream().map(category -> {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setTitle(category.getTitle());
                return categoryDTO;
            }).collect(Collectors.toList()));

            return noticeDTO;
        }).collect(Collectors.toList());
    }
}
