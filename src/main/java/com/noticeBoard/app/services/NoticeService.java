package com.noticeBoard.app.services;

import com.noticeBoard.app.dto.AddNoticeDTO;
import com.noticeBoard.app.dto.CategoryDTO;
import com.noticeBoard.app.dto.NoticeDTO;
import com.noticeBoard.app.model.Category;
import com.noticeBoard.app.model.Notice;
import com.noticeBoard.app.repositories.CategoryRepository;
import com.noticeBoard.app.repositories.NoticeRepository;
import com.noticeBoard.app.repositories.UserRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NoticeService {

    private NoticeRepository noticeRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public NoticeService(NoticeRepository noticeRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.noticeRepository = noticeRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<NoticeDTO> getAll() {
        List<Notice> notices = noticeRepository.findAllWhereEndDateInFutureOrderByCreatedDesc(LocalDate.now());
        return getNoticeDTOS(notices);
    }

    public List<NoticeDTO> getAllByCategory(Long id) {
        Category category = categoryRepository.findOne(id);
        List<Notice> notices = noticeRepository.findAllByCategoriesWhereEndDateInFutureOrderByCreatedDesc(category, LocalDate.now());
        return getNoticeDTOS(notices);
    }

    private List<NoticeDTO> getNoticeDTOS(List<Notice> notices) {
        return notices.stream().map(this::getNoticeDTO).collect(Collectors.toList());
    }

    private NoticeDTO getNoticeDTO(Notice notice) {
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
    }

    public void saveNotice(AddNoticeDTO noticeDTO, String username) {
        Notice notice = new Notice();
        notice.setTitle(noticeDTO.getTitle());
        notice.setContent(noticeDTO.getContent());
        notice.setEndDate(noticeDTO.getEndDate());
        notice.setUser(userRepository.getByUsername(username));

        if (!noticeDTO.getPhoto().isEmpty()) {
            try {
                notice.setPhoto(IOUtils.toByteArray(noticeDTO.getPhoto().getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        notice.setCategories(noticeDTO.getCategories().stream().map(categoryDTO -> {
            Category category = new Category();
            category.setId(categoryDTO.getId());
            category.setTitle(categoryDTO.getTitle());
            return category;
        }).collect(Collectors.toList()));
        noticeRepository.save(notice);
    }

    public NoticeDTO getById(Long id) {
        Notice notice = noticeRepository.findOne(id);
        return getNoticeDTO(notice);
    }

    public boolean checkIfEnded(Long id) {
        Notice notice = noticeRepository.findOne(id);
        return notice.getEndDate().isBefore(LocalDate.now());
    }
}
