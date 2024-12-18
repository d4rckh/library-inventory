package com.dfour.libraryplatform.controller;

import com.dfour.libraryplatform.entity.TagEntity;
import com.dfour.libraryplatform.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dfour.libraryplatform.security.AppAuthorization.EnsureUserLibrarian;

@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TagEntity createTag(@RequestBody final TagEntity tag) {
        EnsureUserLibrarian();

        return tagService.create(tag.getName());
    }

    @GetMapping
    public List<TagEntity> getTags() {
        return tagService.findAll();
    }
}
