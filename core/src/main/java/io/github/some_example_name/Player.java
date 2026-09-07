package io.github.some_example_name;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {

    Vector2 location;
    Vector2 velocity;
    Planets currentPlanet;
    float gravityStrength = 10.0f;
    float playerRadius = 15;
    float movementSpeed = 3f;
    float jumpStrength = 10f;
    boolean grounded = false;


    public Player(int xpos, int ypos) {
        this.location = new Vector2(xpos, ypos);
        this.velocity = new Vector2(0, 0);
    }

    public void applyGravity() {
        Vector2 gravityDirection = new Vector2(currentPlanet.location).sub(location).nor();
        velocity.add(gravityDirection.scl(gravityStrength));
    }

    public void checkPlanetCollision(){
        float distance = location.dst(currentPlanet.location);
        float minimumDistance = currentPlanet.rad + playerRadius;
        if(distance < minimumDistance){
            Vector2 directionFromPlanet = new Vector2(location).sub(currentPlanet.location).nor();
            location.set(currentPlanet.location.x + directionFromPlanet.x * minimumDistance, currentPlanet.location.y + directionFromPlanet.y * minimumDistance);
            velocity.set(0,0);
            grounded = true;
        }
        else{
            grounded = false;
        }

    }

    public void moveAroundPlanet(float direction){
        Vector2 outwardDirection = new Vector2(location).sub(currentPlanet.location).nor();
        Vector2 tangent = new Vector2(-outwardDirection.y, outwardDirection.x);
        location.add(tangent.scl(movementSpeed * direction));
    }

    public void jump(){
        if(grounded){
            Vector2 jumpDirection = new Vector2(location).sub(currentPlanet.location).nor();
            velocity.add(jumpDirection.scl(jumpStrength));
            grounded = false;
        }
    }

    public void update(){
        location.add(currentPlanet.velocity);
        applyGravity();
        location.add(velocity);
        checkPlanetCollision();
    }

    public void draw(ShapeRenderer sr){
        sr.setColor(1,1,1,1);
        sr.rect(location.x - 15, location.y - 15, 30, 30);
        sr.setColor(1,1,1,1);
    }
}
