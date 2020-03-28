package com.example.restservice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restservice.model.Url;

public interface UrlRepository extends JpaRepository<Url, Long> {
  List<Url> findAllByHash(String hash);
}