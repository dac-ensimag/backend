package fr.ensimag.logic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.ensimag.dao.ArticleDAOLocal;
import fr.ensimag.dao.CommandeDAOLocal;
import fr.ensimag.dao.UtilisateurDAOLocal;
import fr.ensimag.entity.Article;
import fr.ensimag.entity.Commande;
import fr.ensimag.vo.ArticleVO;
import fr.ensimag.vo.CommandeVO;

@Stateless
public class CommandeService implements CommandeServiceLocal {

	@EJB
	CommandeDAOLocal commandeDAO;

	@EJB
	ArticleDAOLocal articleDAO;

	@EJB
	UtilisateurDAOLocal utilisateurDAO;

	@Override
	public List<CommandeVO> getAllCommands() throws Exception {
		List<Commande> commandes = commandeDAO.findAll();
		return Commande.toVo(commandes);

	}

	@Override
	public CommandeVO getCommande(Integer commandeId) {
		Commande commande = commandeDAO.find(commandeId);
		if (commande != null) {
			return commande.toVO();
		}

		return null;
	}

	public CommandeVO addCommande(CommandeVO vo)  {
		Commande entity = new Commande();
		List<Article> listeArticles = new ArrayList<>();

		for (ArticleVO articlevo : vo.getArticleList()) {
			listeArticles.add(articleDAO.find(articlevo.getArticleId()));
		}

		entity.setArticleList(listeArticles);
		entity.setCommandeDate(vo.getCommandeDate());
		entity.setUtilisateur(utilisateurDAO.find(vo.getUtilisateurId()));
		entity.setCommandeDescription(vo.getCommandeDescription());
		entity.setCommandeTotale(vo.getCommandeTotale());
		try {
		entity = commandeDAO.create(entity);
		return entity.toVO();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
