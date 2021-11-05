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

        player_area = new Rectangle2D.Float(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
        tp_area = new Rectangle2D.Float(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());

        //if (!this.is_teleported) {
            //if (!this.destination.intersects(player)) return;
        if (!tp_area.intersects(player_area.getCenterX(), player_area.getCenterY(), player_area.getWidth(), player_area.getHeight())) return;
        if (this.is_teleported) {
            this.is_teleported = (
                (player.getPosX()+16 >= this.getPosX() &&
                 player.getPosX()+16 <= this.getPosX()+48) &&
                (player.getPosY()+16 >= this.getPosY() &&
                 player.getPosY()+16 <= this.getPosY()+48)
            );
            return;
        }
        tp_destination_area = new Rectangle2D.Float(this.destination.getPosX(), this.destination.getPosY(), this.destination.getWidth(), this.destination.getHeight());

        player.setPosition((int)tp_destination_area.getCenterX()-8, (int)tp_destination_area.getCenterY()-8);

        //player.setPosition(this.destination.getPosX()+24, this.destination.getPosY()+24);
        this.is_teleported = true;

            //this.Area = new Rectangle2D.Float(this.destination.getPosX(), this.destination.getPosY(), this.destination.getWidth(), this.destination.getHeight());
//        } else {
//            //teleportArea = new Rectangle2D.Float(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
//            //playerArea = new Rectangle2D.Float(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
//
//            //this.is_teleported = true;
//
//            if (!teleportArea.intersects(playerArea.getCenterX(), playerArea.getCenterY(), playerArea.getWidth(), playerArea.getHeight())) {
//                this.is_teleported = false;
//            }
//        }

//        System.out.println(this.is_teleported);
//
//        System.out.println("-------");
//
//        System.out.println(this.getPosX());
//        System.out.println(this.destination.getPosX());
//
//        System.out.println("-------");

//        if (this.is_teleported) {
//                this.is_teleported = (
//                    (player.getPosX()+16 >= this.getPosX() &&
//                     player.getPosX()+16 <= this.getPosX()+48) &&
//                    (player.getPosY()+16 >= this.getPosY() &&
//                     player.getPosY()+16 <= this.getPosY()+48)
//                );
//                return;
//            }
    }
}


