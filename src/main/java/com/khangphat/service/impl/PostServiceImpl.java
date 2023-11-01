package com.khangphat.service.impl;

import com.khangphat.model.dto.PostDto;
import com.khangphat.model.entity.Post;
import com.khangphat.repository.PostRepository;
import com.khangphat.service.PostService;
import com.khangphat.utils.ModelMapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository repository;
    @Autowired
    private ModelMapperUtil<PostDto, Post> mapperUtil;

    @Override
    public PostDto save(PostDto dto) {
        try {
            Post entity = mapperUtil.toEntity(dto);
            entity = repository.save(entity);
            return mapperUtil.toDto(entity);
        } catch (Exception e) {
            System.out.println("error at function save in NewsServices");
            return null;
        }
    }


    @Override
    public List<PostDto> findAll() {
        try {
            List<PostDto> list = new ArrayList<>();
            Iterable<Post> all = repository.findAll();
            for (Post item : all) {
                list.add(mapperUtil.toDto(item));
            }
            return list;
        } catch (Exception e) {
            System.out.println("error at function findAll in NewsServices");
            return null;
        }
    }

    @Override
    public Page<PostDto> findAll(Pageable pageable) {
        try {
            Page<Post> page = repository.findAll(PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    pageable.getSortOr(Sort.by(Sort.Direction.DESC, "createAt"))
            ));
            Page<PostDto> dtos = page.map(item -> {
                return mapperUtil.toDto(item);
            });
            return dtos;
        } catch (Exception e) {
            System.out.println("error at function findAll in NewsServices");
            return null;
        }
    }

    @Override
    public PostDto findById(Long id) {
        try {
            Post entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "can not fine post by id: " + id));
            return mapperUtil.toDto(entity);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    //     find truoc khi update
    public PostDto updateById(PostDto dto, Long id) {
        try {
            repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "can not fine post by id: " + id));
            Post newEntity = mapperUtil.toEntity(dto);
            Post result =repository.save(newEntity);
            return mapperUtil.toDto(result);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteByIds(List<Long> ids) {
        try {
            repository.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
