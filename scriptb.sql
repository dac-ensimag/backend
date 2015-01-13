/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de création :  13/01/2015 10:40:17                      */
/*==============================================================*/


drop table if exists ARTICLE;

drop table if exists ARTICLECOMMANDE;

drop table if exists CATEGORIE;

drop table if exists COMMANDE;

drop table if exists FACTURE;

drop table if exists ROLE;

drop table if exists UTILISATEUR;

/*==============================================================*/
/* Table : ARTICLE                                              */
/*==============================================================*/
create table ARTICLE
(
   ARTICLE_ID           int not null,
   CATEGORIE_ID         int not null,
   ARTICLE_LIBELE       text not null,
   ARTICLE_PRIX         float not null,
   ARTICLE_DISPONIBILITE bool not null,
   ARTICLE_DESCRIPTION  text not null,
   primary key (ARTICLE_ID)
);

/*==============================================================*/
/* Table : ARTICLECOMMANDE                                      */
/*==============================================================*/
create table ARTICLECOMMANDE
(
   COMMANDE_ID          int,
   ARTICLE_ID           int
);

/*==============================================================*/
/* Table : CATEGORIE                                            */
/*==============================================================*/
create table CATEGORIE
(
   CATEGORIE_ID         int not null,
   CATEGORIE_LIBELE     text not null,
   primary key (CATEGORIE_ID)
);

/*==============================================================*/
/* Table : COMMANDE                                             */
/*==============================================================*/
create table COMMANDE
(
   COMMANDE_ID          int not null,
   UTILISATEUR_ID       int not null,
   FACTURE_ID           int,
   COMMANDE_DATE        date not null,
   COMMANDE_DESCRIPTION text not null,
   COMMANDE_TOTALE      double not null,
   primary key (COMMANDE_ID)
);

/*==============================================================*/
/* Table : FACTURE                                              */
/*==============================================================*/
create table FACTURE
(
   FACTURE_ID           int not null,
   COMMANDE_ID          int not null,
   FACTURE_DATE         date not null,
   FACTURE_PRIXHT       float not null,
   FACTURE_TVA          float not null,
   FACTURE_TOTALHT      float not null,
   FACTURE_TOTALTTC     float not null,
   primary key (FACTURE_ID)
);

/*==============================================================*/
/* Table : ROLE                                                 */
/*==============================================================*/
create table ROLE
(
   ROLE_ID              int not null,
   ROLE_LIBELE          text not null,
   ROLE_DESCRIPTION     text not null,
   primary key (ROLE_ID)
);

/*==============================================================*/
/* Table : UTILISATEUR                                          */
/*==============================================================*/
create table UTILISATEUR
(
   UTILISATEUR_ID       int not null,
   COMMANDE_ID          int,
   ROLE_ID              int not null,
   UTILISATEUR_MAIL     text not null,
   UTILISATEUR_LOGIN    text not null,
   UTILISATEUR_PASS     text not null,
   UTILISATEUR_NOM      text not null,
   UTILISATEUR_PRENOM   text not null,
   UTILISATEUR_TEL      text,
   UTILISATEUR_ADRESSE  text,
   UTILISATEUR_CP       text,
   primary key (UTILISATEUR_ID)
);

alter table ARTICLE add constraint FK_FAIT_PARTIE foreign key (CATEGORIE_ID)
      references CATEGORIE (CATEGORIE_ID) on delete restrict on update restrict;

alter table ARTICLECOMMANDE add constraint FK_RELATION_7 foreign key (ARTICLE_ID)
      references ARTICLE (ARTICLE_ID) on delete restrict on update restrict;

alter table ARTICLECOMMANDE add constraint FK_RELATION_8 foreign key (COMMANDE_ID)
      references COMMANDE (COMMANDE_ID) on delete restrict on update restrict;

alter table COMMANDE add constraint FK_CORRESPOND_A foreign key (FACTURE_ID)
      references FACTURE (FACTURE_ID) on delete restrict on update restrict;

alter table COMMANDE add constraint FK_RELATION_10 foreign key (UTILISATEUR_ID)
      references UTILISATEUR (UTILISATEUR_ID) on delete restrict on update restrict;

alter table FACTURE add constraint FK_CORRESPOND_A2 foreign key (COMMANDE_ID)
      references COMMANDE (COMMANDE_ID) on delete restrict on update restrict;

alter table UTILISATEUR add constraint FK_DETIENT foreign key (ROLE_ID)
      references ROLE (ROLE_ID) on delete restrict on update restrict;

alter table UTILISATEUR add constraint FK_RELATION_9 foreign key (COMMANDE_ID)
      references COMMANDE (COMMANDE_ID) on delete restrict on update restrict;

