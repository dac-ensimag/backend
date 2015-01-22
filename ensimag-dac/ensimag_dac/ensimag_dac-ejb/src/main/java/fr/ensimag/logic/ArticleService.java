package fr.ensimag.logic;

import fr.ensimag.dao.ArticleDAOLocal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ArticleService implements ArticleServiceLocal {

	@EJB
	ArticleDAOLocal articleDAO;

	@Override
	public void deleteArticle(Integer articleId) {
		try {
			articleDAO.remove(articleDAO.find(articleId));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*@Override
	public List<ArticleVO> getAllProducts() throws Exception {
		try {
			List<Article> articles = articleDAO.findAll();
			return Article.toVo(articles);
		} catch (Exception e) {
			throw new Exception();
		}
	}
*/
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
