package fr.ensimag.logic;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.ensimag.dao.CommandeDAOLocal;
import fr.ensimag.entity.Commande;
import fr.ensimag.vo.CommandeVO;

@Stateless
public class CommandeService implements CommandeServiceLocal {

	@EJB
	CommandeDAOLocal commandeDAO;

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

}
