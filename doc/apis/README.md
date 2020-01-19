#AMT - Project Two

Le projet se compose de deux APIs Rest, l'une de gestion d'utilisateurs, l'autre permettant de consulter des films et de leur attribuer une note et une description. Chacune des APIs dispose de sa propre base de donnée et une topologie docker permet d'automatiser le lancement des backends, de leurs bases de données ainsi que d'un container *traefik*, agissant comme reverse-proxy pour les deux backends. Des procédures de tests automatisés permettent en outre de valider le bon fonctionnement des APIs.

## Authentication API

La première API Rest consiste en la gestion et l'authentification d'utilisateurs, de manière générale. Une route permet de s'authentifier afin d'obtenir un token JWT, qui servira à être reconnu lors de toutes les requêtes futures sur les deux APIs (celui-ci devra être introduit dans l'en-tête de chaque requête). D'autres routes servent à visualiser les données des utilisateurs.

Une fois authentifié, un utilisateur a la possibilité s'il est administrateur de créer un nouveau compte, ou de visualiser les données personnelles de n'importe quel autre utilisateur. Lors de la création d'un utilisateur, celui-ci est entré dans la base de donnée, avec son mot de passe haché. La liste complète des routes est disponible ()[ici]

## Movies API

La seconde API permet à un utilisateur authentifié de consulter une liste (paginée) de films contenus dans la base de donnée (chargés au lancement à partir d'un fichier .csv) en entrant éventuellement un mot clé comme query-string, de voir tous les votes relatifs à un film donné, de voir la liste complète de ses propres votes, d'en ajouter, d'en supprimer ou d'en mettre à jour de nouveaux.

Lorsqu'un utilisateur ajoute un vote (note et description), une relation est établie entre lui et le film, comme l'illustre le schéma suivant :

!(image)[./imgs/db_movies.png]

La liste exhaustive des différentes routes est résumée ()[ici].

##
