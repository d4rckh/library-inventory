package com.dfour.libraryplatform.service;

import com.dfour.libraryplatform.repository.TagRepository;
import com.dfour.libraryplatform.repository.entity.TagEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tags;

    public TagEntity create(String name) {
        TagEntity tag = new TagEntity();
        tag.setName(name);
        return tags.save(tag);
    }

    public List<TagEntity> findAll() {
        return tags.findAll();
    }
}