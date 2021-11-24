package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

import java.util.Objects;

public class Move<A extends Movable> implements Action<Movable>
{
    private Movable actor;
    private float duration;
    private Direction direction;
    private int amount;
    private float oldDuration;


    public Move(Direction direction, float duration) {
        // implementacia konstruktora akcie
        this.duration = duration;
        this.direction = direction;
        this.amount = 0;
        this.oldDuration = duration;
    }


    @Override
    public @Nullable Movable getActor() {
        return this.actor;
    }

    @Override
    public void setActor(@Nullable Movable actor) {
        this.actor = actor;
    }

    @Override
    public boolean isDone() {
        if (this.duration <= 1e-5 && this.amount >= 1) return true;
        return false;
    }

    @Override
    public void execute(float deltaTime)
    {
        if (this.actor == null || this.isDone()) return;

        this.duration -= deltaTime;

        if (this.amount == 0) {
            this.actor.startedMoving(this.direction);
            this.amount++;
        }

        actor.setPosition(actor.getPosX()+(direction.getDx()*actor.getSpeed()), actor.getPosY()+(direction.getDy()*actor.getSpeed()));

        if (actor.getScene().getMap().intersectsWithWall(actor)) {
            int newX = actor.getPosX() - direction.getDx() * actor.getSpeed();
            int newY = actor.getPosY() - direction.getDy() * actor.getSpeed();
            actor.setPosition(newX, newY);
//            actor.setPosition(actor.getPosX(), actor.getPosY());
//            this.stop();
        } if (this.duration <= 1e-5) this.stop();
    }

    @Override
    public void reset() {
        this.duration = this.oldDuration;
        this.amount = 0;
    }

    public void stop()
    {
        this.duration = 0;
        this.amount = 1;

        if (this.actor != null) {
            this.actor.stoppedMoving();
        }
    }
}
