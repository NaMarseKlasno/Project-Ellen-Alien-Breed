package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;

public class Ripley extends AbstractActor implements Movable, Keeper<Actor>
{
    private Animation PLAYER;
    private int SPEED;
    private int ENERGY;
    private int AMMO;
    private Backpack BACKPACK;


    public Ripley() {
        super("Ellen");

        this.PLAYER = new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(this.PLAYER);

        this.SPEED = 2;
        this.ENERGY = 100;
        this.AMMO = 100;
        this.BACKPACK = new Backpack("Ripley's backpack",10);
    }


    @Override
    public int getSpeed() {
        return this.SPEED;
    }

    @Override
    public void startedMoving(Direction direction) {
        this.PLAYER.setRotation(direction.getAngle());
        this.PLAYER.play();
    }

    @Override
    public void stoppedMoving() {
        this.PLAYER.pause();
    }

    public int getEnergy() {
        return this.ENERGY;
    }

    public void setEnergy(int SET) {
        this.ENERGY = SET;
    }

    public int getAMMO() {
        return this.AMMO;
    }

    public void setAMMO(int ammo) {
        this.AMMO = ammo;
    }

    @Override
    public Backpack getBackpack() {
        return this.BACKPACK;
    }
}
