package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.Scenario;


public class Gameplay extends Scenario {


    @Override
    public void setupPlay(@NotNull Scene scene) {

        Reactor reactor = new Reactor();  // vytvorenie instancie reaktora
        scene.addActor(reactor, 146, 93);  // pridanie reaktora do sceny na poziciu [x=64, y=64]
        reactor.turnOn();

        Reactor reactor2 = new Reactor();  // vytvorenie instancie reaktora
        scene.addActor(reactor2, 146, 253);  // pridanie reaktora do sceny na poziciu [x=64, y=64]
        reactor2.turnOn();

//        Cooler cooler = new Cooler(reactor);
//        new Wait<>(5).scheduleFor(cooler);
//        new Invoke<>(cooler::turnOn).scheduleFor(cooler);
//
//        new ActionSequence<>(
//            new Wait<>(5),
//            new Invoke<>(cooler::turnOn)
//        ).scheduleFor(cooler);

//        Hammer hammer = new Hammer();
//        //public Invoke(Runnable action)
//        new Invoke<>(new Runnable() {  // zapiseme priamo implementaciu rozhrania
//            public void run() {
//                reactor.repairWith(hammer);
//            }
//        });
//
//        new Invoke<>(() -> {
//            reactor.repairWith(hammer);
//        });
//
//        new When<>(
//            () -> {
//                return reactor.getTemperature() >= 3000;
//            },
//            new Invoke<>(() -> {
//                reactor.repairWith(hammer);
//            })
//        ).scheduleFor(reactor);
//
//        new When<>(
//            () -> reactor.getTemperature() >= 3000,
//            new Invoke<>(() -> reactor.repairWith(hammer))
//        ).scheduleFor(reactor);

    }
}
