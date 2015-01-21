#!/bin/sh

rm PEUPLEMENT.sql;

cat databd.sql datagenutil.sql datagenarticle.sql datagencommande.sql datagenarticlecom.sql > PEUPLEMENT.sql
