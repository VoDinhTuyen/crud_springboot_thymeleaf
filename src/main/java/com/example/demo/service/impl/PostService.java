package com.example.demo.service.impl;

import com.example.demo.converter.PostConverter;
import com.example.demo.dto.PostDTO;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.PostEntity;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.IPostService;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostConverter postConverter;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public PostDTO save(PostDTO postDTO) {
        PostEntity postEntity = new PostEntity();
        CategoryEntity categoryEntity = categoryRepository.findOneByCode(postDTO.getCategoryCode());
        postEntity = postConverter.toEntity(postDTO);
        postEntity.setCategory(categoryEntity);
        postRepository.save(postEntity);
        return postConverter.toDTO(postEntity);
    }

    @Override
    public List<PostDTO> findAll() {
        List<PostDTO> results = new ArrayList<>();
        List<PostEntity> entities = postRepository.findAll();
        for(PostEntity entity: entities) {
            PostDTO dto = postConverter.toDTO(entity);
            results.add(dto);
        }
        return results;
    }

    @Override
    public PostDTO findOne(Long id) {
        PostEntity postEntity = postRepository.findOneById(id);
        PostDTO postDTO = postConverter.toDTO(postEntity);
        return postDTO;
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void update(PostDTO postDTO) {
        CategoryEntity categoryEntity = categoryRepository.findOneByCode(postDTO.getCategoryCode());
        PostEntity postEntity = postRepository.findOneById(postDTO.getId());
        postEntity.setCategory(categoryEntity);
        postRepository.save(postConverter.toEntity(postEntity, postDTO));
    }

    @Override
    public List<PostEntity> searchHibernate(String searchKey) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(PostEntity.class)
                .get();
        org.apache.lucene.search.Query query = queryBuilder
                .keyword()
                .onFields("title", "content")
                .matching(searchKey)
                .createQuery();
        Query jpaQuery = fullTextEntityManager.createFullTextQuery(query, PostEntity.class);
        return (List<PostEntity>) jpaQuery.getResultList();
    }

    @Override
    public List<PostDTO> search(String searchKey) {
        List<PostEntity> entities = postRepository.findByTitleLike(searchKey);
        List<PostDTO> results = new ArrayList<>();
        for(PostEntity entity: entities) {
            PostDTO postDTO = postConverter.toDTO(entity);
            results.add(postDTO);
        }
        return results;
    }
}
