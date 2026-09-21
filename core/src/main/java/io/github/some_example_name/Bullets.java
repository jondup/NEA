package io.github.some_example_name;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;

public class Bullets {

    Vector2 mouseTarget;
    Vector2 velocity;
    Vector2 bulletLocation;
    float speed = 10f;
    float angle;

    public Bullets(float xpos, float ypos, float mouseX, float mouseY, ArrayList<Planets> planets){
        this.bulletLocation = new Vector2(xpos,ypos);
        this.velocity = new Vector2(0,0);
        this.mouseTarget = new Vector2(mouseX, mouseY);
        bulletDirection();
    }

    public void bulletDirection(){
        Vector2 bulletDirection = new Vector2(mouseTarget).sub(bulletLocation).nor();
        velocity = bulletDirection.scl(speed);
    }

    public void updateOrientation(){
        Vector2 bulletDirection = new Vector2(mouseTarget).sub(bulletLocation);
        angle = bulletDirection.angleDeg() -90;
    }

    /*public void checkCollision(ArrayList<Planets> planets, ArrayList<Bullets> bullets){
        for(Planets planet : planets){
            for(Bullets bullet : bullets){
                float distance = planet.location.dst(bullet.bulletLocation);
                float minimumDistance = planet.rad + 10;
                if(distance < minimumDistance){
                    bullets.iterator().remove(bullet);
                }
            }
        }
    }*/

    public void update(){
        bulletLocation.add(velocity);
        updateOrientation();
    }

    public void draw (ShapeRenderer sr){
        sr.setColor(1,0,0,1);
        sr.rect(bulletLocation.x-2.5f, bulletLocation.y-10, 2.5f, 10, 5, 20, 1, 1, angle);
        sr.setColor(1,1,1,1);
    }
}
