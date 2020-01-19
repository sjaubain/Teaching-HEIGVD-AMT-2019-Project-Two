## mysql_users

contient uniquement la table *user* avec l'email (id), nom, prénom, nom d'utilisateur, mot de passe, role (isAdmin = true | false), etat du compte (isLocked = true | false).

Quelques utilisateurs arbitraires sont insérés dans la db lors du chargement.

## mysql_movies

* **table user** contient uniquement l'id (email)
* **table movie** contient en plus de l'id, le titre, une description et le réalisateur.
* **table rating** table capturant la relation entre un utilisateur et un film. Contient en plus de l'id, les références vers l'utilisateur et le film concernés, ainsi qu'une note et un description.

La base de donnée est remplie au chargement à partir d'un fichier .csv contenant une liste de films.
