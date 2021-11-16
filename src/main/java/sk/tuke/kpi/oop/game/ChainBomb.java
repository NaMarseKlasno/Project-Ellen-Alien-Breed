package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import java.awt.geom.Ellipse2D;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.When;
//import sk.tuke.kpi.gamelib.graphics.Animation;

import java.awt.geom.Rectangle2D;
import java.util.List;
//import java.util.Set;

public class ChainBomb extends TimeBomb {

    private Disposable disposable;

    public ChainBomb(float TIME)
    {
        super(TIME);
    }

    private Ellipse2D.Float ellipseBomb()
    {
        int X = this.getPosX();
        int Y = this.getPosY();
        return new Ellipse2D.Float(X-42, Y-58, 100f, 100f);
    }

    private Rectangle2D.Float check(Actor actor)
    {
        int x = actor.getPosX();
        int y = actor.getPosY();

        int W = actor.getWidth();
        int H = actor.getHeight();
        return new Rectangle2D.Float(x, y-H, W, H);
    }

    private void activatenearby()
    {
        List<Actor> ARR = getScene().getActors();

        for (Actor ACTOR : ARR)
        {
            if ( ACTOR instanceof ChainBomb && (ellipseBomb().contains(check(ACTOR)) || ellipseBomb().intersects(check(ACTOR)) ))
                ((ChainBomb)ACTOR).activate();
        }
    }

    @Override
    public void activate()
    {
        if (isActivated()) return;

        this.disposable = new ActionSequence<>(
            new Invoke<>(this::activateBomb),
            new Invoke<>(this::setTrueStatus),
            new Wait<>(this.getTIME()),
            new Invoke<>(this::activatenearby),
            new Invoke<>(this::explode)
        ).scheduleFor(this);
    }

    @Override
    public void explode()
    {
        setTimeBombExploded();

        this.disposable.dispose();
        this.disposable =
            new When<>(() -> this.getAnimation().getCurrentFrameIndex() >= this.getAnimation().getFrameCount()-1,
            new Invoke <> (() -> getScene().removeActor(this))
        ).scheduleFor(this);
        //this.status = true;
    }
}
