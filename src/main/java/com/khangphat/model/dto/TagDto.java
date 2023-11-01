package com.khangphat.model.dto;

import com.khangphat.model.entity.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
    @NotNull
    private Long id;
    private String name;
    private Timestamp createAt;
    private Timestamp updateAt;
    @NotNull
    private PostDto post;
}
