package com.django.ugasoft.service;

import com.django.ugasoft.model.BlogEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BlogEntryService {

  /**
   * Получение списка записей из БД
   *
   * @return Список имеющихся записей из БД
   */
  Page<BlogEntry> list(Pageable pageable);

  /**
   * Добавление новой записи в БД.
   *
   * @param blogEntry Добавляемая запись
   * @return добавленная запись
   */
  long add(BlogEntry blogEntry);

  /**
   * Получение конкретной записи из БД
   *
   * @param id Идентификатор записи для поиска
   * @return Данные о записи или null - если запись не найдена
   */
  BlogEntry get(long id);

  /**
   * Редактирование существующей записи в БД.
   *
   * @param blogEntry Обновленные данные для внесения в БД
   * @return отредактированная запись
   */
  BlogEntry edit(BlogEntry blogEntry) ;

  /**
   * Удаление записи в БД
   *
   * @param id Идентификатор записи для удаления
   */
  void delete(long id);

}
