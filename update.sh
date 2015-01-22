#!/bin/sh

rm PEUPLEMENT.sql;

cat databd.sql datagenutil.sql datagenarticle.sql datagencommande.sql datagenarticlecom.sql > PEUPLEMENT.sql

sudo mysql ENSIMAG_DAC_DB < script.sql
sudo mysql ENSIMAG_DAC_DB < PEUPLEMENT.sql
