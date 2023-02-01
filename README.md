# Projet-APO-Tic-tac-toe-3D
Projet réalisé avec Java et Maven


## Structure de l’archive
- Les class *.java sont dans le dossier /projet tictactoe/src/main/java/apo/boissot laqueuvre moulon*
- La documentation est dans le dossier */Javadoc*
- Les diagrammes UML sont dans le dossier */Diagramme UML*


## Lancement
Dans une console, placez vous dans le dossier */projet tictactoe/*

- **Pour compiler le code utiliser les commandes :**
```
	mvn compile
	mvn package
	mvn install
```

- **Pour lancer le programme utiliser la commande :**
```
java -cp projet tictactoe-1.0-SNAPSHOT.jar apo.boissot laqueuvre moulon.App
```


## Le jeu
Le tic-tac-toe est un jeu à deux joueurs. A son tour, chaque joueur place une marque l’identifiant, traditionnellement X et O, sur un tableau de 3×3 cellules. Le jeu se termine quand un des deux joueurs gagne ou quand le tableau est tout rempli. Un joueur gagne quand il a plac ́e 3 marques align ́ees sur le tableau. L’alignement peut être vertical, horizontal ou diagonal.
Dans sa version 3D, le tableau n’est plus représenté par un carré, mais par un cube. Le but du jeu reste l’alignement de 3 cellules. L’alignement peut être parallèle à un axe, diagonal au travers de deux axes, ou diagonal au travers des trois axes.

## Features
- grille de taille variable
- affichage de la combinaison gagnante
- sauvegarde de la partie
- jouer contre l'ordinateur (minmax)