package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.*;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class MissionImpossible implements SceneListener
{
    Ripley ACTOR;

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
                case "door":
                    return new LockedDoor();
                case "access card":
                    return new AccessCard();
                case "locker":
                    return new Locker();
                case "ventilator":
                    return new Ventilator();
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

//        Disposable controllerMove = new MovableController(this.ACTOR);
//        Disposable controllerKeeper = new KeeperController(this.ACTOR);

        Disposable controllerMove = scene.getInput().registerListener(new MovableController(this.ACTOR));
        Disposable controllerKeeper = scene.getInput().registerListener(new KeeperController(this.ACTOR));

//        FireExtinguisher fireExtinguisher= new FireExtinguisher();
//        this.ACTOR.getBackpack().add(fireExtinguisher);
        this.ACTOR.getBackpack().add(new Hammer());
        scene.getGame().pushActorContainer(this.ACTOR.getBackpack());

        AccessCard accessCard = new AccessCard();
        this.ACTOR.getBackpack().add(accessCard);

        scene.getMessageBus().subscribe(Door.DOOR_OPENED, (Ripley) -> ACTOR.reduce_energy());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> controllerMove.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> controllerKeeper.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> ACTOR.getReduceEnergy().dispose());
        scene.getMessageBus().subscribe(Ventilator.VENTILATOR_REPAIRED, (Ripley) -> ACTOR.getReduceEnergy().dispose());
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene)
    {
        Ripley ellen= scene.getFirstActorByType(Ripley.class);
        assert ellen != null;
        ellen.showRipleyState(scene);
    }
}
