package org.unicome.sample.ddd.article.domain.model;

import org.unicome.sample.ddd.annotation.Aggregate;

@Aggregate
public class Article {
    private int id;
    private String remark;
}
