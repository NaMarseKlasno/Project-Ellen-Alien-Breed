package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.framework.Player;


public class Helicopter extends AbstractActor {

    private Animation helicopter;
    private Player PLAYER;
    private int heli_x, heli_y;
    private int player_x, player_y;


    public Helicopter()
    {
        this.helicopter = new Animation("sprites/heli.png", 64, 64, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(this.helicopter);

        this.heli_x = 0;
        this.heli_y = 0;
        this.player_x = 0;
        this.player_y = 0;
    }


    private void search_player()
    {
        PLAYER = getScene().getLastActorByType(Player.class);
        heli_x = getPosX();
        heli_y = getPosY();
        player_x = PLAYER.getPosX();
        player_y = PLAYER.getPosY();

        if (heli_x > player_x) --heli_x;
        else if (heli_x != player_x) ++heli_x;

        if (heli_y > player_y) --heli_y;
        else if (heli_y != player_y) ++heli_y;

        setPosition(heli_x, heli_y);

        if (intersects(PLAYER)) {
            int new_energy = PLAYER.getEnergy()-1;
            PLAYER.setEnergy(new_energy);
        }
    }

    public void searchAndDestroy() {
        new Loop<>(new Invoke<>(this::search_player)).scheduleFor(this);
    }
}
