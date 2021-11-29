package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.AlienMother;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.*;
import sk.tuke.kpi.oop.game.openables.Door;
//import sk.tuke.kpi.oop.game.openables.LockedDoor;
import sk.tuke.kpi.oop.game.behaviours.Observing;

import java.util.Objects;

public class EscapeRoom implements SceneListener
{
    private Ripley ACTOR;

    public static class Factory implements ActorFactory
    {
        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name)
        {
            assert name != null;

            switch (name)
            {
                case "ellen":
                    return new Ripley();
                case "energy":
                    return new Energy();
                case "front door":
                    return new Door("front door",Door.Orientation.VERTICAL);
                case "exit door":
                    return new Door("exit door",Door.Orientation.VERTICAL);
                case "back door":
                    return new Door("back door",Door.Orientation.HORIZONTAL);
//                case "door":
//                    return new LockedDoor();
                case "access card":
                    return new AccessCard();
                case "locker":
                    return new Locker();
                case "ventilator":
                    return new Ventilator();
                case "ammo":
                    return new Ammo();
                case "alien":
                    if (Objects.equals(type, "running")) return new Alien(50, new RandomlyMoving());
                    if (Objects.equals(type, "waiting1") || Objects.equals(type, "waiting2")) return new Alien(50, new Observing<>( Door.DOOR_OPENED, door -> door.getName().equals("front door")  || door.getName().equals("back door"), new RandomlyMoving()));
                    return new Alien();
                case "alien mother":
                    return new AlienMother();
                default:
                    return null;
            }
        }
    }


    @Override
    public void sceneInitialized(@NotNull Scene scene)
    {
        this.ACTOR = scene.getFirstActorByType(Ripley.class);

        assert this.ACTOR != null;
        scene.follow(this.ACTOR);


        this.ACTOR.getBackpack().add(new Hammer());
        this.ACTOR.getBackpack().add(new AccessCard());
        scene.getGame().pushActorContainer(this.ACTOR.getBackpack());


        Disposable controllerMove = scene.getInput().registerListener(new MovableController(this.ACTOR));
        Disposable controllerKeeper = scene.getInput().registerListener(new KeeperController(this.ACTOR));
        Disposable controllerShooter = scene.getInput().registerListener(new ShooterController(this.ACTOR));

//        scene.getMessageBus().subscribe(Door.DOOR_OPENED, (Ripley) -> ACTOR.reduce_energy());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> controllerMove.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> controllerKeeper.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> controllerShooter.dispose());
        scene.getMessageBus().subscribe(Ventilator.VENTILATOR_REPAIRED, (Ripley) -> ACTOR.getReduceEnergy().dispose());
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene)
    {
        Ripley ellen= scene.getFirstActorByType(Ripley.class);
        assert ellen != null;
        ellen.showRipleyState(scene);
    }

    @Override
    public void sceneCreated(@NotNull Scene scene) {
    }
}
