package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
//import sk.tuke.kpi.gamelib.actions.ActionSequence;
//import sk.tuke.kpi.gamelib.actions.Invoke;
//import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
//import sk.tuke.kpi.gamelib.map.MapMarker;
//import sk.tuke.kpi.oop.game.Cooler;
//import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.*;

public class FirstSteps implements SceneListener {

    private Ripley ripley;

    @Override
    public void sceneInitialized(@NotNull Scene scene) {//zac hry
        //Map<String, MapMarker> markers = scene.getMap().getMarkers();
        //MapMarker reactorA = markers.get("reactor-area-1");
        //MapMarker coolerA = markers.get("cooler-area-1");

//        Reactor reactor = new Reactor();
//        scene.addActor(reactor, 64, 64);
//        reactor.turnOn();
//
//        Cooler cooler = new Cooler(reactor);
//        scene.addActor(cooler,32, reactor.getPosY());
//        new ActionSequence<>(
//            new Wait<>(5),
//            new Invoke<>(cooler::turnOn)
//        ).scheduleFor(cooler);


        Ripley ripley = new Ripley();

        Hammer hammer = new Hammer();
        //Hammer hammer1 = new Hammer();
        scene.addActor(hammer,145,63);
        //scene.addActor(hammer1,145,67);
        Wrench wrench = new Wrench();
        scene.addActor(wrench,155,63);
//        Mjolnir mjolnir = new Mjolnir();
//        scene.addActor(mjolnir,145,63);
//
//        new When<>(
//            () -> reactor.getTemperature() >= 3000,
//            new Invoke<>(() -> hammer.useWith(reactor))
//        ).scheduleFor(reactor);


        scene.addActor(ripley,0,0);
        scene.getInput().registerListener(new MovableController(ripley));
        scene.getInput().registerListener(new KeeperController(ripley));

        Energy energy = new Energy();
        scene.addActor(energy,115,63);
        //ripley.

        new When<>(
            () -> ripley.intersects(energy),
            new Use<>(energy)
        ).scheduleFor(ripley);

        Ammo ammo = new Ammo();
        scene.addActor(ammo,155,63);
        ripley.setAmmo(50);

        new When<>(
            () -> ripley.intersects(ammo),
            new Use<>(ammo)
        ).scheduleFor(ripley);

        //ripley.getBackpack(hammer);
        //backpack = ripley.getBackpack();

        scene.getGame().pushActorContainer(ripley.getBackpack());

    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        //Ripley rip = scene.getFirstActorByType(Ripley.class);

        Ripley rip = (Ripley) scene.getFirstActorByName("Ailerko");

        int windowH = scene.getGame().getWindowSetup().getHeight();

        int hText = windowH - GameApplication.STATUS_LINE_OFFSET;

        scene.getGame().getOverlay().drawText("| Energy: " + rip.getHealth(),100,hText);
        scene.getGame().getOverlay().drawText("| Ammo : " + rip.getAmmo(),250,hText);
    }
}
