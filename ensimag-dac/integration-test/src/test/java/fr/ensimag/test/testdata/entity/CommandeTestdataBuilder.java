package fr.ensimag.test.testdata.entity;

import fr.ensimag.entity.Article;
import fr.ensimag.entity.Commande;
import fr.ensimag.entity.Utilisateur;
import fr.ensimag.test.testdata.AbstractTestdataBuilder;

import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CommandeTestdataBuilder extends AbstractTestdataBuilder<Commande> {
    private Date withDate;
    private String withDescription;
    private double withTotale;
    private Utilisateur withUtilisateur;

    public CommandeTestdataBuilder() { super(); }

    public CommandeTestdataBuilder(EntityManager entityManager, UserTransaction userTransaction) {
        super(entityManager, userTransaction);
    }

    public CommandeTestdataBuilder withDate(Date date) {
        this.withDate = date;
        return this;
    }

    public CommandeTestdataBuilder withDescription(String description) {
        this.withDescription = description;
        return this;
    }

    public CommandeTestdataBuilder withTotale(double total) {
        this.withTotale = total;
        return this;
    }

    public CommandeTestdataBuilder withUtilisateur(Utilisateur utilisateur) {
        this.withUtilisateur = utilisateur;
        return this;
    }

    public Date getDate() {
        if (withDate != null) {
            return withDate;
        }

        Calendar c = new GregorianCalendar();
        c.set(Calendar.HOUR_OF_DAY, 0); //anything 0 - 23
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        Date date = c.getTime();
        return date;
    }

    public String getDescription() {
        return withDescription != null ? withDescription : "Une super commande";
    }

    public double getTotale() {
        return withTotale;
    }

    public Utilisateur getUtilisateur() {
        if (withUtilisateur != null) {
            return withUtilisateur;
        }

        return hasEntityManager() ? new UtilisateurTestdataBuilder(getEntityManager(), getUserTransaction()).buildAndSave() : new UtilisateurTestdataBuilder().build();
    }

    @Override
    public Commande build() {
        final Commande commande = new Commande();
        commande.setUtilisateur(getUtilisateur());
        commande.setArticleList(new ArrayList<Article>());
        commande.setCommandeDate(getDate());
        commande.setCommandeDescription(getDescription());
        commande.setCommandeTotale(getTotale());
        commande.setFacture(null);

        return commande;
    }
}
