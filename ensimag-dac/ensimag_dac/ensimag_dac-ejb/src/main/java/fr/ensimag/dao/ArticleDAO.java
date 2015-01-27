package fr.ensimag.dao;

import fr.ensimag.entity.Article;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ArticleDAO extends AbstractDAO<Article> implements ArticleDAOLocal {
}
