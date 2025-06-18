package com.example.taskflow.common.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageResponse<T> {
    private List<T> content;
    private long totalElements;
    private int totalPages;
    private int size;
    private int number;

    public PageResponse(Page<T> page) {
        this.content = page.getContent();
        number = page.getNumber();
        size = page.getSize();
        totalElements = page.getTotalElements();
        totalPages = page.getTotalPages();
    }
}
