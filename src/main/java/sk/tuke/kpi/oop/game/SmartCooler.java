package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class SmartCooler extends Cooler{
    private Reactor reactor;

    public SmartCooler(Reactor reactor) {
        super(reactor);
        this.reactor=reactor;
    }

    private void isOnline() {
        if(reactor!=null && reactor.getTemperature()>2500 ){
            super.turnOn();
        }
        if(reactor!=null && reactor.getTemperature()==1500 ){
            super.turnOff();
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        //new object of action in Invoke
        new Loop<>(new Invoke<>(this::isOnline)).scheduleFor(this);
    }
}
