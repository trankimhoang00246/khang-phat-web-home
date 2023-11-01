package com.khangphat.service;

import com.khangphat.model.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    public PostDto save(PostDto dto);
    public List<PostDto> findAll();
    public Page<PostDto> findAll(Pageable pageable);
    public PostDto findById(Long id);
    public PostDto updateById(PostDto Dto, Long id);
    public boolean deleteById(Long id);
    public boolean deleteByIds(List<Long> ids);
}
