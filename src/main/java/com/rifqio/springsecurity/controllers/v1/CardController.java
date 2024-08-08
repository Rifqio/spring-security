package com.rifqio.springsecurity.controllers.v1;

import com.rifqio.springsecurity.commons.dto.response.ApiResponse;
import com.rifqio.springsecurity.commons.dto.response.ErrorResponse;
import com.rifqio.springsecurity.commons.dto.response.SuccessResponse;
import com.rifqio.springsecurity.model.Cards;
import com.rifqio.springsecurity.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getCurrentCards(@RequestParam Long accountId) {
        List<Cards> cards = cardService.getMyCurrentCardList(accountId);
        if (cards.isEmpty()) return ResponseEntity.badRequest().body(ErrorResponse.notFound("No card found"));
        return ResponseEntity.ok(SuccessResponse.success("Card list retrieved successfully", cards));
    }
}
