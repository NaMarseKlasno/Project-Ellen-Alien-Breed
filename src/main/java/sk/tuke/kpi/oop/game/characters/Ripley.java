package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;

import java.util.Objects;

public class Ripley extends AbstractActor implements Movable, Keeper, Alive
{
    private Animation PLAYER;
    private Animation PLAYER_DIE;
    private int SPEED;
    private Health HEALTH;
    //    private int ENERGY;
    private int AMMO;
    private Backpack BACKPACK;
    private Disposable reduceEnergy;

    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("ripley died", Ripley.class);


    public Ripley() {
        super("Ellen");

        this.PLAYER = new Animation("sprites/player.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        this.PLAYER_DIE = new Animation("sprites/player_die.png", 32, 32, 0.1f, Animation.PlayMode.ONCE);
        setAnimation(this.PLAYER);
        this.PLAYER.pause();


        this.SPEED = 2;
//        this.ENERGY = 100;
        this.HEALTH = new Health(100);
        this.HEALTH.onExhaustion(this::onExhaustion);

        this.AMMO = 100;
        this.BACKPACK = new Backpack("Ripley's backpack",10);
    }


    @Override
    public int getSpeed() {
        return this.SPEED;
    }

    @Override
    public void startedMoving(Direction direction) {
        this.PLAYER.setRotation(direction.getAngle());
        this.PLAYER.play();
    }

    @Override
    public void stoppedMoving() {
        this.PLAYER.stop();
    }

    public int getAMMO() {
        return this.AMMO;
    }

    public void setAMMO(int ammo) {
        this.AMMO = ammo;
    }

    @Override
    public Backpack getBackpack() {
        return this.BACKPACK;
    }

    public void showRipleyState(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        getScene().getGame().getOverlay().drawText("Energy " + this.HEALTH.getValue(), 120, yTextPos);
        getScene().getGame().getOverlay().drawText("Ammo " + this.getAMMO(), 320, yTextPos);
    }

    public void reduce_energy()
    {
//        if (this.HEALTH.getValue() <= 0) {
//            onExhaustion();
//            return;
//        }

        this.reduceEnergy = new Loop<>(
            new ActionSequence<>(
                new Wait<>(0.35f),
                new Invoke<>(() -> this.HEALTH.drain(1))
            )
        ).scheduleFor(this);
    }

    private void onExhaustion() {
        setAnimation(this.PLAYER_DIE);
        Objects.requireNonNull(getScene()).cancelActions(this);
//        if (this.reduceEnergy != null) this.reduceEnergy.dispose();
        Objects.requireNonNull(getScene()).getMessageBus().publish(RIPLEY_DIED, this);
    }

    public Disposable getReduceEnergy() {
        return this.reduceEnergy;
    }

    @Override
    public Health getHealth() {
        return this.HEALTH;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.HEALTH.onExhaustion(this::onExhaustion);
    }
}
