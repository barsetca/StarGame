package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.StarGame;
import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class PlayGame extends Sprite {


  private float newScale = 0.9f;
  private StarGame starGame;


    public PlayGame(Texture texture, StarGame starGame) {
        super(new TextureRegion(texture, 0, 260, 269, 269));
        this.starGame = starGame;
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.5f);
       this.pos.set(worldBounds.pos);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {

        if(this.isMe(touch)){
            scale = 1f;
            starGame.setScreen(starGame.playScreen);
        } else {
            scale = 1f;
        }
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if(this.isMe(touch)){
            scale = newScale;
        }
        return super.touchDown(touch, pointer, button);
    }
}
