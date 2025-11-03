package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
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

public class MissionImpossible implements SceneListener {
    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        Ripley ripley = scene.getFirstActorByType(Ripley.class);
        if(ripley==null) return;
        scene.getInput().registerListener(new MovableController(ripley));
        scene.getInput().registerListener(new KeeperController(ripley));
        scene.getGame().pushActorContainer(ripley.getBackpack());
        Disposable shooter = scene.getInput().registerListener(new ShooterController(ripley));

        scene.follow(ripley);
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
