package fr.ensimag.logic;

import fr.ensimag.dao.ArticleDAOLocal;
import fr.ensimag.entity.Article;
import fr.ensimag.vo.ArticleVO;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class CatalogService implements CatalogServiceLocal {

	@EJB
	ArticleDAOLocal articleDAO;

	@Override
	public List<ArticleVO> getAllProducts() throws Exception {
		try {
			List<Article> articles = articleDAO.findAll();
			return Article.toVo(articles);
		} catch (Exception e) {
			throw new Exception();
		}
	}

/*@EJB
	AccountDAOLocal accountDBAcces;

	@Override
	public void register(AccountVO accountVO) {
		Account account = new Account();
		account.setUsername(accountVO.getUsername());
		account.setPassword(accountVO.getPassword());
		try {
			accountDBAcces.create(account);
		} catch (Exception e) {
			//TODO
			e.printStackTrace();
		}
	}

	@Override
	public AccountVO login(AccountVO accountVO) {
		Account account = new Account();
		account.setUsername(accountVO.getUsername());
		account.setPassword(accountVO.getPassword());
		try {
			Account login = accountDBAcces.find(account.getUsername());
			if (login != null && login.getPassword().equals(account.getPassword())) {
				return login.toVO();
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}*/

}
