package com.lh.repository.test;

import com.lh.entity.test.NameHref;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameHrefRepository extends JpaRepository<NameHref,Long> {
    boolean existsByName(String name);

    NameHref findAllByName(String name);
}
