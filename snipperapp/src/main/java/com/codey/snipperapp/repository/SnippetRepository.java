package com.codey.snipperapp.repository;

import com.codey.snipperapp.entity.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnippetRepository extends JpaRepository<Snippet, Long> {
}
