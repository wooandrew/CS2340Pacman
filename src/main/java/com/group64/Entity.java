package com.group64;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Entity {
    
    protected D2D position;                         // Position in x,y coords
    protected D2D size;                             // Size of character, in pixels

    protected String spriteKey;                     // Key for chosen sprite
    protected HashMap<String, Image> sprites;       // Map of available sprites for this entity

    public Entity(String imgKey, D2D pos, D2D size) throws FileNotFoundException {

        this.position = pos;
        this.size = size;

        sprites = new HashMap<String, Image>();

        String key = imgKey.split(":")[0];
        String path = imgKey.split(":")[1];

        sprites.put(key, new Image(new FileInputStream(path)));

        spriteKey = key;
    }

    public Entity(ArrayList<String> imgKeys, D2D pos, D2D size) throws FileNotFoundException {
        
        this.position = pos;
        this.size = size;

        sprites = new HashMap<String, Image>();

        for (String iKey : imgKeys) {

            String key = iKey.split(":")[0];
            String path = iKey.split(":")[1];

            sprites.put(key, new Image(new FileInputStream(path)));
        }

        // Sprite key defaults to first sprite in imgKey
        spriteKey = imgKeys.get(0).split(":")[0];
    }

    public void update(ArrayList<Entity> entities) {

        // Do collision detection
        for (Entity ent : entities) {
            collisionDetection(ent);
        }
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(getSprite(), position.getX(), position.getY());
    }

    protected Boolean collisionDetection(Entity ent) {

        if (ent.getPosition().getX() < position.getX() + size.getWidth()
            && ent.getPosition().getX() + ent.getSize().getWidth() > position.getX() 
            && ent.getPosition().getY() < position.getY() + size.getHeight()
            && ent.getPosition().getY() + ent.getSize().getHeight() > position.getY()) {

            return true;
        }

        return false;
    }

    // Getters
    public D2D getPosition() {
        return position;
    }

    public D2D getSize() {
        return size;
    }

    public String getSpriteKey() {
        return spriteKey;
    }

    public Image getSprite() {
        return sprites.get(spriteKey);
    }

    public Image getSprite(String key) {
        return sprites.get(key);
    }

    // Setters
    public void setPosition(D2D position) {
        this.position = position;
    }

    public void getSize(D2D size) {
        this.size = size;
    }

    public void setSpriteKey(String key) {
        spriteKey = key;
    }

    public void respawn() {

    }
}
