package ru.geekbrains.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.StarGame;
import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.ExitGame;
import ru.geekbrains.sprite.PlayGame;

public class MenuScreen extends BaseScreen {

    private Texture bg;
    private Texture exit;
    private Texture play;
    private Background background;

    private ExitGame exitGame;
    private PlayGame playGame;
    private StarGame starGame;

    public MenuScreen(StarGame starGame) {
        this.starGame = starGame;
    }

    @Override
    public void show() {
        super.show();

        exit = new Texture("menuAtlas.png");
        play = new Texture("menuAtlas.png");
        bg = new Texture("menu-background.jpg");

        background = new Background(bg);
        exitGame = new ExitGame(exit);
        playGame = new PlayGame(play, starGame);
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        exitGame.resize(worldBounds);
        playGame.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        exitGame.draw(batch);
        playGame.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        exit.dispose();
        bg.dispose();
        play.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        exitGame.touchDown(touch, pointer, button);
        playGame.touchDown(touch, pointer, button);
        return super.touchDown(touch, pointer, button);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
        exitGame.touchUp(touch, pointer, button);
        playGame.touchUp(touch, pointer, button);
        return super.touchUp(touch, pointer, button);
    }
}