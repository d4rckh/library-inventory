package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.entity.StatsEntity;
import com.dfour.libraryplatform.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    @GetMapping
    List<StatsEntity> getStats() {
        return statsService.getStats();
    }

}
