package ru.geekbrains.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.BulletPull;

public class EnemyShip extends Sprite {
    private Rect worldBounds;
    private Vector2 v;
    private Sprite owner;

    private BulletPull bulletPull;
    private TextureRegion bulletRegion;
    private Vector2 bulletV;

    private float animateTimer;
    private float animateInterval;

    private Sound sound;

    public EnemyShip() {
        regions = new TextureRegion[1];
        v = new Vector2();
        bulletV = new Vector2(0, -0.3f);
        animateInterval = 0;
    }

    @Override
    public void update(float delta) {
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
        }
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
        }
        pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
        animateTimer += delta;
        if (animateTimer > animateInterval) {
            shoot();
            sound.play(0.5f);
            animateTimer = 0;
            animateInterval = Rnd.nextFloat(0, 3f);
        }
    }

    public void set(Sprite owner, TextureRegion region, Vector2 pos0, Vector2 v0, float height,
                    Rect worldBounds, BulletPull bulletPull, Sound sound, TextureAtlas atlas) {
        this.owner = owner;
        this.regions[0] = region;
        this.pos.set(pos0);
        this.v.set(v0);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.bulletPull = bulletPull;
        bulletRegion = atlas.findRegion("bulletEnemy");
        this.sound = sound;

    }


    public Sprite getOwner() {
        return owner;
    }

    private void shoot() {
        Bullet bullet = bulletPull.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, 0.01f, worldBounds, 1);
    }
}
