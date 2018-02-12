This was a project given to students of an introductory course to Java. 
Some classes were given and some had to be written or completed. The implementation
of the snake was supposed to use linked lists (tought during the course) and
a more sophisticated variant called Hydra was a pretext to use a tree-like data structure.

The following readme in French was also provided.

  **************************************************************
Les différentes parties du projet 
  **************************************************************
  **************************************************************

  - La classe Game prend en charge l'affichage et l'interface avec le joueur. Vous n'avez pas besoin de la modifier, mais vous êtes encouragés à modifier certains paramètres pour voir l'effet que cela produit sur le jeu.
  
  - Le répertoire maps contient les différentes décors des différents niveaux. Vous n'aurez pas besoin de le modifier.
  
  - La classe ColorPoint permet de manipuler des points associés à des couleurs. Rien à modifier ici non plus.

  - Le fichier Creature.java est une interface qui donne la liste des méthodes devant figurer dans Serpent et Hydre pour que le jeu fonctionne. Ne pas modifier ce fichier.
  
  - La classe Serpent, partiellement écrite, implémente le serpent qui apparaît à l'écran. C'est sur cette classe que vous travaillerez dans la première partie.
  
  - La classe Hydre implémente l'hydre. C'est l'objet de la Partie 2.

  **************************************************************
Options de Game
  **************************************************************
  **************************************************************

  - Pour lancer une partie avec serpent: java Game s

  - Pour lancer une partie avec hydre: java Game h

  - Pour initialiser au niveau i<3: java Game h i

  **************************************************************
Les commandes de jeu
  **************************************************************
  **************************************************************

  - Les flèches servent à bouger la créature (si la méthode bouge(int[] v) a été implémentée).

  - Pour mettre en pause, on appuie sur la barre espace.

  - En appuyant sur 'c' quand le jeu est en pause, on peut changer de tête (si la méthode changeTete() a été implémentée).

  - En appuyant sur 'd' quand le jeu est en pause, on peut afficher des informations de déboggage (si la méthode debugInfo() a été implémentée).
