package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;

import java.util.Objects;

public class Bullet extends AbstractActor implements Fireable
{
    private int SPEED;

    public Bullet() {
        setAnimation(new Animation("sprites/bullet.png", 16, 16));
        this.SPEED = 0;
    }

    @Override
    public int getSpeed() {
        return this.SPEED;
    }

    @Override
    public void startedMoving(Direction direction) {
        if (direction == null || direction == Direction.NONE) return;

        this.getAnimation().setRotation(direction.getAngle());
        this.SPEED = 4;
    }

    @Override
    public void collidedWithWall() {
        Objects.requireNonNull(getScene()).removeActor(this);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(
            new Invoke<>(() -> {
                for (Actor actor : Objects.requireNonNull(getScene()).getActors()) {
                    if (this.intersects(actor) && (actor instanceof Alive)) {
                        ((Alive) actor).getHealth().drain(10);
                        Objects.requireNonNull(getScene()).removeActor(this);
                    }
                }
            })
        ).scheduleFor(this);

//        new Loop<>(
//            new Invoke<>(() -> {
//                for (Actor actor : Objects.requireNonNull(getScene()).getActors()) {
//                    if (actor.getScene().getMap().intersectsWithWall(actor)) {
//                        collidedWithWall();
//                    }
//                }
//            })
//        ).scheduleFor(this);
    }
}
