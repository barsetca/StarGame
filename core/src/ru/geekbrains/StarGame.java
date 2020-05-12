package ru.geekbrains;

import com.badlogic.gdx.Game;

import ru.geekbrains.screen.MenuScreen;
import ru.geekbrains.screen.PlayScreen;

public class StarGame extends Game {
    public MenuScreen menuScreen;
    public PlayScreen playScreen;

    @Override
    public void create() {
        playScreen = new PlayScreen(this);
        menuScreen = new MenuScreen(this);
        setScreen(menuScreen);
    }
}
