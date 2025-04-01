package com.codey.snipperapp.service;

import com.codey.snipperapp.entity.Snippet;
import com.codey.snipperapp.repository.SnippetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnippetService {

  private final SnippetRepository snippetRepository;
  private final EncryptionService encryptionService;

  public SnippetService(SnippetRepository snippetRepository,
                        EncryptionService encryptionService) {
    this.snippetRepository = snippetRepository;
    this.encryptionService = encryptionService;
  }

  public List<Snippet> getAllSnippets() throws Exception {
    for (Snippet snippet : snippetRepository.findAll()) {
      snippet.setCode(encryptionService.decrypt(snippet.getCode()));
    }
    return snippetRepository.findAll();
  }


  public Snippet getSnippetById(long id) throws Exception {
    Snippet snippet = snippetRepository.findById(id).orElse(null);
    if (snippet != null) {
      snippet.setCode(encryptionService.decrypt(snippet.getCode()));
    }
    return snippet;
  }

  public Snippet saveSnippet(Snippet snippet) throws Exception {
    snippet.setCode(encryptionService.encrypt(snippet.getCode()));
    return snippetRepository.save(snippet);
  }

  public List<Snippet> findAllByLanguage(String lang) {
    return snippetRepository.findAllByLanguage(lang);
  }


}
