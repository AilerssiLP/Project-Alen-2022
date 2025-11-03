package sk.tuke.kpi.oop.game;

import java.util.Random;
import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Disposable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

public class DefectiveLight extends Light implements Repairable {
    private boolean repaired;
    private Disposable blink;

    public DefectiveLight() {
        super();
    }

    public int random(){
        Random rand = new Random();
        return rand.nextInt(20);
    }

    private void defective(){
        if(this.repaired){
            return;
        }
        if (this.random() == 14) {
            //if (this.random() == 14) {
            this.toggle();
            //}
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);

        //new object of action in Invoke
        blink = new Loop<>(new Invoke<>(this::defective)).scheduleFor(this);
    }

    public boolean repair() {
        //this.turnOn();

        //(new ActionSequence(new Action[]{new Wait(10.0F), new Invoke(() -> {
        //    this.repaired = false;
        //})})).scheduleFor(this);
        //return true;
        if(!this.repaired){
            this.repaired = true;
            new ActionSequence<DefectiveLight>(
                new Invoke<>(() -> {
                    blink.dispose();
                    this.repaired=true;
                    //this.toggle();//new
                }),

                new Wait<>(10),
                new Invoke<>(() -> {
                    blink = new Loop<>(new Invoke<>(this::defective)).scheduleFor(this);
                    this.repaired=false;

                })

            ).scheduleFor(this);
            return true;

        }
        return false;

    }
}
