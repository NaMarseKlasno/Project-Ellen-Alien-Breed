package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import java.awt.geom.Ellipse2D;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
//import sk.tuke.kpi.gamelib.graphics.Animation;

import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//import java.util.Set;

public class ChainBomb extends TimeBomb {

    //private Animation timeBomb_on;
//    private List<Actor> ARR;
    //private float TIME;
    private Set<ChainBomb> ARR;


    public ChainBomb(Float TIME)
    {
        super(TIME);
        this.ARR = new HashSet<>();

        //this.timeBomb_on = new Animation("sprites/bomb_activated.png", 16, 16, 0.2f);
    }


    private void activate_nearby()
    {
        //Set<ChainBomb> ARR = getScene().getActors();

        float X = (float)this.getPosX() - 50f, Y = (float)this.getPosY() - 50f;

        //        this.X = (float)this.getPosX() - 50f;
//        this.Y = (float)this.getPosY() - 50f;

        Ellipse2D.Float ChainBomb_ellipse = new Ellipse2D.Float(X, Y, 100f, 100f);

        for (Actor ACTOR : ARR)
        {
            if (ACTOR instanceof ChainBomb)
            {
                X = ACTOR.getPosX() - (float)ACTOR.getWidth() / 2;
                Y = ACTOR.getPosY() - (float)ACTOR.getHeight() / 2;

                Rectangle2D other_bomb = new Rectangle2D.Float(X, Y, ACTOR.getWidth(),ACTOR.getHeight());

                if (ChainBomb_ellipse.contains(other_bomb) || ChainBomb_ellipse.intersects(other_bomb))
                    ((ChainBomb)ACTOR).activate();
            }
        }
    }

    @Override
    public void activate()
    {
        if (isActivated()) return;

        //setAnimation(timeBomb_on);

        new ActionSequence<>(
            new Invoke<>(this::activateBomb),
            new Invoke<>(this::activate_nearby),
            new Wait<>(this.getTIME()),
            new Invoke<>(this::explode),
            new Wait<>(0.3f*8f),
            new Invoke<>(this::remove)
        ).scheduleFor(this);
    }


    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new ActionSequence<>(
            new Invoke<>(this::newbomb)
        )).scheduleFor(this);
    }

    private void newbomb() {
        for (Actor BOMB : getScene().getActors()) {
            if (BOMB instanceof ChainBomb && BOMB != this) ARR.add((ChainBomb)BOMB);
        }
    }
}
