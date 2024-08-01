package com.j0k3r.andreanamaste.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PageableUtils {

    public static Pageable createPageable(Integer page, Integer size, String direction, List<String> sorts,String defaultSort){
        if (sorts == null)
            sorts = List.of(defaultSort);

        return PageRequest.of(
                page == null ? 1 : page,
                size == null ? 10 : size,
                Sort.by(Sort.Direction.fromString(
                                direction != null ? direction : "ASC"),
                        String.valueOf(sorts).replace("[","").replace("]","").replaceAll(" ","").split(",")
                )
        );
    }

}
