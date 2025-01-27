package com.erdemsidal.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class PageableEntity<T> {

    private List<T> content;

    private int pageNumber;

    private int pageSize;

    private Long totalElements;

}
