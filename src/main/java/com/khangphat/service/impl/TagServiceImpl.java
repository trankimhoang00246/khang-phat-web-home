package com.khangphat.service.impl;

import com.khangphat.model.dto.PostDto;
import com.khangphat.model.dto.TagDto;
import com.khangphat.model.entity.Post;
import com.khangphat.model.entity.Tag;
import com.khangphat.repository.TagRepository;
import com.khangphat.service.TagService;
import com.khangphat.utils.ModelMapperUtil;
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
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository repository;
    @Autowired
    private ModelMapperUtil<TagDto, Tag> mapperUtil;


    @Override
    public TagDto save(TagDto dto) {
        try {
            mapperUtil.updateClasses(TagDto.class,Tag.class);
            Tag entity = mapperUtil.toEntity(dto);
            entity = repository.save(entity);
            return mapperUtil.toDto(entity);
        } catch (Exception e) {
            System.out.println("error at function save in NewsServices");
            return null;
        }
    }


    @Override
    public List<TagDto> findAll() {
        try {
            mapperUtil.updateClasses(TagDto.class,Tag.class);
            List<TagDto> list = new ArrayList<>();
            Iterable<Tag> all = repository.findAll();
            for (Tag item : all) {
                list.add(mapperUtil.toDto(item));
            }
            return list;
        } catch (Exception e) {
            System.out.println("error at function findAll in NewsServices");
            return null;
        }
    }

    @Override
    public Page<TagDto> findAll(Pageable pageable) {
        try {
            mapperUtil.updateClasses(TagDto.class,Tag.class);
            Page<Tag> page = repository.findAll(PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    pageable.getSortOr(Sort.by(Sort.Direction.DESC, "createAt"))
            ));
            Page<TagDto> dtos = page.map(item -> {
                return mapperUtil.toDto(item);
            });
            return dtos;
        } catch (Exception e) {
            System.out.println("error at function findAll in NewsServices");
            return null;
        }
    }

    @Override
    public TagDto findById(Long id) {
        try {
            mapperUtil.updateClasses(TagDto.class,Tag.class);
            Tag entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "can not fine post by id: " + id));
            return mapperUtil.toDto(entity);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    //     find truoc khi update
    public TagDto updateById(TagDto dto, Long id) {
        try {
            mapperUtil.updateClasses(TagDto.class,Tag.class);
            repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "can not fine post by id: " + id));
            Tag newEntity = mapperUtil.toEntity(dto);
            Tag result =repository.save(newEntity);
            return mapperUtil.toDto(result);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            mapperUtil.updateClasses(TagDto.class,Tag.class);
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
            mapperUtil.updateClasses(TagDto.class,Tag.class);
            repository.deleteAllById(ids);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
