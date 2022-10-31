package com.group64.Map.Tiles;

import javax.imageio.ImageIO;
import java.io.IOException;

public class TileManager {

     private Tile[] tile;
     private final int NUM_DIFF_TILES = 2;

     public TileManager() {
         tile = new Tile[NUM_DIFF_TILES];

         getTileImage();
     }

     public void getTileImage() {

        try {

            tile[0] = new Tile(true);
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/MapTileAssets/passable.png"));
            tile[1] = new Tile(true);
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/MapTileAssets/impassable.png"));



        } catch (IOException e) {
            e.printStackTrace();
        }

     }
}

