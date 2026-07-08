package io.github.some_example_name;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.Gdx;

public class Player {

    Vector2 location;
    Vector2 velocity;
    Vector2 jumppower;

    public Player(int xpos, int ypos) {
        this.location = new Vector2(xpos, ypos);
        this.velocity = new Vector2(0, 0);
        this.jumppower = new Vector2(0, 10);
    }

    public void draw(ShapeRenderer sr){
        sr.setColor(1,1,1,1);
        sr.rect(location.x, location.y, 20, 30);
        sr.setColor(1,1,1,1);
    }
}
