package io.github.some_example_name;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Planets {
    Vector2 location;
    Vector2 velocity;
    int rad = 200;
    float rvalue;
    float gvalue;
    float bvalue;

    public Planets(int locationx, int locationy, int velocityx, int velocityy, float rvalue, float gvalue, float bvalue) {
        this.location = new Vector2(locationx, locationy);
        this.velocity = new Vector2(velocityx, velocityy);

        this.rvalue = rvalue;
        this.gvalue = gvalue;
        this.bvalue = bvalue;
    }

    public void update(){
        location.add(velocity);
    }

    public void draw(ShapeRenderer sr){
        sr.setColor(rvalue,gvalue,bvalue,1);
        sr.circle(location.x, location.y, rad);
        sr.setColor(1,1,1,1);
    }
}
