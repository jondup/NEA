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
import java.util.ArrayList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {

    private ArrayList<Planets> planets;
    float captureDistance = 350;

    private Player player;

    ShapeRenderer sr;



    @Override
    public void create() {
        sr = new ShapeRenderer();
        planets = new ArrayList<>();
        planets.add(new Planets(400, 500, -2, 0, 1, 0, 0));
        planets.add(new Planets(1000, 500, -2, 0, 0, 1, 0));
        planets.add(new Planets(1600, 500, -2, 0, 0, 0, 1));
        planets.add(new Planets(2200,500, -2, 0, 0, 0.5f, 0.5f));
        player = new Player(1000, 900);
        player.currentPlanet = planets.get(1);
    }

    @Override
    public void render() {

        ScreenUtils.clear(0, 0, 0, 1);

        sr.begin(ShapeRenderer.ShapeType.Filled);

        for(Planets planet : planets){
            planet.update();
            planet.draw(sr);
        }

        player.update();

        if(player.escaping){
            checkForNewPlanet();
            player.angle = 0;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            player.moveAroundPlanet(-1);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            player.moveAroundPlanet(1);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            player.jump();
        }

        player.draw(sr);

        for(Planets planet : planets){
            if(planet.location.x + planet.rad < 0){
                planet.location.x = Gdx.graphics.getWidth() + planet.rad;
            }
        }

        sr.end();
    }

    public void checkForNewPlanet(){
        for(Planets planet : planets){
            if(player.currentPlanet != planet && player.location.dst(planet.location) < captureDistance){
                player.currentPlanet = planet;
                player.escaping = false;
                return;
            }
        }
    }
}
