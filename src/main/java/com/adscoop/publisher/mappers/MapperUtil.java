package com.adscoop.publisher.mappers;

import com.adscoop.entiites.Category;

/**
 * Created by thokle on 29/11/2016.
 */
public class MapperUtil {



    public static Category mapToCategory(String name, String value){


        Category category = new Category();

        category.setValue(value);
        category.setName(name);
        return category;

    }
}
