package com.group64;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Character extends Entity {
    
    private int lives;          // Number of lives player has
    private int score;
    private int speed;

    private boolean goNorth;
    private boolean goSouth;
    private boolean goEast;
    private boolean goWest;

    private boolean invincible;
    private int invincibleFrames;
    private boolean poweredUp;

    public Character(ArrayList<String> sprites, D2D size) throws FileNotFoundException {
        super(sprites, new D2D(576, 576), size);
        reset();
    }

    public void reset() {
        score = 0;
        lives = 3;
        speed = 2;

        position = new D2D(576, 576);
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void update(Scene scene, ArrayList<Entity> tiles, 
                       ArrayList<Pellet> pellets, ArrayList<Ghost> ghosts) {

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        goNorth = true;
                        break;
                    case DOWN:
                        goSouth = true;
                        break;
                    case LEFT:
                        goWest  = true;
                        break;
                    case RIGHT:
                        goEast  = true;
                        break;
                    default:

                        break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        goNorth = false;
                        break;
                    case DOWN:
                        goSouth = false;
                        break;
                    case LEFT:
                        goWest  = false;
                        break;
                    case RIGHT:
                        goEast  = false;
                        break;

                    default:
                        break;
                }
            }
        });

        if (goNorth) {
            position.setY(position.getY() - speed);
        }
        if (goSouth) {
            position.setY(position.getY() + speed);
        }
        if (goEast) {
            position.setX(position.getX() + speed);
        }
        if (goWest) {
            position.setX(position.getX() - speed);
        }

        // Collision check against walls
        for (Entity tile : tiles) {

            if (collisionDetection(tile) && tile.getSpriteKey().compareTo("wall") == 0) {
                
                if (goNorth) {
                    position.setY(position.getY() + speed);
                }
                if (goSouth) {
                    position.setY(position.getY() - speed);
                }
                if (goEast) {
                    position.setX(position.getX() - speed);
                }
                if (goWest) {
                    position.setX(position.getX() + speed);
                }
            }
        }
        // Collision check against pellets
        for (int x = 0; x < pellets.size(); ++x) {

            if (collisionDetection(pellets.get(x))) { // collision happens
                score += pellets.get(x).getPointsWorth();
                if (pellets.get(x).getSpriteKey().equals("addLife")) {
                    lives++;
                }
                pellets.remove(x);
            }
        }

        if (invincible) {
            if (invincibleFrames++ > 120) {
                invincible = false;
                invincibleFrames = 0;
            }
        } else {
            for (Ghost ghost : ghosts) {
                if (collisionDetection(ghost)) {
                    if (poweredUp) {
                        ghost.respawn();
                        score += 20;
                    } else {
                        lives--;
                        ghost.respawn();
                        respawn();
                    }
                }
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(getSprite(), position.getX(), position.getY());

        // Hitbox debugging
        //gc.fillRect(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }

    public void respawn() {
        position.setX(576);
        position.setY(576);
    }
}
