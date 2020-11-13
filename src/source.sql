
connect 'jdbc:derby:mail;create=true';

CREATE TABLE "APP"."PRIVILAGE" (
		"N_PRIVILAGE" INTEGER primary key,
		"NOM_PRIVILAGE" VARCHAR(20)
);

CREATE TABLE "APP"."MESSAGE" (
	   "SRC" VARCHAR(20),
		"DEST" VARCHAR(20),
		"DATE" TIMESTAMP primary key,
		"OBJECT" VARCHAR(20),
		"MSG"  VARCHAR(10000),
		"ETAT" SMALLINT
	);

CREATE TABLE "APP"."CONFIG" (
		"PARAM_NAME" VARCHAR(20) primary key,
		"PARAM_VALUE" VARCHAR(20)
	);

CREATE TABLE "APP"."CONTACTE" (
		"NOM" VARCHAR(20),
		"PRENOM" VARCHAR(20),
		"MAIL" VARCHAR(20)primary key,
		"PASS" VARCHAR(20),
		"COTA" INTEGER
	);

CREATE TABLE "APP"."ADMIN" (
		"MAIL" VARCHAR(20) primary key,
		"PASS" VARCHAR(20) 
	);

CREATE TABLE "APP"."GROUPE" (
		"N_GROUP" INTEGER primary key,
		"NOM_GROUP" VARCHAR(20)
	);

CREATE TABLE "APP"."DROIT_ACCES" (
		"MAIL" VARCHAR(20),
		"N_GROUP" INTEGER,
		"PRIVILAGE" INTEGER,
	    constraint pk_droi_acces primary key(MAIL,N_GROUP,PRIVILAGE),
	    constraint fk_droi_acces_contacte foreign key(mail) references contacte(MAIL),
	    constraint fk_droi_acces_groupe foreign key(N_GROUP) references groupe(N_GROUP)
	);
	
CREATE TABLE RelationContacte (
		nom VARCHAR(32),
		liste VARCHAR(32)
	);
