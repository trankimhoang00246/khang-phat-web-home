package com.khangphat.rest;

import com.khangphat.model.dto.PostDto;
import com.khangphat.model.dto.TagDto;
import com.khangphat.model.form.IdRequest;
import com.khangphat.model.form.IdsRequest;
import com.khangphat.model.response.GenericResponse;
import com.khangphat.service.impl.PostServiceImpl;
import com.khangphat.service.impl.TagServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController()
@RequestMapping("/api/tag")
public class TagResource {
    @Autowired
    private TagServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<GenericResponse> save(@RequestBody TagDto dto){
        try {
            TagDto result = service.save(dto);
            return result !=null?
                    ResponseEntity.ok().body(new GenericResponse("complete",result))
                    :
                    ResponseEntity.badRequest().body(new GenericResponse("error"));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(new GenericResponse("error"));
        }
    }
    @GetMapping(value = "/find-all")
    private ResponseEntity<List<TagDto>> findAll() {
        List<TagDto> result = service.findAll();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping(value = "/find-pageable")
    private ResponseEntity<Page<TagDto>> findPageable(Pageable pageable) {
        return ResponseEntity.ok().body(service.findAll(pageable));
    }

    @PutMapping("/update")
    private ResponseEntity<GenericResponse> updateById(@RequestBody @Valid TagDto dto) {
        try {
            TagDto result = service.updateById(dto, dto.getId());
            return result != null ?
                    ResponseEntity.ok().body(new GenericResponse("complete", result))
                    :
                    ResponseEntity.badRequest().body(new GenericResponse("error", null));
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(new GenericResponse("Lỗi hệ thống", null));
        }
    }

    @GetMapping(value = "/find-id")
    private ResponseEntity<GenericResponse> findById(@RequestBody IdRequest id) {
        try {
            TagDto result = service.findById(id.getId());
            return result != null ?
                    ResponseEntity.ok().body(new GenericResponse("complete", result))
                    :
                    ResponseEntity.badRequest().body(new GenericResponse("error", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new GenericResponse("internal system error"));
        }
    }


    @DeleteMapping("/delete-id")
    private ResponseEntity<GenericResponse> deleteById(@RequestBody IdRequest id) {
        try {
            return service.deleteById(id.getId()) ?
                    ResponseEntity.ok().body(GenericResponse.builder().message("complete").build()) :
                    ResponseEntity.badRequest().body(GenericResponse.builder().message("error").build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(GenericResponse.builder().message("internal system error").build());
        }
    }

    @DeleteMapping("/delete-ids")
    private ResponseEntity<GenericResponse> deleteByIds(@RequestBody IdsRequest ids) {
        try {
            return service.deleteByIds(ids.getIds()) ?
                    ResponseEntity.ok().body(GenericResponse.builder().message("complete").build())
                    :
                    ResponseEntity.badRequest().body(GenericResponse.builder().message("error").build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(GenericResponse.builder().message("internal system error").build());
        }
    }
}
