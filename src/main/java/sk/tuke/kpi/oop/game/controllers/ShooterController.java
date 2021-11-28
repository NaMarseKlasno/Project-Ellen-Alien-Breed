package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.actions.Fire;
import sk.tuke.kpi.oop.game.characters.Armed;

public class ShooterController implements KeyboardListener {

    private Fire<Armed> BULLET;
    private Armed ARMED;


    public ShooterController(Armed ARMED){
        this.ARMED = ARMED;
    }


    @Override
    public void keyPressed(@NotNull Input.Key key)
    {
        switch (key) {
            case SPACE:
                this.BULLET = new Fire<>();
                this.BULLET.scheduleFor(ARMED);
                break;
            default:
                break;
        }
    }
}
