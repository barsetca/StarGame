package ru.geekbrains.sprite;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class Logo extends Sprite {

    private static final float V_LEN = 0.005f;

    private Vector2 v;
    private Vector2 common;
    private Vector2 touch;

    public Logo(Texture texture) {
        super(new TextureRegion(texture));
        v = new Vector2();
        common = new Vector2();
        touch = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.25f);
        this.pos.set(worldBounds.pos);
    }

    @Override
    public void update(float delta) {
        common.set(touch);
        if (common.sub(pos).len() >= V_LEN) {
            pos.add(v);
        } else {
            pos.set(touch.x, touch.y);
            v.setZero();
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        v.set(touch.sub(pos)).setLength(V_LEN);
        return super.touchDown(touch, pointer, button);
    }
}
