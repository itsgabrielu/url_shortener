package com.example.restservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.model.Url;
import com.example.restservice.repository.UrlRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class UrlController {

  @Autowired
  UrlRepository urlRepository;

//   @GetMapping("/urls")
//   public ResponseEntity<List<Url>> getAllUrls(@RequestParam(required = false) String hash) {
//     try {
//       List<Url> urls = new ArrayList<Url>();

//       if (hash == null)
//         urlRepository.findAll().forEach(urls::add);
//       else
//         urlRepository.findAllByHash(hash).forEach(urls::add);

//       if (urls.isEmpty()) {
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//       }
//       return new ResponseEntity<>(urls, HttpStatus.OK);
//     } catch (Exception e) {
//       return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//     }
//   }

  @GetMapping("/urls/{hash}")
  public ResponseEntity<Url> getUrlById(@PathVariable("hash") String hash) {
    List<Url> urls = urlRepository.findAllByHash(hash);
    if (urls.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } else {
        Url _url = urls.get(0);
        HttpHeaders responseHeaders = new HttpHeaders();
        try {
            URI location = new URI(_url.getActual());
            responseHeaders.setLocation(location);
            responseHeaders.set("MyResponseHeader", "MyValue");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(_url, responseHeaders, HttpStatus.FOUND);
    }
  }

  @PostMapping("/urls")
  public ResponseEntity<Url> createUrl(@RequestBody Url url) {
      try {
          String randHash = UUID.randomUUID().toString().substring(0, 7);
          Url _url = urlRepository.save(new Url(randHash, url.getActual()));
          return new ResponseEntity<>(_url, HttpStatus.CREATED);
      } catch (Exception e) {
          return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
      }
  }

//   @PutMapping("/urls/{id}")
//   public ResponseEntity<Url> updateUrl(@PathVariable("id") long id, @RequestBody Url url) {
//     Optional<Url> urlData = urlRepository.findById(id);

//     if (urlData.isPresent()) {
//       Url _url = urlData.get();
      
//       _url.setHash(url.getHash());
//       _url.setActual(url.getActual());
//       return new ResponseEntity<>(urlRepository.save(_url), HttpStatus.OK);
//     } else {
//       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//     }
//   }

//   @DeleteMapping("/urls/{id}")
//   public ResponseEntity<HttpStatus> deleteUrl(@PathVariable("id") long id) {
//     try {
//       urlRepository.deleteById(id);
//       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     } catch (Exception e) {
//       return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//     }
//   }

//   @DeleteMapping("/urls")
//   public ResponseEntity<HttpStatus> deleteAllUrls() {
//     try {
//       urlRepository.deleteAll();
//       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     } catch (Exception e) {
//       return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//     }
//   }

}