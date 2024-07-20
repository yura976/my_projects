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

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j

public class CategoryService implements CRUDService<CategoryDto>{

    private final CategoryRepository categoryRepository;
    private HashMap<String, String> message = new HashMap<>();
    @Override
    public ResponseEntity getById(Long id) {
        log.info("Get category by ID " + id);
        return Optional.ofNullable(categoryRepository.findById(id)
                        .map(category -> new ResponseEntity(mapToDto(category), HttpStatus.OK)))
                .get()
                .orElse(new ResponseEntity(getMessage(id), HttpStatus.NOT_FOUND));
    }
    @Override
    public ResponseEntity getAll() {
        log.info("Get all categories");
        Collection<CategoryDto> categoryDto = categoryRepository.findAll()
                .stream()
                .map(CategoryService::mapToDto)
                .toList();
        return new ResponseEntity(categoryDto, HttpStatus.OK);
    }
    @Override
    public ResponseEntity create(CategoryDto categoryDto) {
        log.info("Create category");
        Category category = mapToEntity(categoryDto);
        categoryRepository.save(category);
        return new ResponseEntity(mapToDto(category), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity update(CategoryDto categoryDto) {
        log.info("Update category");
        Category category = mapToEntity(categoryDto);
        categoryRepository.save(category);
        return new ResponseEntity(mapToDto(category), HttpStatus.OK);
    }

    @Override
    public ResponseEntity delete(Long id) {
        log.info("Delete category by ID " + id);
        return Optional.ofNullable(categoryRepository.findById(id)
                        .map(category -> {
                            categoryRepository.delete(category);
                            return new ResponseEntity(HttpStatus.NO_CONTENT);
                        }))
                .get()
                .orElse(new ResponseEntity(getMessage(id), HttpStatus.NOT_FOUND));
    }

    public static CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setNews(category.getNewsList()
                .stream()
                .map(NewsService::mapToDto)
                .toList());
        return categoryDto;
    }

    public static Category mapToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setTitle(categoryDto.getTitle());
        List<NewsDto> newsDto = Optional.ofNullable(categoryDto.getNews()).orElse(new ArrayList<>());
        category.setNewsList(newsDto
                .stream()
                .map(NewsService::mapToEntity)
                .toList());
        return category;
    }

    private HashMap<String, String> getMessage(Long id) {
        message.put("message", String.format("Категория с ID %d не найдена.", id));
        return message;
    }
}
