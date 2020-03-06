package com.django.ugasoft;

import com.django.ugasoft.repository.BlogEntryRepository;
import com.django.ugasoft.service.BlogEntryService;
import com.django.ugasoft.service.BlogEntryServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {SpringApplication.run(Application.class, args);
  }

  @Bean
  public BlogEntryService blogEntryInteractor(BlogEntryRepository blogEntryRepository) {
    return new BlogEntryServiceImpl(blogEntryRepository);
  }
}
