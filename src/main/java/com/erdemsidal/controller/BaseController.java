package com.erdemsidal.controller;


import com.erdemsidal.utils.PageableEntity;
import com.erdemsidal.utils.PageableRequest;
import com.erdemsidal.utils.PagerUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BaseController {

    public <T> RootEntity<T> ok(T payload){
       return RootEntity.ok(payload);
    }


    public <T> RootEntity<T> error(String errorMessage){
        return RootEntity.error(errorMessage);
    }

    public Pageable toPageable(PageableRequest request){
        return PagerUtil.toPageable(request);
    }

    public <T> PageableEntity<T> toPageableResponse(Page<?> page, List<T> content){
        return PagerUtil.toPageableResponse(page,content);
    }





}
