package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.backends.lwjgl.LwjglBackend;
import sk.tuke.kpi.oop.game.scenarios.EscapeRoom;
//import sk.tuke.kpi.oop.game.scenarios.FirstSteps;
import sk.tuke.kpi.oop.game.scenarios.MissionImpossible;

public class Main {
    public static void main(String[] args) {
        // nastavenie okna hry: nazov okna a jeho rozmery
        WindowSetup windowSetup = new WindowSetup("Project Ellen", 800, 600);

        // vytvorenie instancie hernej aplikacie
        // pouzijeme implementaciu rozhrania `Game` triedou `GameApplication`
        Game game = new GameApplication(windowSetup, new LwjglBackend());  // v pripade Mac OS bude druhy parameter "new Lwjgl2Backend()"





        //Scene scene = new World("Doom","maps/escape-room.tmx",new EscapeRoom.Factory());
        Scene scene = new World("Doom","maps/mission-impossible.tmx",new MissionImpossible.Factory());

        //pridanie listenera
        //FirstSteps firstSteps = new FirstSteps();
        //scene.addListener(firstSteps);

        MissionImpossible missionImpossible =new MissionImpossible();
        scene.addListener(missionImpossible);

        // pridanie sceny do hry
        game.addScene(scene);

        //ukoncenie hry na esc
        scene.getInput().onKeyPressed(Input.Key.ESCAPE,game::stop);

        // spustenie hry
        game.start();
    }
}
