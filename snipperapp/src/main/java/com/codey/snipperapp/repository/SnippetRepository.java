package com.codey.snipperapp.repository;

import com.codey.snipperapp.entity.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SnippetRepository extends JpaRepository<Snippet, Long> {
  List<Snippet> findAllByLanguage(String lang);
}
