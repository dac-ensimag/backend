package fr.ensimag.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.ensimag.logic.CommandeServiceLocal;
import fr.ensimag.util.RepeatPaginator;
import fr.ensimag.vo.ArticleVO;
import fr.ensimag.vo.CommandeVO;

@ManagedBean(name = "commandeBean")
@SessionScoped
public class CommandeBean implements Serializable {
	@EJB
	private CommandeServiceLocal commandeService;
	private List<CommandeVO> commandes;
	private List<ArticleVO> articles;
	private RepeatPaginator paginator;
	@ManagedProperty(value = "#{cartBean}")
	private CartBean cartBean;

	public CartBean getCartBean() {
		return cartBean;
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
			articles = new ArrayList<ArticleVO>();
			articles.addAll(cartBean.getArticles());
			commandes = new ArrayList<CommandeVO>();
			this.commandes = this.commandeService.getAllCommands();
		} catch (final Exception e) {
		}
	}

	public Map<CommandeVO, Integer> getCommandeContents() {
		Map<CommandeVO, Integer> commandeContents = new HashMap<CommandeVO, Integer>();
		for (CommandeVO obj : commandes) {
			commandeContents.put(obj, 1);
		}
		return commandeContents;
	}

	public Map<ArticleVO, Integer> getCartContents() {
		Map<ArticleVO, Integer> cartContents = new HashMap<ArticleVO, Integer>();
		for (ArticleVO obj : articles) {
			cartContents.put(obj, 1);
		}
		return cartContents;
	}
}