package com.erdemsidal.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableRequest {

    private int pageNumber;

    private int pageSize;

    private String columnName;

    private boolean asc;

}
