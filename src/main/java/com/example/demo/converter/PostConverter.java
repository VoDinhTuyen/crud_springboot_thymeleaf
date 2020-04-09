package com.example.demo.converter;

import com.example.demo.dto.PostDTO;
import com.example.demo.entity.PostEntity;
import org.springframework.stereotype.Component;

@Component
public class PostConverter {

    public PostDTO toDTO(PostEntity entity) {
        PostDTO dto = new PostDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setThumbnail(entity.getThumbnail());
        dto.setShortDescription(entity.getShortDescription());
        dto.setContent(entity.getContent());
        dto.setCategoryCode(entity.getCategory().getCode());
        return dto;
    }

    public PostEntity toEntity(PostDTO dto) {
        PostEntity entity = new PostEntity();
        entity.setTitle(dto.getTitle());
        entity.setThumbnail(dto.getThumbnail());
        entity.setShortDescription(dto.getShortDescription());
        entity.setContent(dto.getContent());
        return entity;
    }

    public PostEntity toEntity(PostEntity postEntity, PostDTO postDTO) {
        postEntity.setTitle(postDTO.getTitle());
        postEntity.setThumbnail(postDTO.getThumbnail());
        postEntity.setShortDescription(postDTO.getShortDescription());
        postEntity.setContent(postDTO.getContent());
        return postEntity;
    }
}
