package com.rifqio.springsecurity.service;

import com.rifqio.springsecurity.model.Notices;
import com.rifqio.springsecurity.repository.NoticesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticesRepository noticesRepository;

    public List<Notices> getAllActiveNotices() {
        return noticesRepository.findAllActiveNotices();
    }
}
