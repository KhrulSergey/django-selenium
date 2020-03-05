package com.django.ugasoft.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter
@ToString(exclude="id")
@EqualsAndHashCode(exclude="id")
@RequiredArgsConstructor
@Entity
@Table(name = "blog_entry")
public class BlogEntry implements Serializable {

  private static final long serialVersionUID = 1L;

  //Properties
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Basic
  private String title;

  @Basic
  private String slug;

  @Basic
  private String txtMarkdown;

  @Basic
  private String text;
}
