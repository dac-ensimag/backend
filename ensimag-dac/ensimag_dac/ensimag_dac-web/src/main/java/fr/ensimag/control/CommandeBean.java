package fr.ensimag.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.ensimag.logic.CommandeServiceLocal;
import fr.ensimag.logic.UtilisateurServiceLocal;
import fr.ensimag.vo.ArticleVO;
import fr.ensimag.vo.CommandeVO;
import fr.ensimag.vo.UtilisateurVO;

@ManagedBean(name = "commandeBean")
@SessionScoped
public class CommandeBean implements Serializable {
	@EJB
	private CommandeServiceLocal commandeService;
	private List<CommandeVO> commandes;
	private List<ArticleVO> articles = new ArrayList<ArticleVO>();
	private List<ArticleVO> articlesLinks = new ArrayList<ArticleVO>();
	private CommandeVO cmd;

	@EJB
	private UtilisateurServiceLocal utilisateurService;

	private UtilisateurVO utilisateurCourant;

	@ManagedProperty(value = "#{utilisateurBean}")
	private UtilisateurBean utilisateurBean;

	public UtilisateurBean getUtilisateurBean() {
		return this.utilisateurBean;
	}

	public void setUtilisateurBean(UtilisateurBean utilisateurBean) {
		this.utilisateurBean = utilisateurBean;
	}

	public CommandeVO getCmd() {
		return this.cmd;
	}

	public void setCmd(CommandeVO cmd) {
		this.cmd = cmd;
	}

	@ManagedProperty(value = "#{cartBean}")
	private CartBean cartBean;

	public CartBean getCartBean() {
		return this.cartBean;
	}

	public void setCartBean(CartBean cartBean) {
		this.cartBean = cartBean;
	}

	/**
	 * Creates a new instance of cart
	 */
	public CommandeBean() {
	}

	@PostConstruct
	public void init() {
		try {
			this.commandes = new ArrayList<CommandeVO>();
			this.commandes = this.commandeService.getAllCommands();

		} catch (Exception e) {
		}
	}

	public Map<CommandeVO, Integer> getCommandeContents() {
		Map<CommandeVO, Integer> commandeContents = new HashMap<CommandeVO, Integer>();
		for (CommandeVO obj : this.commandes) {
			commandeContents.put(obj, 1);
		}
		return commandeContents;
	}

	public Map<CommandeVO, Integer> getUserCommandes() {
		this.utilisateurCourant = new UtilisateurVO();
		this.utilisateurCourant = this.utilisateurBean.getUser();
		Map<CommandeVO, Integer> commandeContents = new HashMap<CommandeVO, Integer>();
		// List<CommandeVO> userCmdList;
		// userCmdList = this.utilisateurCourant.getCommandeList();
		for (CommandeVO obj : commandes) {
			if (obj.getUtilisateurId() == utilisateurCourant.getUtilisateurId()) {
				commandeContents.put(obj, 1);
			}
		}
		return commandeContents;
	}

	public Map<ArticleVO, Integer> getCartContents() {
		Map<ArticleVO, Integer> cartContents = new HashMap<ArticleVO, Integer>();
		for (ArticleVO obj : this.articles) {
			cartContents.put(obj, 1);
		}
		return cartContents;
	}

	public Map<ArticleVO, Integer> getDownloadLinks() {

		  Map<ArticleVO, Integer> cartContents = new HashMap<>();
		for (  ArticleVO obj : this.articlesLinks) {
			if (cartContents.containsKey(obj)) {
				cartContents.put(obj, cartContents.get(obj) + 1);
			} else {
				cartContents.put(obj, 1);
			}
		}
		return cartContents;
	}

	public String confirmCommand() throws Exception {

		if (this.utilisateurBean.isLoggedIn()) {
			/*
			 * CommandeVO success = null;
			 * 
			 * success = this.commandeService.addCommande(this.getCmd()); if
			 * (success != null) { return "index"; } else { return "error"; }
			 */
			this.cmd = new CommandeVO();
			this.articles = this.cartBean.getArticles();
			this.cmd.setCommandeDate(new Date());
			this.cmd.setArticleList(this.articles);
			this.cmd.setCommandeDescription("description");
			this.cmd.setCommandeTotale(223);

			this.cmd.setUtilisateurId(this.utilisateurBean.getUser()
					.getUtilisateurId());

			CommandeVO res = null;
			res = this.commandeService.addCommande(this.cmd);
			this.cmd = new CommandeVO();
			if (res != null) {
				this.articlesLinks.clear();
				this.articlesLinks.addAll(this.cartBean.getArticles());
				this.cartBean.clear();
				return "/confirmation";
			} else {
				return "/index?faces-redirect=true";
			}
		} else {
			return "/index?faces-redirect=true";
		}

	}

}
