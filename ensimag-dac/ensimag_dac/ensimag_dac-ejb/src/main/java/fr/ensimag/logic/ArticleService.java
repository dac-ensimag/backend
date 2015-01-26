package fr.ensimag.logic;

import fr.ensimag.dao.ArticleDAOLocal;
import fr.ensimag.vo.ArticleVO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

@Stateless
public class ArticleService implements ArticleServiceLocal {

	@EJB
	ArticleDAOLocal articleDAO;

	public String getError() {
		return error;
	}

	private String error;

	@Override
	public void deleteArticle(Integer articleId) {
		try {
			articleDAO.remove(articleDAO.find(articleId));
		} catch (Exception e) {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			final PrintStream ps = new PrintStream(baos);
			e.printStackTrace(ps);
			try {
				this.error = baos.toString("UTF-8");
			} catch (final UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public ArticleVO getArticle(Integer articleId) throws Exception {
		return articleDAO.find(articleId).toVO();
	}

}
