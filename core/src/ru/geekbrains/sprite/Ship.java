package ru.geekbrains.sprite;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class Ship extends Sprite {

    private static final float V_LEN = 0.005f;

    private Vector2 v;
    private Vector2 v2;
    private Vector2 common;
    private Vector2 touch;

    private Rect worldBounds;

    public Ship(TextureRegion textureShip) {
        super(new TextureRegion(textureShip));
//        TextureRegion textureRegion = atlas.findRegion("main_ship").;
//        textureRegion.setRegion(0 , 0 , 200, 200);

        v = new Vector2();
        v2 = new Vector2();
        common = new Vector2();
        touch = new Vector2();
        worldBounds = new Rect();
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(0.15f);
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
        //if (touchKey) {
//            pos.add(v2);
        // }
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

    @Override
    public boolean keyDown(int keycode) {
        System.out.println(getHalfWidth());
        switch (keycode) {
            case 19:
                touch.set(pos.x, worldBounds.getTop() - getHalfHeight());
                v.set(touch.cpy().sub(pos)).setLength(V_LEN);
                break;
            case 20:
                touch.set(pos.x, worldBounds.getBottom() + getHalfHeight());
                v.set(touch.cpy().sub(pos)).setLength(V_LEN);
                break;
            case 21:
                touch.set(worldBounds.getLeft() + getHalfWidth(), pos.y);
                v.set(touch.cpy().sub(pos)).setLength(V_LEN);
                break;
            case 22:
                touch.set(worldBounds.getRight() - getHalfWidth(), pos.y);
                v.set(touch.cpy().sub(pos)).setLength(V_LEN);
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == 19 || keycode == 20 || keycode == 21 || keycode == 22) {
            v.setZero();
        }
        return false;
    }
}
