package fr.ensimag.dao;

import fr.ensimag.entity.Article;
import fr.ensimag.foundation.INames;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class ArticleDAO extends AbstractDAO<Article> implements ArticleDAOLocal {

	@PersistenceContext(unitName = INames.PU_NAME)
	private EntityManager em;

	@Resource
	private EJBContext ctx;

	public ArticleDAO() {
		super(Article.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	protected UserTransaction getUserTransaction() {
		return ctx.getUserTransaction();
	}
}
