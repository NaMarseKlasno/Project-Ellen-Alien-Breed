package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.*;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import org.jetbrains.annotations.NotNull;

import java.awt.geom.Rectangle2D;


public class Teleport extends AbstractActor
{
    private Animation teleport;

    private Teleport destination;
    private Player player;
    private Rectangle2D player_area;
    private Rectangle2D tp_area;
    private Rectangle2D tp_destination_area;

    private boolean is_teleported;


    public Teleport(Teleport tpshka)
    {
        this.teleport = new Animation("sprites/lift.png");
        setAnimation(this.teleport);
        is_teleported = false;
        destination = null;
        if (tpshka!=null) destination = tpshka;
    }

    public void setDestination(Teleport D) {
        if (D != this && D != null)
            this.destination = D;
    }

    public Teleport getDestination() {
        return destination;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(
            new Invoke<>(this::teleport)
        ).scheduleFor(this);
    }

    public void teleport()
    {
        if (destination == null) return;

        this.player = (Player)getScene().getFirstActorByName("Player");
        this.teleportPlayer(player);
    }

    public void teleportPlayer(Player player)
    {
        if (destination == null || player == null) return;

//        player_area = new Rectangle2D.Float(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
//        tp_area = new Rectangle2D.Float(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
//
        if (!is_teleported && this.intersects(this.player)) {


            player.setPosition(destination.getPosX(), destination.getPosY());
        }
//        tp_destination_area = new Rectangle2D.Float(this.destination.getPosX(), this.destination.getPosY(), this.destination.getWidth(), this.destination.getHeight());

//        player.setPosition((int)tp_destination_area.getCenterX()-8, (int)tp_destination_area.getCenterY()-8);
        else {
            if (this.intersects(this.player)) this.is_teleported = true;
            else this.is_teleported = false;
        }
        System.out.println(this.is_teleported);

    }
}


