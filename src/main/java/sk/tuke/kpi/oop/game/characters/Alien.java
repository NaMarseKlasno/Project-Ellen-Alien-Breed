package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;

import java.util.List;
import java.util.Objects;

public class Alien extends AbstractActor implements Movable, Enemy, Alive
{
    private Health HEALTH;
    private Behaviour<? super Alien> behaviour;


    public Alien()
    {
        setAnimation(new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
        this.HEALTH = new Health(100);
    }

    public Alien(int HEALTH, Behaviour<? super Alien> behaviour)
    {
        setAnimation(new Animation("sprites/alien.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
        this.HEALTH = new Health(HEALTH);
        this.behaviour = behaviour;
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public Health getHealth() {
        return this.HEALTH;
    }

    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);
        if (this.behaviour != null) this.behaviour.setUp(this);

        new Loop<>(
            new Invoke<>(() -> killAlien(getHealth().getValue()))
        ).scheduleFor(this);

        new Loop<> (
            new ActionSequence <> (
//                new Invoke <> (this::test),
                new Invoke <> (() -> drainHealthAlien(Objects.requireNonNull(this.getScene()).getActors())),
                new Wait   <> (.04f)
            )
        ).scheduleFor(this);
    }

    private void drainHealthAlien(List<Actor> ACTORS)
    {
        for (Actor actor : ACTORS)
            if (actor instanceof Alive && !(actor instanceof Enemy) && this.intersects(actor))
                ((Alive) actor).getHealth().drain(1);
    }

    private void killAlien(int health) {
        if (health > 0) return;
        Objects.requireNonNull(this.getScene()).removeActor(this);
    }

//    private void test() {
//        getHealth().drain(1);
//    }
}
