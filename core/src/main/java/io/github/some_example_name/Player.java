package io.github.some_example_name;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player {

    Vector2 location;
    Vector2 velocity;
    Planets currentPlanet;
    float gravityStrength = 0.2f;
    float playerRadius = 15;
    float movementSpeed = 3f;
    float jumpStrength = 5f;
    boolean grounded = false;
    int jumpCount = 0;
    boolean escaping = false;
    float angle;


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
            Vector2 directionFromPlanet = new Vector2 ((location).sub(currentPlanet.location).nor());
            location.set(currentPlanet.location.x + directionFromPlanet.x * minimumDistance, currentPlanet.location.y + directionFromPlanet.y * minimumDistance);
            velocity.set(0,0);
            grounded = true;
            jumpCount = 0;
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
        if(jumpCount < 2){
            if(!grounded && jumpCount == 1){
                Vector2 jumpDirection = new Vector2(location).sub(currentPlanet.location).nor();
                velocity.set(jumpDirection.scl(jumpStrength));
                jumpCount = 2;
                escaping = true;
            }
            else if(grounded){
                Vector2 jumpDirection = new Vector2(location).sub(currentPlanet.location).nor();
                velocity.add(jumpDirection.scl(jumpStrength));
                jumpCount = 1;
                grounded = false;
            }
        }
    }

    public void updateOrientation(){
        Vector2 playerToPlanet = new Vector2(location).sub(currentPlanet.location);
        angle = playerToPlanet.angleDeg() -90;
    }

    public void update(){
        if(!escaping){
            location.add(currentPlanet.velocity);
            applyGravity();
            updateOrientation();
        }
        location.add(velocity);
        if(!escaping){
            checkPlanetCollision();
        }
    }

    public void draw(ShapeRenderer sr){
        sr.setColor(1,1,1,1);
        sr.rect(location.x - 10, location.y - 15, 10, 15,20,30,1,1, angle);
        sr.setColor(1,1,1,1);
    }
}
