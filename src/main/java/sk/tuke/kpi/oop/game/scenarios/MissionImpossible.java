package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
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
//                case "locker":
//                case "ventilator":
//                    return null;
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
        scene.getInput().registerListener(new MovableController(this.ACTOR));
        scene.getInput().registerListener(new KeeperController(this.ACTOR));


    }


    @Override
    public void sceneUpdating(@NotNull Scene scene)
    {
        Ripley ellen= scene.getFirstActorByType(Ripley.class);
        assert ellen != null;
        ellen.showRipleyState(scene);
    }
}
