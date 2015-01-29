package fr.ensimag.logic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.ensimag.dao.CommandeDAOLocal;
import fr.ensimag.entity.Article;
import fr.ensimag.entity.Commande;
import fr.ensimag.vo.ArticleVO;
import fr.ensimag.vo.CommandeVO;

@Stateless
public class CommandeService implements CommandeServiceLocal {

	@EJB
	CommandeDAOLocal commandeDAO;

	ArticleServiceLocal articleService;

	/**
	 * Default constructor.
	 */
	public CommandeService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CommandeVO> getAllCommands() throws Exception {
		List<Commande> commandes = commandeDAO.findAll();
		return Commande.toVo(commandes);

	}

	public CommandeVO addCommande(CommandeVO vo) throws Exception {
		Commande entity = new Commande();
		List<Article> listeArticles = new ArrayList<Article>();
		for (ArticleVO articlevo : vo.getArticleList()) {
			listeArticles.add(articleService.getArticleEntity(articlevo
					.getArticleId()));
		}
		entity.setArticleList(listeArticles);

		entity = commandeDAO.create(entity);

		return entity.toVO();
	}

}
