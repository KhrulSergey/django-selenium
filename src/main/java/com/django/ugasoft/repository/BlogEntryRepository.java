package com.django.ugasoft.repository;

import com.django.ugasoft.model.BlogEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * Интерфейс доступа к данным сущности "Запись в блоге" из БД
 */
public interface BlogEntryRepository extends JpaRepository<BlogEntry, Long>,
    JpaSpecificationExecutor<BlogEntry> {

  Page<BlogEntry> findAllByOrderByIdAsc(Pageable pageable);
}

