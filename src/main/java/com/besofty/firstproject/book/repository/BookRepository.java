package com.besofty.firstproject.book.repository;

import com.besofty.firstproject.book.domain.BookDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookDO, Long>, QueryDslPredicateExecutor<BookDO> {
}
