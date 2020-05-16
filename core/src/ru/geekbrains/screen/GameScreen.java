package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPull;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.MainShip;
import ru.geekbrains.sprite.Star;

public class GameScreen extends BaseScreen {

    private Texture bg;
    private Background background;
    private TextureAtlas atlasMenu;
    private TextureAtlas atlasMain;
    private Star[] stars;
    private MainShip mainShip;
    private BulletPull bulletPull;


    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);
        atlasMenu = new TextureAtlas(Gdx.files.internal("textures/menuAtlas.tpack"));
        stars = new Star[64];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlasMenu);
        }
        atlasMain = new TextureAtlas("textures/mainAtlas.tpack");
        bulletPull = new BulletPull();
        mainShip = new MainShip(atlasMain, bulletPull);


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        free();
        draw();
    }

    @Override
    public void resize(Rect worldBounds) {
                background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }
                mainShip.resize(worldBounds);

    }

    @Override
    public void dispose() {
        atlasMain.dispose();
        atlasMenu.dispose();
        bulletPull.dispose();
        bg.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        mainShip.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        mainShip.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
        bulletPull.updateActiveSprites(delta);
        mainShip.update(delta);

    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        bulletPull.drawActiveSprites(batch);
        mainShip.draw(batch);
        batch.end();
    }

    private void free(){
        bulletPull.freeAllDestroyed();
    }
}