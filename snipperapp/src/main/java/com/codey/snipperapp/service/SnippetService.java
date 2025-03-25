package com.codey.snipperapp.service;

import com.codey.snipperapp.entity.Snippet;
import com.codey.snipperapp.repository.SnippetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnippetService {

  private final SnippetRepository snippetRepository;

  public SnippetService(SnippetRepository snippetRepository) {
    this.snippetRepository = snippetRepository;
  }

  public List<Snippet> getAllSnippets() {
    return snippetRepository.findAll();
  }

  public Snippet getSnippetById(long id) {
    return snippetRepository.findById(id).orElse(null);
  }

  public Snippet saveSnippet(Snippet snippet) {
    return snippetRepository.save(snippet);
  }

  public List<Snippet> findAllByLanguage(String lang) {
    return snippetRepository.findAllByLanguage(lang);
  }
}
