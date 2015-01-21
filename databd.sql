/*=========================================================*/
/*             Peuplement BDD - Données de test            */
/*=========================================================*/

/*
Notes :
Login Admin : admin / admin
Login Fournisseur : imag / imag
Login User : user / user (ou énormément d'autres mais ils ont été générés en random)

Articles : 500
Users : 50 + 3 avec chacun une commande.
Factures : 0 (elles doivent être générés après quelques calculs à partir de Commande et CommandeArticle. Cela n'a aucun intérêt d'en mettre de base.)
Commandes : 200 articles aléatoires ont été répartis aléatoirement entre les 53 commandes existantes.
*/

USE ENSIMAG_DAC_DB;

/*Rôles des utilisateurs*/
INSERT INTO ROLE VALUES ('1', "Administrateur","Role principal, possédant tous les droits. Ce compte est principalement présent pour la maintenance du système.");

INSERT INTO ROLE VALUES ('2', "Fournisseur","Role des personnes pouvant ajouter de nouveaux produits sur le site.");

INSERT INTO ROLE VALUES ('3', "Utilisateur","Utilisateur enregistré.");

/*Catégories*/
INSERT INTO CATEGORIE VALUES('1', "Autres");

INSERT INTO CATEGORIE VALUES('2', "Programmes");

INSERT INTO CATEGORIE VALUES('3', "Librairies Logicielles");

/*Utilisateurs*/
INSERT INTO UTILISATEUR (`UTILISATEUR_MAIL`,`UTILISATEUR_LOGIN`,`UTILISATEUR_PASS`,`UTILISATEUR_NOM`,`UTILISATEUR_PRENOM`,`UTILISATEUR_TEL`,`UTILISATEUR_CP`,`UTILISATEUR_ADRESSE`,`ROLE_ID`) VALUES ('admin@admin.com','admin','admin','Robert','Plancton','575-811-6650','NW46 6FP','87393  Gambia Ln.',1);

INSERT INTO UTILISATEUR (`UTILISATEUR_MAIL`,`UTILISATEUR_LOGIN`,`UTILISATEUR_PASS`,`UTILISATEUR_NOM`,`UTILISATEUR_PRENOM`,`UTILISATEUR_TEL`,`UTILISATEUR_CP`,`UTILISATEUR_ADRESSE`,`ROLE_ID`) VALUES ('imag@ensimag.fr','imag','imag','IT','Engy','575-811-6650','NW46 6FP','87393  Gambia Ln.',2);

INSERT INTO UTILISATEUR (`UTILISATEUR_MAIL`,`UTILISATEUR_LOGIN`,`UTILISATEUR_PASS`,`UTILISATEUR_NOM`,`UTILISATEUR_PRENOM`,`UTILISATEUR_TEL`,`UTILISATEUR_CP`,`UTILISATEUR_ADRESSE`,`ROLE_ID`) VALUES ('user@random.com','user','user','Roger','Dupont','579-812-6670','NW76 6FP','87593  Fournier Str.',3);

