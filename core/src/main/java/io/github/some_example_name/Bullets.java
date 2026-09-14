package io.github.some_example_name;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Bullets {

    Vector2 mouseTarget;
    Vector2 velocity;
    Vector2 bulletLocation;
    float speed = 10f;

    public Bullets(float xpos, float ypos, float mouseX, float mouseY){
        this.bulletLocation = new Vector2(xpos,ypos);
        this.velocity = new Vector2(0,0);
        this.mouseTarget = new Vector2(mouseX, mouseY);
        bulletDirection();
    }

    public void bulletDirection(){
        Vector2 bulletDirection = new Vector2(mouseTarget).sub(bulletLocation).nor();
        velocity = bulletDirection.scl(speed);
    }

    public void update(){
        bulletLocation.add(velocity);
    }

    public void draw (ShapeRenderer sr){
        sr.setColor(1,0,0,1);
        sr.rect(bulletLocation.x, bulletLocation.y, 10, 10);
        sr.setColor(1,1,1,1);
    }
}
