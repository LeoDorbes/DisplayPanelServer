# Display Panel Server - Projet ECE Tech

Le principe de ce projet est de créer une application de paneau d'affichage dans
un stade. Ce panneau doit afficher le score d'un match ainsi que le temps restant.
Il faudra également ajouter un controlleur à distance (sous la forme d'une application differente) qui
communiquera avec le panneau afin de modifier le score du match, de le mettre en pause voir de le terminer.

Les applications sont codées en Java (Client & Serveur).
Les applications doivent sauvegarder/recuperer des informations sur une base de données (ici MySQL).
La communication se fait à l'aide du protocole TCP/IP en utilisant les sockets Java.
