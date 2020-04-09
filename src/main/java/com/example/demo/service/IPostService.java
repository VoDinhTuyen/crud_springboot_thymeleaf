package com.example.demo.service;

import com.example.demo.dto.PostDTO;
import com.example.demo.entity.PostEntity;

import java.util.List;

public interface IPostService {

    PostDTO save(PostDTO postDTO);
    List<PostDTO> findAll();
    PostDTO findOne(Long id);
    void delete(Long id);
    void update(PostDTO postDTO);
    List<PostEntity> searchHibernate(String searchKey);
    List<PostDTO> search(String searchKey);
}
