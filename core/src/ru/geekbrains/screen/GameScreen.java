package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPull;
import ru.geekbrains.pool.EnemyShipPull;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.EnemyShipBase;
import ru.geekbrains.sprite.MainShip;
import ru.geekbrains.sprite.Star;

public class GameScreen extends BaseScreen {

    private Texture bg;
    private Background background;
    private TextureAtlas atlasMenu;
    private TextureAtlas atlasMain;

    private Star[] stars;
    private MainShip mainShip;
    private EnemyShipBase enemyShipBase;
    private BulletPull bulletPull;
    private EnemyShipPull enemyShipPull;

    private Sound sound;
    private Sound soundBullet;
    private Music music;


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
        enemyShipPull = new EnemyShipPull();

        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/laser.wav"));
        soundBullet = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));

        mainShip = new MainShip(atlasMain, bulletPull, sound);
        enemyShipBase = new EnemyShipBase(atlasMain, enemyShipPull, bulletPull, soundBullet);

        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/spaceWalker.mp3"));
        music.play();
        music.setVolume(0.25f);
        music.setLooping(true);


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
        enemyShipBase.resize(worldBounds);

    }

    @Override
    public void dispose() {
        sound.dispose();
        soundBullet.dispose();
        music.dispose();
        atlasMain.dispose();
        atlasMenu.dispose();
        bulletPull.dispose();
        enemyShipPull.dispose();
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
        enemyShipPull.updateActiveSprites(delta);
        enemyShipBase.update(delta);
        mainShip.update(delta);

    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : stars) {
            star.draw(batch);
        }
        bulletPull.drawActiveSprites(batch);
        enemyShipPull.drawActiveSprites(batch);
        mainShip.draw(batch);
        enemyShipBase.draw(batch);
        batch.end();
    }

    private void free() {
        bulletPull.freeAllDestroyed();
        enemyShipPull.freeAllDestroyed();
    }
}