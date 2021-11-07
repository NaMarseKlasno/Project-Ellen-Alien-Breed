package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.Scenario;


public class Gameplay extends Scenario {


    @Override
    public void setupPlay(@NotNull Scene scene) {

        ChainBomb B1 = new ChainBomb(4f);
        scene.addActor(B1, 40, 50);

        ChainBomb B2 = new ChainBomb(4f);
        scene.addActor(B2, 40, 100);

        ChainBomb B3 = new ChainBomb(4f);
        scene.addActor(B3, 40, 150);

        ChainBomb B4 = new ChainBomb(4f);
        scene.addActor(B4, 40, 200);

        ChainBomb B5 = new ChainBomb(4f);
        scene.addActor(B5, 40, 250);



        ChainBomb B6 = new ChainBomb(4f);
        scene.addActor(B6, 200, 95);

        ChainBomb B7 = new ChainBomb(4f);
        scene.addActor(B7, 125, 70);

        //x=200, y=95
//        Reactor reactor = new Reactor();  // vytvorenie instancie reaktora
//        scene.addActor(reactor, 146, 93);  // pridanie reaktora do sceny na poziciu [x=64, y=64]
//        reactor.turnOn();
//
//        Reactor reactor2 = new Reactor();  // vytvorenie instancie reaktora
//        scene.addActor(reactor2, 146, 253);  // pridanie reaktora do sceny na poziciu [x=64, y=64]
//        reactor2.turnOn();
//
//        Teleport T1 = new Teleport(null);
//        scene.addActor(T1, 50, 50);
//
//        Teleport T2 = new Teleport(null);
//        scene.addActor(T2, 50, 130);
//
//        Teleport T3 = new Teleport(null);
//        scene.addActor(T3, 50, 330);
//
//
//        T1.setDestination(T3);
//        T2.setDestination(T3);
        //T3.getDestination(null);


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
