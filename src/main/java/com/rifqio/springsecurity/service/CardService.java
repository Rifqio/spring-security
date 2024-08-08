package com.rifqio.springsecurity.service;

import com.rifqio.springsecurity.model.Cards;
import com.rifqio.springsecurity.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardsRepository cardRepository;

    public List<Cards> getMyCurrentCardList(Long customerId) {
        return cardRepository.findByCustomerId(customerId);
    }
}
