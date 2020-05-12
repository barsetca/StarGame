package ru.geekbrains.screen;

import com.badlogic.gdx.graphics.Texture;

import ru.geekbrains.StarGame;
import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.ExitGame;
import ru.geekbrains.sprite.Logo;

public class PlayScreen extends BaseScreen {

    private Texture img;
    private Texture bg;
    private Texture exit;
    private Background background;
    private Logo logo;
    private ExitGame exitGame;

    private StarGame starGame;

    public PlayScreen(StarGame starGame) {
        this.starGame = starGame;
    }

    @Override
    public void show() {
        super.show();
        img = new Texture("invaders-ship.png");
        exit = new Texture("menuAtlas.png");
        bg = new Texture("starsky.jpg");

        background = new Background(bg);
        exitGame = new ExitGame(exit);
        logo = new Logo(img);

    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        exitGame.resize(worldBounds);
        logo.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);

        logo.draw(batch);
        exitGame.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        exit.dispose();
        img.dispose();
        bg.dispose();

        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        exitGame.touchDown(touch, pointer, button);
        logo.touchDown(touch, pointer, button);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        super.touchUp(screenX, screenY, pointer, button);
        exitGame.touchUp(touch, pointer, button);
        return false;
    }
}