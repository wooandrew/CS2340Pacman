package com.group64;

import com.group64.Map.Map;
import javafx.scene.canvas.GraphicsContext;

import java.util.Random;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Ghost extends Entity {

    private int direction;          // 0: up, 1: down, 2: left, 3: right
    private int nextDirection;      // 0: up, 1: down, 2: left, 3: right
    private int speed;
    private int dead;

    public Ghost(String imgKey, D2D pos, D2D size) throws FileNotFoundException {

        super(imgKey, pos, size);
        dead = 0;
        speed = 1;
        direction = 2;
        nextDirection = 0;
    }

    private void setNextDirection(Random random) {

        nextDirection = random.nextInt(4);

        if (nextDirection == direction) {
            nextDirection++;
        }
    }

    private Boolean inSpawn() {

        D2D nPos = new D2D(position.getX() / 32, position.getY() / 32);
        if (nPos.getY() == 12 && nPos.getX() > 14 && nPos.getX() < 21) {
            return true;
        }

        return false;
    }

    private void changeDirections(Map map, Random random) {

        D2D nPos = new D2D(position.getX() / 32, position.getY() / 32);

        int iAbove = (nPos.getY() - 1) * map.getMapSize().getWidth() + nPos.getX();         // Above
        int iBelow = (nPos.getY() + 1) * map.getMapSize().getWidth() + nPos.getX();         // Below
        int iRight = nPos.getY() * map.getMapSize().getWidth() + nPos.getX() + 1;           // Right
        int iLeft = nPos.getY() * map.getMapSize().getWidth() + nPos.getX() - 1;            // Left

        // 0: up, 1: down, 2: left, 3: right
        if (direction == 0) {
            if (map.isWall(iAbove)) {
                if (!map.isWall(iRight) && !map.isWall(iLeft)) {
                    nextDirection = random.nextInt(2) + 2;
                } else if (!map.isWall(iRight)) {
                    nextDirection = 3;
                } else if (!map.isWall(iLeft)) {
                    nextDirection = 2;
                }
            }
        } else if (direction == 1) {
            if (map.isWall(iBelow)) {
                if (!map.isWall(iRight) && !map.isWall(iLeft)) {
                    nextDirection = random.nextInt(2) + 2;
                } else if (!map.isWall(iRight)) {
                    nextDirection = 3;
                } else if (!map.isWall(iLeft)) {
                    nextDirection = 2;
                }
            }
        } else if (direction == 2) {
            if (map.isWall(iLeft)) {
                if (!map.isWall(iAbove) && !map.isWall(iBelow)) {
                    nextDirection = random.nextInt(2);
                } else if (!map.isWall(iAbove)) {
                    nextDirection = 0;
                } else if (!map.isWall(iBelow)) {
                    nextDirection = 1;
                }
            }
        } else if (direction == 3) {
            if (map.isWall(iRight)) {
                if (!map.isWall(iAbove) && !map.isWall(iBelow)) {
                    nextDirection = random.nextInt(2);
                } else if (!map.isWall(iAbove)) {
                    nextDirection = 0;
                } else if (!map.isWall(iBelow)) {
                    nextDirection = 1;
                }
            }
        }

    }

    public void update(Map map, Random random) {

        ArrayList<Entity> tiles = map.getTiles();

        changeDirections(map, random);
        if (!inSpawn() && random.nextInt(1001) < 5) {
            setNextDirection(random);
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

        Boolean skipNormalization = false;

        for (Entity tile : tiles) {

            if (collisionDetection(tile) && tile.getSpriteKey().compareTo("wall") == 0) {

                if (inSpawn()) {
                    nextDirection = 0;
                    skipNormalization = true;
                }

                if (direction == 0) {
                    position.setY(position.getY() + speed);
                } else if (direction == 1) {
                    position.setY(position.getY() - speed);
                } else if (direction == 2) {
                    position.setX(position.getX() + speed);
                    if (inSpawn()) {
                        direction = 3;
                        break;
                    }
                } else if (direction == 3) {
                    position.setX(position.getX() - speed);
                    if (inSpawn()) {
                        direction = 2;
                        break;
                    }
                }

                direction = nextDirection;
                skipNormalization = true;
            }
        }

        // Normalized position for ghost / grid position
        if (!skipNormalization) {

            D2D nPos = new D2D(position.getX() / 32, position.getY() / 32);

            if (nextDirection == 0) {
                int index = (nPos.getY() - 1) * map.getMapSize().getWidth() + nPos.getX();
                Boolean aboveIsNotWall = tiles.get(index).getSpriteKey().compareTo("wall") != 0;
                if (aboveIsNotWall && position.getX() == tiles.get(index).getPosition().getX()) {
                    direction = nextDirection;
                }
            } else if (nextDirection == 1) {
                int index = (nPos.getY() + 1) * map.getMapSize().getWidth() + nPos.getX();
                Boolean underIsNotWall = tiles.get(index).getSpriteKey().compareTo("wall") != 0;
                if (underIsNotWall && position.getX() == tiles.get(index).getPosition().getX()) {
                    direction = nextDirection;
                }
            } else if (nextDirection == 2) {
                int index = nPos.getY() * map.getMapSize().getWidth() + nPos.getX() - 1;
                Boolean leftIsNotWall = tiles.get(index).getSpriteKey().compareTo("wall") != 0;
                if (leftIsNotWall && position.getY() == tiles.get(index).getPosition().getY()) {
                    direction = nextDirection;
                }
            } else if (nextDirection == 3) {
                int index = nPos.getY() * map.getMapSize().getWidth() + nPos.getX() + 1;
                Boolean rightIsNotWall = tiles.get(index).getSpriteKey().compareTo("wall") != 0;
                if (rightIsNotWall && position.getY() == tiles.get(index).getPosition().getY()) {
                    direction = nextDirection;
                }
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        if (dead-- < 0) {
            gc.drawImage(getSprite(), position.getX(), position.getY());
        }
    }
    public void respawn() {
        dead = 120;
        position.setX(608);
        position.setY(384);
    }
}
