package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Openable;
import sk.tuke.kpi.oop.game.items.Usable;


public class Door extends AbstractActor implements Openable, Usable<Actor>
{
    private Animation DOOR;
    private boolean STATUS;

    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);


    public Door() {
        this.DOOR = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE);
        setAnimation(this.DOOR);

        this.DOOR.stop();
        this.STATUS = false;
    }


    @Override
    public void useWith(Actor actor) {
        if (this.STATUS) close();
        else open();
    }

    @Override
    public void open()
    {
        if (this.STATUS || getScene() == null) return;

        this.STATUS = true;

        MapTile title = getScene().getMap().getTile(this.getPosX()/16, this.getPosY()/16);
        title.setType(MapTile.Type.CLEAR);

        MapTile title2 = getScene().getMap().getTile(this.getPosX()/16, this.getPosY()/16+1);
        title2.setType(MapTile.Type.CLEAR);

        this.DOOR.play();
        this.getScene().getMessageBus().publish(DOOR_OPENED, this);
//        this.DOOR.stop();
    }

    @Override
    public void close()
    {
        assert getScene() != null;

        this.STATUS = false;

        MapTile title = getScene().getMap().getTile(this.getPosX()/16, this.getPosY()/16);
        title.setType(MapTile.Type.WALL);

        MapTile title2 = getScene().getMap().getTile(this.getPosX()/16, this.getPosY()/16+1);
        title2.setType(MapTile.Type.WALL);

        this.getScene().getMessageBus().publish(DOOR_CLOSED, this);
    }


    @Override
    public boolean isOpen() {
        return this.STATUS;
    }

    @Override
    public void addedToScene(@NotNull Scene scene)
    {
        super.addedToScene(scene);
        close();
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }
}
