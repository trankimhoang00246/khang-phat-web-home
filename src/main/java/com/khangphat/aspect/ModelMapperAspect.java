package com.khangphat.aspect;

import com.khangphat.model.dto.PostDto;
import com.khangphat.model.entity.Post;
import com.khangphat.utils.ModelMapperUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ModelMapperAspect {
    @Autowired
    private ModelMapperUtil<PostDto, Post> mapperUtil;
    @Before("execution(* com.khangphat.service.impl.PostServiceImpl.*(..))")
    public void beforeUpdateMapperClassesPostService() {
        mapperUtil.updateClasses(PostDto.class,Post.class);
    }
}
