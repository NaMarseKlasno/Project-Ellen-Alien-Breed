package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;

public class Ventilator extends AbstractActor implements Repairable
{
    private boolean STATUS;
    public static final Topic<Ventilator> VENTILATOR_REPAIRED = Topic.create("ventilator repaired", Ventilator.class);

    public Ventilator()
    {
        Animation ventilator = new Animation("sprites/ventilator.png",32,32,0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(ventilator);
        ventilator.pause();
        this.STATUS = true;
    }


    @Override
    public boolean repair()
    {
        if (!this.STATUS) return false;

        this.getScene().getMessageBus().publish(VENTILATOR_REPAIRED,this);
        this.getAnimation().play();

        this.STATUS = false;
        return true;
    }

    @Override
    public boolean extinguish() {
        return false;
    }
}
