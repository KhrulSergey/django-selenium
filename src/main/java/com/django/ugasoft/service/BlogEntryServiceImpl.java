package com.django.ugasoft.service;

import com.django.ugasoft.model.BlogEntry;
import com.django.ugasoft.repository.BlogEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса-прослойки между контроллером и сервисом доступа к данным
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class BlogEntryServiceImpl implements BlogEntryService {

  private static final Logger LOG = LoggerFactory.getLogger(BlogEntryServiceImpl.class);

  private BlogEntryRepository blogEntryRepository;

  public BlogEntryServiceImpl(BlogEntryRepository blogEntryRepository) {
    this.blogEntryRepository = blogEntryRepository;
  }

  /**
   * Получение списка записей из БД
   *
   * @return Список имеющихся записей из БД
   */
  @Override
  public Page<BlogEntry> list(Pageable pageable) {
    LOG.info("getting blogEntries: ");
    return blogEntryRepository.findAllByOrderByIdAsc(pageable);
  }

  /**
   * Добавление новой записи в БД.
   *
   * @param blogEntry Добавляемая запись
   * @return добавленная запись
   */
  @Override
  public long add(BlogEntry blogEntry) {
    LOG.info("save blogEntry: %s", blogEntry);
    BlogEntry blogEntrySaved = blogEntryRepository.saveAndFlush(blogEntry);
    return blogEntrySaved.getId();
  }

  /**
   * Получение конкретной записи из БД
   *
   * @param id Идентификатор записи для поиска
   * @return Данные о записи или null - если запись не найдена
   */
  @Override
  public BlogEntry get(long id) {
    BlogEntry blogEntry = blogEntryRepository.findById(id).orElse(null);
    LOG.info("getting blogEntry: %s", blogEntry);
    return blogEntry;
  }

  /**
   * Редактирование существующей записи в БД.
   *
   * @param blogEntry Обновленные данные для внесения в БД
   * @return отредактированная запись
   */
  @Override
  public BlogEntry edit(BlogEntry blogEntry) {
    BlogEntry updatedBlogEntry = null;
    if (blogEntryRepository.existsById(blogEntry.getId())) {
      LOG.info("blogEntry: %s is edited", blogEntry);
      updatedBlogEntry = blogEntryRepository.saveAndFlush(blogEntry);

    } else {
      LOG.warn("blogEntry: %s is not exist");
    }
    return updatedBlogEntry;
  }

  /**
   * Удаление записи в БД
   *
   * @param id Идентификатор записи для удаления
   */
  @Override
  public void delete(long id) {
    blogEntryRepository.deleteById(id);
    LOG.info("delete blogEntry by id: %d", id);
  }


}
