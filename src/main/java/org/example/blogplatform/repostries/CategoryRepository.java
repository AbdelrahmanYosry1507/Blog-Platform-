package org.example.blogplatform.repostries;
import org.example.blogplatform.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @Query("SELECT c FROM Category c JOIN c.posts")
    List<Category> findAllWithPostCount();

    boolean existsByName(String name);
}
