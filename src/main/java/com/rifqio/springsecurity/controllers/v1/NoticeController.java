package com.rifqio.springsecurity.controllers.v1;

import com.rifqio.springsecurity.commons.dto.response.ApiResponse;
import com.rifqio.springsecurity.commons.dto.response.SuccessResponse;
import com.rifqio.springsecurity.model.Notices;
import com.rifqio.springsecurity.service.LoanService;
import com.rifqio.springsecurity.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/notice")
@RequiredArgsConstructor
public class NoticeController {
    private final LoanService loanService;
    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllActiveNotices() {
        List<Notices> notices = noticeService.getAllActiveNotices();
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                .body(SuccessResponse.success("Notices retrieved successfully", notices));
    }
}
