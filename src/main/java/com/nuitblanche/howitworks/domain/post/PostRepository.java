package com.nuitblanche.howitworks.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Query(value = "SELECT p FROM Post p JOIN FETCH p.project JOIN FETCH p.postTags WHERE p.id = :id")
    Post findByIdFetchedPost(@Param("id") Long id);
}
