package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {
    private Texture img;
    private Vector2 pos;
    private Vector2 v;
    private Vector2 gravity;
    private Vector2 touch;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        pos = new Vector2();
        v = new Vector2(1, 1);
        gravity = new Vector2(0, -0.005f);
        touch = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        pos.add(v);
        v.add(gravity);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
    }

    @Override
    public void dispose() {
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        pos.set(touch);
        System.out.println("touchDown touch.x= " + touch.x + " touch.y= " + touch.y);
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
