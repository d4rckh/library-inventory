package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.repository.entity.TagEntity;
import com.dfour.libraryplatform.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @GetMapping
    public List<TagEntity> getTags() {
        return tagService.findAll();
    }

    @PostMapping
    public TagEntity createTag(@RequestBody TagEntity tag) {
        return tagService.create(tag.getName());
    }

}
