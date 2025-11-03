package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.gamelib.map.MapMarker;
import sk.tuke.kpi.oop.game.Cooler;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.items.Hammer;
import sk.tuke.kpi.oop.game.items.Mjolnir;

import java.util.Map;

public class TrainingGameplay extends Scenario {
    @Override
    public void setupPlay(@NotNull Scene scene) {

        Map<String, MapMarker> markers = scene.getMap().getMarkers();
        MapMarker reactorA = markers.get("reactor-area-1");
        MapMarker coolerA = markers.get("cooler-area-1");

        Reactor reactor = new Reactor();
        scene.addActor(reactor, reactorA.getPosX(), reactorA.getPosY());
        reactor.turnOn();
        Cooler cooler = new Cooler(reactor);
        scene.addActor(cooler,coolerA.getPosX(), coolerA.getPosY());

        new ActionSequence<>(
            new Wait<>(5),
            new Invoke<>(cooler::turnOn)
        ).scheduleFor(cooler);

        Hammer hammer = new Hammer();
        scene.addActor(hammer,145,63);
        Mjolnir mjolnir = new Mjolnir();
        scene.addActor(mjolnir,145,63);

        new When<>(
            () -> reactor.getTemperature() >= 3000,
            new Invoke<>(() -> hammer.useWith(reactor))
        ).scheduleFor(reactor);
    }
}
