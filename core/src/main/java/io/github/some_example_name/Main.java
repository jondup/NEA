package io.github.some_example_name;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    private Planets planet1;
    private Planets planet2;
    private Planets planet3;
    private Planets planet4;
    private Vector2 midpoint12;
    private Vector2 midpoint23;
    private Vector2 midpoint34;
    private Vector2 midpoint41;

    boolean nearestToPlanet1;
    boolean nearestToPlanet2;
    boolean nearestToPlanet3;
    boolean nearestToPlanet4;

    private Player player;
    private Vector2 playerLocation;
    Vector2 playerToPlanet;
    Vector2 leftTangent;
    Vector2 leftMovement;
    Vector2 rightTangent;
    Vector2 rightMovement;
    Vector2 gravityDirection;
    float gravityStrength = 0.5f;
    float playerSpeed = 0.01f;

    ShapeRenderer sr;



    @Override
    public void create() {
        sr = new ShapeRenderer();
        planet1 = new Planets(400, 500, -3, 0, 1, 0, 0);
        planet2 = new Planets(1000, 500, -3, 0, 0, 1, 0);
        planet3 = new Planets(1600, 500, -3, 0, 0, 0, 1);
        planet4 = new Planets(2200,500, -3, 0, 0, 0.5f, 0.5f);
        midpoint12 = new Vector2(300 + planet1.location.x, 0);
        midpoint23 = new Vector2(300 + planet2.location.x, 0);
        midpoint34 =  new Vector2(300 + planet3.location.x, 0);
        midpoint41 =  new Vector2(300 + planet4.location.x, 0);

        player = new Player(1000, 700);

        playerLocation = player.location;
        player.location.x = player.location.x + 10;

    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);

        planet1.draw(sr);
        planet2.draw(sr);
        planet3.draw(sr);
        planet4.draw(sr);
        planet1.location.add(planet1.velocity);
        planet2.location.add(planet1.velocity);
        planet3.location.add(planet1.velocity);
        planet4.location.add(planet1.velocity);
        midpoint12.add(planet1.velocity);
        midpoint23.add(planet1.velocity);
        midpoint34.add(planet1.velocity);
        midpoint41.add(planet1.velocity);

        closestPlanetToPlayer(player.location);

        player.draw(sr);
        player.velocity.add(0.8f,0);
        circularMotion();

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            rightMotion();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            leftMotion();
        }

        if(midpoint12.x < 0){
            midpoint12.x = planet1.location.x + 300;
        }
        if(midpoint23.x < 0){
            midpoint23.x = planet2.location.x + 300;
        }
        if(midpoint34.x < 0){
            midpoint34.x = planet3.location.x + 300;
        }
        if(midpoint41.x < 0){
            midpoint41.x = planet4.location.x + 300;
        }

        if(planet1.location.x + planet1.rad < 0){
            planet1.location.x = Gdx.graphics.getWidth() + planet1.rad;
        }
        if(planet2.location.x + planet2.rad < 0){
            planet2.location.x = Gdx.graphics.getWidth() + planet2.rad;
        }
        if(planet3.location.x + planet3.rad < 0){
            planet3.location.x = Gdx.graphics.getWidth() + planet3.rad;
        }
        if(planet4.location.x + planet4.rad < 0){
            planet4.location.x = Gdx.graphics.getWidth() + planet4.rad;
        }
        sr.end();
    }

    public void closestPlanetToPlayer(Vector2 playerLocation){
        if(midpoint41.x < midpoint12.x){
            if(playerLocation.x < midpoint12.x && playerLocation.x > midpoint41.x) {
                nearestToPlanet1 = true;
                gravity(planet1.location);
            }
            else{
                nearestToPlanet1 = false;
            }
        }
        if(midpoint41.x > midpoint12.x){
            if(playerLocation.x < midpoint41.x && playerLocation.x > 0){
                nearestToPlanet1 = true;
                gravity(planet1.location);
            }
            else{
                nearestToPlanet1 = false;
            }
        }
        if(midpoint12.x < midpoint23.x){
            if(playerLocation.x < midpoint23.x && playerLocation.x > midpoint12.x){
                nearestToPlanet2 = true;
                gravity(planet2.location);
            }
            else{
                nearestToPlanet2 = false;
            }
        }
        if(midpoint12.x > midpoint23.x){
            if(playerLocation.x < midpoint23.x && playerLocation.x > 0){
                nearestToPlanet2 = true;
                gravity(planet2.location);
            }
            else{
                nearestToPlanet2 = false;
            }
        }
        if(midpoint23.x < midpoint34.x){
            if(playerLocation.x < midpoint34.x && playerLocation.x > midpoint23.x) {
                nearestToPlanet3 = true;
                gravity(planet3.location);
            }
            else{
                nearestToPlanet3 = false;
            }
        }
        if(midpoint23.x > midpoint34.x){
            if(playerLocation.x < midpoint34.x && playerLocation.x > 0){
                nearestToPlanet3 = true;
                gravity(planet3.location);
            }
            else{
                nearestToPlanet3 = false;
            }
        }
        if(midpoint34.x < midpoint41.x) {
            if (playerLocation.x < midpoint41.x && playerLocation.x > midpoint34.x){
                nearestToPlanet4 = true;
                gravity(planet4.location);
            }
            else{
                nearestToPlanet4 = false;
            }
        }
        if(midpoint34.x > midpoint41.x) {
            if (playerLocation.x < midpoint41.x && playerLocation.x > 0) {
                nearestToPlanet4 = true;
                gravity(planet4.location);
            }
            else{
                nearestToPlanet4 = false;
            }
        }
    }

    public void gravity(Vector2 planetLocation){
        playerToPlanet = new Vector2(planetLocation.x - playerLocation.x, planetLocation.y - playerLocation.y);
        gravityDirection = playerToPlanet.nor();
        player.velocity.add(gravityDirection.scl(gravityStrength));
        playerLocation.add(player.velocity);
    }

    public void circularMotion(){
        leftTangent = new Vector2(-1 * playerToPlanet.y, playerToPlanet.x);
        rightTangent = new Vector2(playerToPlanet.y, -1 * playerToPlanet.x);
        leftMovement = leftTangent.nor();
        rightMovement = rightTangent.nor();
    }

    public void leftMotion(){
        player.velocity.add(leftMovement.scl(playerSpeed));
    }

    public void rightMotion(){
        player.velocity.add(rightMovement.scl(playerSpeed));
    }
}
