package fr.ensimag.entity.testdata;

import de.akquinet.jbosscc.needle.db.testdata.AbstractTestdataBuilder;
import fr.ensimag.entity.Categorie;

import javax.persistence.EntityManager;

public class CategorieTestdataBuilder extends AbstractTestdataBuilder<Categorie> {

	private String withLibele;

	public CategorieTestdataBuilder() {
		super();
	}

	public CategorieTestdataBuilder(EntityManager entityManager) {
		super(entityManager);
	}

	public CategorieTestdataBuilder withLibele(String libele) {
		this.withLibele = libele;
		return this;
	}

	private String getCategoriLibele() {
		return withLibele != null ? withLibele : "utilitaire";
	}

	@Override
	public Categorie build() {
		final Categorie categorie = new Categorie();
		categorie.setCategorieLibele(getCategoriLibele());

		return categorie;
	}
}
