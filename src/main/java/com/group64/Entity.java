package com.group64;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public abstract class Entity {
    
    protected D2D position;                         // Position in x,y coords
    protected D2D size;                             // Size of character, in pixels

    protected String spriteKey;                     // Key for chosen sprite
    protected HashMap<String, Image> sprites;       // Map of available sprites for this entity

    public Entity(ArrayList<String> imgKey, D2D pos, D2D size) throws FileNotFoundException {
        
        this.position = pos;
        this.size = size;

        sprites = new HashMap<String, Image>();

        for (String iKey : imgKey) {

            String key = iKey.split(":")[0];
            String path = iKey.split(":")[1];

            sprites.put(key, new Image(new FileInputStream(path)));
        }

        // Sprite key defaults to first sprite in imgKey
        spriteKey = imgKey.get(0).split(":")[0];
    }

    public void update(Entity[] entities) {

        // Do collision detection
        for (Entity ent : entities) {
            collisionDetection(ent);
        }
    }

    private Boolean collisionDetection(Entity ent) {

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
}
