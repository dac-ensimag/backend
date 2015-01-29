package fr.ensimag.dao;

import fr.ensimag.entity.Article;

import javax.ejb.Stateless;

@Stateless
public class ArticleDAO extends AbstractDAO<Article> implements ArticleDAOLocal {
}
