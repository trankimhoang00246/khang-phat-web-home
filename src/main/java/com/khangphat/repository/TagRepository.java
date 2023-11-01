package com.khangphat.repository;

import com.khangphat.model.entity.Post;
import com.khangphat.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {
}
