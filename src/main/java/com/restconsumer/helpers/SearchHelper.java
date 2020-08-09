package com.restconsumer.helpers;

import com.restconsumer.models.Item;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;

public class SearchHelper {

    public static Specification<Item> containsText(final String text) {
        return (Specification<Item>) (root, query, builder) -> {

            String finalText = text;

            if (!text.contains("%")) {
                finalText = "%" + text + "%";
            }
            return getBuilder(builder).or(
                    getBuilder(builder).like(root.get("author"), finalText),
                    getBuilder(builder).like(root.get("camera"), finalText),
                    getBuilder(builder).like(root.get("tags"), finalText)
            );
        };
    }

    private static CriteriaBuilder getBuilder(CriteriaBuilder builder) {
        return builder;
    }

}
