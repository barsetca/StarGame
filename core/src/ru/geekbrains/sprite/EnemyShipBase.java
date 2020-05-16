package ru.geekbrains.sprite;


import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.BulletPull;
import ru.geekbrains.pool.EnemyShipPull;
import ru.geekbrains.utils.Regions;

public class EnemyShipBase extends Sprite {

    private Rect worldBounds;

    private EnemyShipPull enemyShipPull;
    private TextureRegion enemyShipRegion;
    private Vector2 enemyShipV;

    private float animateTimer;
    private float animateInterval;

    private TextureAtlas atlas;

    private BulletPull bulletPull;
    private Sound sound;

    public EnemyShipBase(TextureAtlas atlas, EnemyShipPull enemyShipPull, BulletPull bulletPull, Sound sound) {
        super(atlas.findRegion("enemy2"), 1, 2, 2);
        this.enemyShipPull = enemyShipPull;
        TextureRegion[] enemyShip = Regions.split(atlas.findRegion("enemy1"), 1, 2, 2);
        enemyShipRegion = enemyShip[0];
        enemyShipV = new Vector2(0, -0.15f);
        animateInterval = 0;
        this.atlas = atlas;
        this.bulletPull = bulletPull;
        this.sound = sound;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(1f);
        setBottom(worldBounds.getHalfHeight());

    }

    @Override
    public void update(float delta) {

        animateTimer += delta;
        if (animateTimer > animateInterval) {
            launch();
            animateTimer = 0;
            animateInterval = Rnd.nextFloat(0, 4f);
        }

    }

    private void launch() {
        EnemyShip enemyShip = enemyShipPull.obtain();
        Vector2 posEnemyShip = new Vector2();
        posEnemyShip.set(Rnd.nextFloat(-getHalfWidth(), getHalfWidth()), getBottom());
        enemyShip.set(this, enemyShipRegion, posEnemyShip, enemyShipV, 0.1f, worldBounds, bulletPull, sound, atlas);
    }
}
