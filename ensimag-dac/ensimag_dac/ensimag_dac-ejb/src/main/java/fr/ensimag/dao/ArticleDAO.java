package fr.ensimag.dao;

import fr.ensimag.entity.Article;
import javax.annotation.Resource;
import javax.ejb.EJBContext;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ArticleDAO extends AbstractDAO<Article> implements ArticleDAOLocal {
    
    @Resource
    private EJBContext ctx;
    
    @Override
    protected UserTransaction getUserTransaction() {
        return ctx.getUserTransaction();
    }
    
}
