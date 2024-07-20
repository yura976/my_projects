package org.example.controllers;

import org.example.dto.NewsDto;
import org.example.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getNewsById(@PathVariable Long id) { return newsService.getById(id); }

    @GetMapping
    public ResponseEntity getAllNews() {
        return newsService.getAll();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity getNewsByCategory(@PathVariable Long id) { return newsService.getNewsByCategory(id); }

    @PostMapping
    public ResponseEntity createNews(@RequestBody NewsDto news) {
        return newsService.create(news);
    }

    @PutMapping
    public ResponseEntity updateNews(@RequestBody NewsDto news) { return newsService.update(news); }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNews(@PathVariable Long id) {
        return newsService.delete(id);
    }
}
