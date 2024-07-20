package org.example.controllers;

import org.example.dto.News;
import org.example.service.NewsManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsManagementService newsService;

    public NewsController(NewsManagementService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getNewsById(@PathVariable Long id) { return newsService.getById(id); }

    @GetMapping
    public ResponseEntity getAllNews() {
        return newsService.getAll();
    }

    @PostMapping
    public ResponseEntity createNews(@RequestBody News news) {
        return newsService.create(news);
    }

    @PutMapping
    public ResponseEntity updateNews(@RequestBody News news) { return newsService.update(news); }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNews(@PathVariable Long id) {
        return newsService.delete(id);
    }
}
