package ru.geekbrains.sprite;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class ExitGame extends Sprite {

    private Vector2 touch;
  private float newScale = 0.9f;

    public ExitGame(Texture texture) {
        super(new TextureRegion(texture, 5, 5, 265, 265));
        touch = new Vector2();
        }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.15f);
        setBottom(-worldBounds.getHalfHeight());
        setRight(worldBounds.getHalfWidth());
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {

        if(this.isMe(touch)){
            scale = 1f;
            Gdx.app.exit();
        } else {
            scale = 1f;
        }
        return super.touchDown(touch, pointer, button);
    }


    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if(this.isMe(touch)){
            this.scale = newScale;

        }
        return super.touchDown(touch, pointer, button);

    }
}
