# Guide d'installation pour docker

## Docker avec serveur applicatif Wildfly

Utilisez ce docker pour vos tests en local !

Basé sur 

> https://www.jetbrains.com/help/idea/2021.3/deploying-a-web-app-into-wildfly-container.html#13b6589

0. Il faut ajouter l'extension Docker à IntellJ (Settings > rechercher Docker > ... )

1. Générer le fichier .war (dans target) et mettre une copie de celui-ci dans `docker-dir/docker_serveur_applicatif`

2. Ouvrir le fichier Dockerfile, clic sur les fleches vertes et Edit Run configuration

3. Paramètre :

> Container name : WildflyIntel
> Modify > Bind ports > 8080 | 8080 | 127.0.0.1
> Apply 
> Run

4. Pour consulter le site :

> si file.war = shop_els.war

> site accessible à : http://127.0.0.1:8080/shop_els/

## Docker avec MySQL et phpmyadmin

Docker afin de  visualiser et lancer la base de données pour la connexion code-base de données.

1. Aller dans le répertoire `docker.dir` du projet à la racine
2. Lancer le script `start_docker.sh`
3. Aller sur `localhost:6060` 
4. Entrer les credentials: root/root

Vous pouvez donc visualiser la base de données et toutes les données.

!!!! si vous avez besoin de modifier la base de données, aller dans le répertoire `mysql/database`

