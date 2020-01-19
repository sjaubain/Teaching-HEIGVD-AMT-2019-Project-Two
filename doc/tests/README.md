## Tests couverts pour l'API users

* authentification (validée ou non suivant le JWT).
* validation du role de l'administrateur ou du propriétaire des données

## Tests couverts pour l'API movies

Les 4 opérations CRUD sont testées. Pour ce faire, on partage grâce à *Picocontainer* l'état des classes de tests entre les différentes étapes d'un scénario et on garde ainsi une référence sur le dernier vote utilisé (ajouté, modifié...) ainsi que sur la dernière réponse du serveur, ce qui permet de comparer les deux et de vérifier que les opérations CRUD se sont bien déroulé (p.ex. ajout d'un vote, le serveur répond avec ce vote dans le corps de la réponse, puis au prochain rendez-vous sur la page de consultation des votes, vérification qu'il s'y trouve bien)

* ajout d'un vote, puis verification de l'ajout.
* mise à jour d'un vote, puis vérification que celui-ci a changé et non qu'un nouveau vote a été ajouté.
* suppression d'un vote, puis vérification qu'il ne se trouve plus dans la db.
* simple consultation des votes.
