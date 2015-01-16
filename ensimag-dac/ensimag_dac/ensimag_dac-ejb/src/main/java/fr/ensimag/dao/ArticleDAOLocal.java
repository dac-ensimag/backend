package fr.ensimag.dao;

import fr.ensimag.entity.Article;
import javax.ejb.Local;

/**
 *
 * @author dac
 */
@Local
public interface ArticleDAOLocal extends AbstractLocal<Article> {
    
}
