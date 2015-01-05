package fr.ensimag;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Debug {
	public static String defaultFile = "DAC_log.txt";

	public static void w(String fileName, String message) {
		String adressedufichier = System.getProperty("user.dir") + "/"+ fileName;

		//on met try si jamais il y a une exception
		try
		{
			/**
			 * BufferedWriter a besoin d un FileWriter, 
			 * les 2 vont ensemble, on donne comme argument le nom du fichier
			 * true signifie qu on ajoute dans le fichier (append), on ne marque pas par dessus 

			 */
			FileWriter fw = new FileWriter(adressedufichier, true);

			// le BufferedWriter output auquel on donne comme argument le FileWriter fw cree juste au dessus
			BufferedWriter output = new BufferedWriter(fw);

			//on marque dans le fichier ou plutot dans le BufferedWriter qui sert comme un tampon(stream)
			output.write(message);
			//on peut utiliser plusieurs fois methode write

			output.flush();
			//ensuite flush envoie dans le fichier, ne pas oublier cette methode pour le BufferedWriter

			output.close();
			//et on le ferme
		}
		catch(Exception e){
		}
	}

	public static void w(String message) {
		Debug.w(Debug.defaultFile, message);
	}
}

