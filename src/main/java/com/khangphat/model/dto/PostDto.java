package com.khangphat.model.dto;

import com.khangphat.model.entity.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Timestamp;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    @NotNull
    private Long id;
    @NotNull
    private String title;
    private String content;
    @NotNull
    private boolean isPublic = true;
    private String description;
    private Timestamp createAt;
    private Timestamp updateAt;
    private Set<TagDto> setTag;
}
