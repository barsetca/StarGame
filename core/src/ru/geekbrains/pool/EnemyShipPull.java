package ru.geekbrains.pool;

import ru.geekbrains.base.SpritesPool;
import ru.geekbrains.sprite.EnemyShip;

public class EnemyShipPull extends SpritesPool<EnemyShip> {

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip();
    }
}
