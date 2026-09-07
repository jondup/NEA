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

    private Player player;

    ShapeRenderer sr;



    @Override
    public void create() {
        sr = new ShapeRenderer();
        planet1 = new Planets(400, 500, -3, 0, 1, 0, 0);
        planet2 = new Planets(1000, 500, -3, 0, 0, 1, 0);
        planet3 = new Planets(1600, 500, -3, 0, 0, 0, 1);
        planet4 = new Planets(2200,500, -3, 0, 0, 0.5f, 0.5f);

        player = new Player(1000, 900);

        player.currentPlanet = planet2;

    }

    @Override
    public void render() {

        ScreenUtils.clear(0, 0, 0, 1);

        sr.begin(ShapeRenderer.ShapeType.Filled);

        planet1.update();
        planet2.update();
        planet3.update();
        planet4.update();

        planet1.draw(sr);
        planet2.draw(sr);
        planet3.draw(sr);
        planet4.draw(sr);

        player.update();

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
}
