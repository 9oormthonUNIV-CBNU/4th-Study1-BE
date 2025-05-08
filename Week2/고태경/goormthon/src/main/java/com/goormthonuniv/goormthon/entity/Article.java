package com.goormthonuniv.goormthon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
@Getter
@AllArgsConstructor
@ToString
@Entity
public class Article {

    public Article() {
        //@AllArgsConstructor는 모든 필드를 매개변수로 받는 생성자만 자동 생성해 주기 때문에,
        //기본 생성자는 자동으로 만들어지지 않음 -> 명시적으로 만들어 줘야함.
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        if(article.title != null) {
            this.title = article.title;
        }
        if(article.content != null) {
            this.content = article.content;
        }
    }
}