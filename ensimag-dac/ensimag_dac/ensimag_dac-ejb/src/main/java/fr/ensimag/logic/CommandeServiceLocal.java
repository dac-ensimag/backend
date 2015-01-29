package fr.ensimag.logic;

import java.util.List;

import javax.ejb.Local;

import fr.ensimag.vo.CommandeVO;

@Local
public interface CommandeServiceLocal {

	List<CommandeVO> getAllCommands() throws Exception;

	CommandeVO addCommande(CommandeVO vo) throws Exception;

}