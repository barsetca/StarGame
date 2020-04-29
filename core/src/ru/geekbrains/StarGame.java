package ru.geekbrains;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StarGame extends ApplicationAdapter {
    SpriteBatch batch; //send textures to graphic Processor
    Texture img;
    Texture imgBackground;
    TextureRegion region;
    float x;
    float y;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        imgBackground = new Texture("starsky.jpg");
        region = new TextureRegion(img, 50, 50, 100, 100);
        x = 0;
        y = 0;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        x += 0.5f;
        y += 0.5f;
        batch.begin();
        batch.draw(imgBackground, 0, 0, 645, 483);
        batch.draw(img, x, y);
        batch.draw(region, 100, 100, 200, 200);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
