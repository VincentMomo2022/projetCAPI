# Objectif du projet 

# Structuration du code

exemple :  
    -arboressance  
    -rapport dans tel dossier  
    -tel prgramme fait ci  
    -tel dossier contien ci
    
    
    
Faire un read_me
    - qui a fait le projet
    - titre du proejt
    - description
    - guide d'installation
    - guide d'utili 
    - description des fichiers
    - comment ça s'est passé
    
#Projet réalisé par Albane Nicoullaud, Morgan Raveleau et Vincent Renault
#Objectif : Créer un jeu de balle aux prisionners
#Description : nous souhaitons réaliser une interface afin de permettre à des utilisateurs de jouer au jeu de la balle aux prisonniers.
#Comment récupérer le projet ?

#Guide d'utilisitation: le jeu se joue à deux, c'est un trois contre trois avec 2 ia dans chacune des équipes. Le joueur du haut se déplace avec les touche "Q" et "D", celui du bas avec les flèches driote et gauche du clavier. Pour viser le joueur du haut utilisera les touches "Z" et "S", tandis que le joueur du bas utilisera les flèches du haut et du bas. Pour tirer, les touches espace et entrée sont disponibles. Vous pouvez quitter la partie à tout moment en appuyant sur la croix.
#Le but du jeu est d'envoyer le projectile sur le joueur adverse. L'équipe qui aura touchés ses trois adversaire en premier gagnera la partie.
#Les fichiers sont divisés en 7 classes (App, Field, Player, PlayerHumain, PlayerIA, Projectile et Sprite), elles permettent le bon fonctionnement du jeu.
#La classe App sert de lancement du jeu, Field afin de préparer le terrain, Player est le constructeur pour les classes PlayerHumain (gère les joueurs humain) et PlayerIA (gère les joueurs artificiels), La classe Projectile traite la balle que les joueurs vont s'envoyer et la classe Sprite traitera des animations.
