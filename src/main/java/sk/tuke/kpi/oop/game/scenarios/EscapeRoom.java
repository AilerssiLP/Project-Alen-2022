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
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

import java.util.Objects;

public class EscapeRoom implements SceneListener {
    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        Ripley ripley = scene.getFirstActorByType(Ripley.class);
        if(ripley==null) return;
        Disposable movableCon = scene.getInput().registerListener(new MovableController(ripley));
        Disposable keeperCon = scene.getInput().registerListener(new KeeperController(ripley));
        Disposable shooterCon = scene.getInput().registerListener(new ShooterController(ripley));

        scene.getGame().pushActorContainer(ripley.getBackpack());

        scene.follow(ripley);
        Disposable shooter = scene.getInput().registerListener(new ShooterController(ripley));

        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> movableCon.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> keeperCon.dispose());
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, (Ripley) -> shooterCon.dispose());

    }

    public static class Factory implements ActorFactory {

        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            if(name.contains("ellen")) return new Ripley();
            else if (name.contains("energy")) return new Energy();
            else if (name.contains("door")) return new LockedDoor(name,getDoorOrientation(type));
            else if (name.contains("locker")) return new Locker();
            else if (name.contains("ventilator")) return new Ventilator();
            else if (name.contains("access card")) return new AccessCard();
            else if (name.contains("alien")) return new Alien(100,new RandomlyMoving());
            else if (name.contains("alienmother")) return new AlienMother(200,new RandomlyMoving());
            else if (name.contains("ammo")) return new Ammo();
            return null;
        }


        private Door.Orientation getDoorOrientation(String type) {
            return Objects.equals(type, "horizontal") ? Door.Orientation.HORIZONTAL : Door.Orientation.VERTICAL;
        }
    }
    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        Ripley ellen= scene.getFirstActorByType(Ripley.class);
        assert ellen != null;
        ellen.showRipleyState();
    }
}
