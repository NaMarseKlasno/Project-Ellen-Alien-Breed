package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;


public class Door extends AbstractActor implements Openable, Usable<Actor>
{
    private boolean STATUS;

    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);

    private Animation vdoor;
    private Animation hdoor;

    private Orientation DOOR;

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }


    public Door(String name,Orientation orientation)
    {
        super(name);
        this.DOOR = orientation;

        this.vdoor = new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE);
        this.hdoor = new Animation("sprites/hdoor.png", 32, 16, 0.1f, Animation.PlayMode.ONCE);

        if (orientation == Orientation.HORIZONTAL) setAnimation(hdoor);
        else if (orientation == Orientation.VERTICAL) setAnimation(vdoor);

        this.hdoor.pause();
        this.vdoor.pause();
//        this.DOOR.stop();
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

        if (this.DOOR == Orientation.HORIZONTAL) this.hdoor.play();
        if (this.DOOR == Orientation.VERTICAL) this.vdoor.play();
        //this.DOOR.play();
        this.getScene().getMessageBus().publish(DOOR_OPENED, this);
        if (this.DOOR == Orientation.HORIZONTAL) this.hdoor.stop();
        if (this.DOOR == Orientation.VERTICAL) this.vdoor.stop();
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
