package com.example.taskcreator.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pagination {
    private Long totalData;
    private int totalPages;
    private int currentPage;

    public Pagination(){}

    public Pagination(Long totalData, int totalPages, int currentPage){
        this.totalData = totalData;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }
}
