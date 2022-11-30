package com.group64;

import com.group64.Map.Map;

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

    private int direction;          // 0: up, 1: down, 2: left, 3: right
    private int nextDirection;      // 0: up, 1: down, 2: left, 3: right

    private boolean invincible;
    private int invincibleFrames;

    private boolean poweredUp;
    private int poweredUpFrames;

    private int ghostsEaten;

    public Character(ArrayList<String> sprites, D2D size) throws FileNotFoundException {
        super(sprites, new D2D(576, 576), size);
        reset();
    }

    public void reset() {
        score = 0;
        lives = 3;
        speed = 2;

        direction = 1;
        nextDirection = 1;

        ghostsEaten = 0;

        position = new D2D(576, 576);
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public int getGhostsEaten() {
        return ghostsEaten;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void update(Scene scene, Map map, ArrayList<Pellet> pellets, ArrayList<Ghost> ghosts) {

        ArrayList<Entity> tiles = map.getTiles();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        nextDirection = 0;
                        break;
                    case DOWN:
                        nextDirection = 1;
                        break;
                    case LEFT:
                        nextDirection = 2;
                        break;
                    case RIGHT:
                        nextDirection = 3;
                        break;
                    default:

                        break;
                }
            }
        });

        D2D nPos = new D2D(position.getX() / 32, position.getY() / 32);

        int iAbove = (nPos.getY() - 1) * map.getMapSize().getWidth() + nPos.getX();         // Above
        int iBelow = (nPos.getY() + 1) * map.getMapSize().getWidth() + nPos.getX();         // Below
        int iRight = nPos.getY() * map.getMapSize().getWidth() + nPos.getX() + 1;           // Right
        int iLeft = nPos.getY() * map.getMapSize().getWidth() + nPos.getX() - 1;            // Left

        if (nextDirection == 0 && !map.isWall(iAbove)) {
            if (position.getX() == tiles.get(iAbove).getPosition().getX()) {
                direction = nextDirection;
            }
        } else if (nextDirection == 1 && !map.isWall(iBelow)) {
            if (position.getX() == tiles.get(iBelow).getPosition().getX()) {
                direction = nextDirection;
            }
        } else if (nextDirection == 2 && !map.isWall(iLeft)) {
            if (position.getY() == tiles.get(iLeft).getPosition().getY()) {
                direction = nextDirection;
            }
        } else if (nextDirection == 3 && !map.isWall(iRight)) {
            if (position.getY() == tiles.get(iRight).getPosition().getY()) {
                direction = nextDirection;
            }
        }

        if (direction == 0) {
            position.setY(position.getY() - speed);
        } else if (direction == 1) {
            position.setY(position.getY() + speed);
        } else if (direction == 2) {
            position.setX(position.getX() - speed);
        } else if (direction == 3) {
            position.setX(position.getX() + speed);
        }

        // Collision check against walls
        for (Entity tile : tiles) {

            if (collisionDetection(tile) && tile.getSpriteKey().compareTo("wall") == 0) {
                
                if (direction == 0) {
                    position.setY(position.getY() + speed);
                } else if (direction == 1) {
                    position.setY(position.getY() - speed);
                } else if (direction == 2) {
                    position.setX(position.getX() + speed);
                } else if (direction == 3) {
                    position.setX(position.getX() - speed);
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
                if (pellets.get(x).getSpriteKey().equals("big")) {
                    poweredUp = true;
                }
                pellets.remove(x);
            }
        }

        if (poweredUp) {
            if (++poweredUpFrames > 300) {
                poweredUp = false;
                poweredUpFrames = 0;
            }
        }

        if (invincible) {
            if (++invincibleFrames > 120) {
                invincible = false;
                invincibleFrames = 0;
            }
        } else {
            for (Ghost ghost : ghosts) {
                if (collisionDetection(ghost)) {
                    if (poweredUp) {
                        ghost.respawn();
                        score += 20;
                        ++ghostsEaten;
                    } else {
                        invincible = true;
                        lives--;
                    }
                }
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc) {

        if (invincible && invincibleFrames % 10 < 5) {       // Blink
            gc.drawImage(getSprite(), position.getX(), position.getY());
        } else if (poweredUp) {
            gc.drawImage(getSprite("magenta"), position.getX(), position.getY());
        } else if (!invincible) {
            gc.drawImage(getSprite(), position.getX(), position.getY());
        }

        // Hitbox debugging
        //gc.fillRect(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }
}
