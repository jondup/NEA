package io.github.some_example_name;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Coins {
    Vector2 coinLocation;
    Vector2 velocity;
    int orientation;

    public Coins(Planets planet){
        this.orientation = (int)(Math.random() * 360);
        this.coinLocation = new Vector2(planet.location.x + 210*(float)Math.cos(orientation), planet.location.y + 210*(float)Math.sin(orientation));
        this.velocity = new Vector2(-2, 0);
    }

    public void update(){
        coinLocation.add(velocity);
    }

    public void draw(ShapeRenderer sr){
        sr.setColor(2.25f,2.15f,0,1);
        sr.circle(coinLocation.x, coinLocation.y, 8);
        sr.setColor(1,1,1,1);
    }
}
