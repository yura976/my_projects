package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CategoryDto;
import org.example.dto.NewsDto;
import org.example.entity.Category;
import org.example.entity.News;
import org.example.repositories.CategoryRepository;
import org.example.repositories.NewsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.function.Supplier;

@RequiredArgsConstructor
@Service
@Slf4j
public class NewsService implements CRUDService<NewsDto>{

    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    private HashMap<String, String> message = new HashMap<>();
    @Override
    public ResponseEntity getById(Long id) {
        log.info("Get news by ID " + id);
        return Optional.ofNullable(newsRepository.findById(id)
                .map(news -> new ResponseEntity(mapToDto(news), HttpStatus.OK)))
                .get()
                .orElse(new ResponseEntity(getMessage(id), HttpStatus.NOT_FOUND));
    }
    @Override
    public ResponseEntity getAll() {
        log.info("Get all news");
        Collection<NewsDto> newsDto = newsRepository.findAll()
                .stream()
                .map(news -> mapToDto(news))
                .toList();
        return new ResponseEntity(newsDto, HttpStatus.OK);
    }
    @Override
    public ResponseEntity create(NewsDto newsDto) {
        log.info("Create news");
        News news = mapToEntity(newsDto);
        news.setCategory(getCategory(newsDto));
        newsRepository.save(news);
        return new ResponseEntity(mapToDto(news), HttpStatus.CREATED);
    }

    public ResponseEntity getNewsByCategory(Long id) {
        log.info("Get news by category");
        Collection<NewsDto> newsDto = newsRepository.findAll()
                .stream()
                .filter(news -> news.getCategory().getId() == id)
                .map(news -> mapToDto(news))
                .toList();
        return new ResponseEntity(newsDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity update(NewsDto newsDto) {
        log.info("Update news");
        News news = mapToEntity(newsDto);
        news.setCategory(getCategory(newsDto));
        news.setDate(Instant.now());
        newsRepository.save(news);
        return new ResponseEntity(mapToDto(news), HttpStatus.OK);
    }

    @Override
    public ResponseEntity delete(Long id) {
        log.info("Delete news by ID " + id);
        return Optional.ofNullable(newsRepository.findById(id)
                        .map(news -> {
                            newsRepository.delete(news);
                            return new ResponseEntity(HttpStatus.NO_CONTENT);
                        }))
                .get()
                .orElse(new ResponseEntity(getMessage(id), HttpStatus.NOT_FOUND));
    }

    public static NewsDto mapToDto(News news) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setTitle(news.getTitle());
        newsDto.setText(news.getText());
        newsDto.setDate(news.getDate());
        newsDto.setCategory(news.getCategory().getTitle());
        return newsDto;
    }

    public static News mapToEntity(NewsDto newsDto) {
        News news = new News();
        news.setId(newsDto.getId());
        news.setTitle(newsDto.getTitle());
        news.setText(newsDto.getText());
        news.setDate(newsDto.getDate());
        return news;
    }
    private HashMap<String, String> getMessage(Long id) {
        message.put("message", String.format("Новость с ID %d не найдена.", id));
        return message;
    }

    private Category getCategory(NewsDto newsDto) {
        String categoryTitle = newsDto.getCategory();
        return categoryRepository.findByTitle(categoryTitle).orElseGet(() -> {
            CategoryDto categoryDto = new CategoryDto(categoryTitle);
            Category category = CategoryService.mapToEntity(categoryDto);
            categoryRepository.save(category);
            return category;
        });
    }
}
