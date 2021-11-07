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
//    private Animation teleport;

    private Teleport destination;
    private boolean TELB;
//    private Player player;
//    private Rectangle2D player_area;
//    private Rectangle2D tp_area;

    private boolean is_teleported;


    public Teleport(Teleport tpshka)
    {
        //this.teleport = new Animation("sprites/lift.png");
        setAnimation(new Animation("sprites/lift.png"));
        is_teleported = false;
        destination = null;
        this.TELB = false;
        if (tpshka!=null) destination = tpshka;
    }

    public void setDestination(Teleport D) {
        if (D != this)
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

        Player player = this.getScene().getLastActorByType(Player.class);
        //falseTeleport(player);
        teleportPlayer(player);
        falseTeleport(player);
    }
    public void falseTeleport(Player player) {
        if (is_teleported &&  !this.intersects(player)) {
            this.is_teleported = false;
            this.destination.is_teleported = true;
            //this.is_teleported = false;
        }
    }

    public void teleportPlayer(Player player)
    {
        if (destination == null || player == null) return;

        Rectangle2D player_area = new Rectangle2D.Float(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
        Rectangle2D tp_area = new Rectangle2D.Float(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());

        if (!this.TELB && !this.is_teleported && (tp_area.intersects(player_area.getCenterX(), player_area.getCenterY(), player_area.getWidth()/2, player_area.getHeight()/2)))
        {
            if (player.getPosY() + 16 >= this.getScene().getFirstActorByType(Teleport.class).getPosY() && player.getPosY() + 16 <= this.getScene().getFirstActorByType(Teleport.class).getPosY() + 48 && player.getPosX() + 16 >= this.getScene().getFirstActorByType(Teleport.class).getPosX() && player.getPosX() + 16 <= this.getScene().getFirstActorByType(Teleport.class).getPosX() + 48) {
                this.getScene().getFirstActorByType(Teleport.class).TELB = true;
            }
            //this.is_teleported = true;
            this.is_teleported = true;
            player.setPosition(destination.getPosX()+8, destination.getPosY()+8);
            this.destination.is_teleported = true;
        }
//        if (!is_teleported && this.intersects(this.player)) {
//
//
//            player.setPosition(destination.getPosX(), destination.getPosY());
//        }
//        tp_destination_area = new Rectangle2D.Float(this.destination.getPosX(), this.destination.getPosY(), this.destination.getWidth(), this.destination.getHeight());

//        player.setPosition((int)tp_destination_area.getCenterX()-8, (int)tp_destination_area.getCenterY()-8);
//        else {
//            if (player.getPosX() < this.getPosX()-24 &&
//                player.getPosX() > this.getPosX()+24 &&
//                player.getPosY() < this.getPosY()-24 &&
//                player.getPosY() > this.getPosY()+24) {
//                this.is_teleported = true;
//            }
//            else this.is_teleported = true;
//        }
        //System.out.println(this.is_teleported);

    }
}





//    public void teleportPlayer(Player player)
//    {
//        if (destination == null || player == null) return;
//
//        player_area = new Rectangle2D.Float(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());
//        tp_area = new Rectangle2D.Float(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight());
//
//        if (player.getPosX() > this.getPosX()-24 &&
//            player.getPosX() < this.getPosX()+24 &&
//            player.getPosY() > this.getPosY()-24 &&
//            player.getPosY() < this.getPosY()+24 &&
//            !this.is_teleported)
//        {
//            //this.is_teleported = true;
//            this.is_teleported = true;
//            player.setPosition(destination.getPosX(), destination.getPosY());
//            this.destination.is_teleported = true;
//        }
