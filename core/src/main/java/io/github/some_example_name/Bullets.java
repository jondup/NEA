package io.github.some_example_name;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Bullets {
    Vector2 velocity;
    Vector2 bulletLocation;
    float mouseX;
    float mouseY;
    Vector2 mouseLocation = new Vector2(mouseX, mouseY);
    float speed = 10f;

    public Bullets(float xpos, float ypos){
        this.bulletLocation = new Vector2(xpos,ypos);
        this.velocity = new Vector2(0,0);
    }

    public void bulletDirection(){
        Vector2 bulletDirection = mouseLocation.sub(bulletLocation).nor();
        velocity = bulletDirection.scl(speed);
        System.out.println(bulletDirection);
    }

    public void update(){
        mouseX = Gdx.input.getX();
        mouseY = Gdx.input.getY();
        bulletLocation.add(velocity);
    }

    public void draw (ShapeRenderer sr){
        sr.setColor(1,0,0,1);
        sr.rect(bulletLocation.x, bulletLocation.y, 10, 10);
        sr.setColor(1,1,1,1);
    }
}
