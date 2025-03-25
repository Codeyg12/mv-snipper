package com.codey.snipperapp.repository;

import com.codey.snipperapp.entity.Snippet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class SnippetSeeder implements CommandLineRunner {

  private final SnippetRepository snippetRepository;

  public SnippetSeeder(SnippetRepository snippetRepository) {
    this.snippetRepository = snippetRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    ObjectMapper mapper = new ObjectMapper();

    File jsonFile = new File("snipperapp/src/main/resources/snippetSeedData.json");


    List<Snippet> snippets = mapper.readValue(jsonFile, new TypeReference<List<Snippet>>() {
    });

    snippetRepository.saveAll(snippets);

    System.out.println("Snippet seed data loaded!");
  }
}
