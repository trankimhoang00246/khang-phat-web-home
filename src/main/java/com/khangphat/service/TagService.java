package com.khangphat.service;

import com.khangphat.model.dto.PostDto;
import com.khangphat.model.dto.TagDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    public TagDto save(TagDto dto);
    public List<TagDto> findAll();
    public Page<TagDto> findAll(Pageable pageable);
    public TagDto findById(Long id);
    public TagDto updateById(TagDto Dto, Long id);
    public boolean deleteById(Long id);
    public boolean deleteByIds(List<Long> ids);
}
