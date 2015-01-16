package fr.ensimag.dao;

import fr.ensimag.entity.Article;

import javax.ejb.Local;

@Local
public interface ArticleDAOLocal extends AbstractLocal<Article> {

}
