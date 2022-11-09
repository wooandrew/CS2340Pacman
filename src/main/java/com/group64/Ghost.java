package com.group64;

import java.io.FileNotFoundException;

public class Ghost extends Entity {
    public Ghost(String imgKey, D2D pos, D2D size) throws FileNotFoundException {
        super(imgKey, pos, size);
    }
}
