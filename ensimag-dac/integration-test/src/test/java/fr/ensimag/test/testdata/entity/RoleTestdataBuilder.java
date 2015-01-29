package fr.ensimag.test.testdata.entity;

import fr.ensimag.entity.Role;
import fr.ensimag.test.testdata.AbstractTestdataBuilder;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

public class RoleTestdataBuilder extends AbstractTestdataBuilder<Role> {

	private String withLibele;
	private String withDescription;

	public RoleTestdataBuilder() {
		super();
	}

	public RoleTestdataBuilder(EntityManager entityManager, UserTransaction userTransaction) {
		super(entityManager, userTransaction);
	}

	public RoleTestdataBuilder withLibele(String libele) {
		this.withLibele = libele;
		return this;
	}

	public RoleTestdataBuilder withDescription(String description) {
		this.withDescription = description;
		return this;
	}

	private String getRoleLibele() {
		return withLibele != null ? withLibele : "guest";
	}

	private String getRoleDescription() {
		return withDescription != null ? withDescription : "description";
	}

	@Override
	public Role build() {
		final Role role = new Role();
		role.setRoleDescription(getRoleDescription());
		role.setRoleLibele(getRoleLibele());

		return role;
	}
}
