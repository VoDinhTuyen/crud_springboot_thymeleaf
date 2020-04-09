package com.example.demo.repository;

import com.example.demo.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

    PostEntity findOneById(Long id);

    @Query("select p from PostEntity p where p.title like ?1")
    List<PostEntity> findByTitleLike(String searchKey);
}
