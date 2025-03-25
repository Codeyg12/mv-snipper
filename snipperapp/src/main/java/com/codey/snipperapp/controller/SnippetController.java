package com.codey.snipperapp.controller;

import com.codey.snipperapp.entity.Snippet;
import com.codey.snipperapp.service.SnippetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/snippets")
public class SnippetController {

  private final SnippetService snippetService;

  public SnippetController(SnippetService snippetService) {
    this.snippetService = snippetService;
  }

  @GetMapping
  public ResponseEntity<List<Snippet>> getSnippets() {
    return ResponseEntity.ok(snippetService.getAllSnippets());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Snippet> getSnippetById(@PathVariable Long id) {
    return ResponseEntity.ok(snippetService.getSnippetById(id));
  }

  @PostMapping
  public ResponseEntity<Snippet> createSnippet(@RequestBody Snippet snippet) {
    return new ResponseEntity<>(snippetService.saveSnippet(snippet), HttpStatus.CREATED);
  }
}
