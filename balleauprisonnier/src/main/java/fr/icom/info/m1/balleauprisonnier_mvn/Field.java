package fr.icom.info.m1.balleauprisonnier_mvn;


import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/**
 * Classe gerant le terrain de jeu.
 * 
 */
public class Field extends Canvas {

	private static Field instance;

	/** Joueurs */
	Player[] joueursBas = new Player[3];
	Player[] joueursHaut = new Player[3];

	Player[] joueursHumain = new Player[2];
	Player[] joueursIA = new Player[4];

	Projectile projectile;

	/** Couleurs possibles */
	String[] colorMap = new String[] {"blue", "green", "orange", "purple", "yellow"};
	/** Tableau traçant les evenements */
    ArrayList<String> input = new ArrayList<String>();
    

    final GraphicsContext gc;
    final int width = 600;
    final int height = 600;
    
    /**
     * Canvas dans lequel on va dessiner le jeu.
     * 
     * @param scene Scene principale du jeu a laquelle on va ajouter notre Canvas
     * @param w largeur du canvas
     * @param h hauteur du canvas
     */
	public Field(int w, int h)
	{
		super(w, h);

		/** permet de capturer le focus et donc les evenements clavier et souris */
		this.setFocusTraversable(true);

        gc = this.getGraphicsContext2D();

        /** On initialise le terrain de jeu */
		joueursBas[0] = new PlayerHumain(gc, colorMap[0], w/4, h-100, "bottom");
		joueursBas[0].display();

		joueursBas[1] = new PlayerIA(gc, colorMap[0], 2*w/4, h-100, "bottom");
		joueursBas[1].display();

		joueursBas[2] = new PlayerIA(gc, colorMap[0], 3*w/4, h-100, "bottom");
		joueursBas[2].display();


		joueursHaut[0] = new PlayerHumain(gc,colorMap[1], w/4, 20, "top");
		joueursHaut[0].display();

		joueursHaut[1] = new PlayerIA(gc, colorMap[1], 2*w/4, 20, "top");
		joueursHaut[1].display();

		joueursHaut[2] = new PlayerIA(gc, colorMap[1], 3*w/4, 20, "top");
		joueursHaut[2].display();


		joueursBas[0].setBall(new Projectile(gc, joueursBas[0].getX(), joueursBas[0].getY(), joueursBas[0].getAngle(), 0,  joueursBas[0].getPlayerColor()));
		projectile = joueursBas[0].getBall();

		for(Player p : joueursHaut){
			if(p instanceof PlayerHumain){
				joueursHumain[0] = p;
			}
		}
		for(Player p : joueursHaut){
			if(p instanceof PlayerIA){
				joueursIA[0] = p;
			}
		}


		for(Player p : joueursBas){
			if(p instanceof PlayerHumain){
				joueursHumain[1] = p;
			}
		}
		for(Player p : joueursBas){
			if(p instanceof PlayerIA){
				joueursIA[1] = p;
			}
		}


	    /** 
	     * Event Listener du clavier 
	     * quand une touche est pressee on la rajoute a la liste d'input
	     *   
	     */
	    this.setOnKeyPressed(
	    		new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            // only add once... prevent duplicates
	    	            if ( !input.contains(code) )
	    	                input.add( code );
	    	        }
	    	    });

	    /** 
	     * Event Listener du clavier 
	     * quand une touche est relachee on l'enleve de la liste d'input
	     *   
	     */
	    this.setOnKeyReleased(
	    	    new EventHandler<KeyEvent>()
	    	    {
	    	        public void handle(KeyEvent e)
	    	        {
	    	            String code = e.getCode().toString();
	    	            input.remove( code );
	    	        }
	    	    });
	    
	    /** 
	     * 
	     * Boucle principale du jeu
	     * 
	     * handle() est appelee a chaque rafraichissement de frame
	     * soit environ 60 fois par seconde.
	     * 
	     */

	    new AnimationTimer() 
	    {
			Player[] joueurs = getJoueursHumain();
			Player[] joueursIA = getJoueursIA();

			int width = width();
			int height = height();

			Projectile projectile = getProjectile();
	        public void handle(long currentNanoTime)
	        {	 
	            // On nettoie le canvas a chaque frame
	            gc.setFill( Color.LIGHTGRAY);
	            gc.fillRect(0, 0, width, height);
	        	
	            // Deplacement et affichage des joueurs
	        	for (int i = 0; i < joueurs.length; i++)
	    	    {
	        		if (i==1 && input.contains("LEFT"))
	        		{
	        			joueurs[i].moveLeft();
	        		} 
	        		if (i==1 && input.contains("RIGHT"))
	        		{
	        			joueurs[i].moveRight();	        			
	        		}
	        		if (i==1 && input.contains("UP"))
	        		{
	        			joueurs[i].turnLeft();
	        		} 
	        		if (i==1 && input.contains("DOWN"))
	        		{
	        			joueurs[i].turnRight();	        			
	        		}
	        		if (i==0 && input.contains("Q"))
	        		{
	        			joueurs[i].moveLeft();
	        		} 
	        		if (i==0 && input.contains("D"))
	        		{
	        			joueurs[i].moveRight();	        			
	        		}
	        		if (i==0 && input.contains("Z"))
	        		{
	        			joueurs[i].turnLeft();
	        		} 
	        		if (i==0 && input.contains("S"))
	        		{
	        			joueurs[i].turnRight();	        			
	        		}
					if (i==0 && input.contains("SPACE") && joueurs[i].getBall() != null){
						joueurs[i].shoot();
					}
					if (i==1 && input.contains("ENTER") && joueurs[i].getBall() != null){
						joueurs[i].shoot();
					}

					if (projectile != null) {
						projectile.display();
					}

					joueurs[i].display();
					joueursIA[i].display();
	    	    }

	    	}
	     }.start(); // On lance la boucle de rafraichissement 
	     
	}
	public GraphicsContext getGc(){
		return gc;
	}
	public Player[] getJoueursBas() {
		return joueursBas;
	}
	public Player[] getJoueursHaut(){ return joueursHaut;}
	public Player[] getJoueursHumain() { return joueursHumain; }
	public Player[] getJoueursIA() { return joueursIA; }

	public int width() {
		return width;
	}
	public int height(){
		return height;
	}

	public static Field getInstance(){
		if(instance == null){
			instance = new Field(600, 600);
		}
		return instance;
	}

	public Projectile getProjectile() {
		return projectile;
	}

	public Player getClosestPlayer(double x, double y, String side){
		float distanceMin = -1;
		Player closestPlayer = null;
		if(side.equals("top")){
			for (Player p : Field.instance.getJoueursHaut()) {
				float distance = (float) Math.sqrt(Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2));
				if(distanceMin == -1 || distance < distanceMin){
					distanceMin = distance;
					closestPlayer = p;
				}
			}
		}
		else{
			for (Player p : Field.instance.getJoueursBas()) {
				float distance = (float) Math.sqrt(Math.pow(p.getX() - x, 2) + Math.pow(p.getY() - y, 2));
				if(distanceMin == -1 || distance < distanceMin){
					distanceMin = distance;
					closestPlayer = p;
				}
			}
		}
		return closestPlayer;
	}
}
