#!/bin/bash
#Ce script rend administrateur le login passé en paramètre.
if [ $# -ne 1 ]
then
	echo "Ce script nécessite un (unique) login en paramètre."
	exit
fi

echo "UPDATE UTILISATEUR SET ROLE_ID = 1 WHERE UTILISATEUR_LOGIN='"$1"';" > toto.sql
sudo mysql ENSIMAG_DAC_DB < toto.sql
rm toto.sql
