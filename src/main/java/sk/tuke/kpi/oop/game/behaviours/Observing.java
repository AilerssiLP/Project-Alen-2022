package sk.tuke.kpi.oop.game.behaviours;

//import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.messages.Topic;

import java.util.function.Predicate;

public class Observing<T,A  extends Actor> implements Behaviour<A>
{

    private Behaviour<A> delegate;

    private Topic<T> topic;

    private Predicate<T> predicate;



    private A actor ;


    public Observing(Topic<T> topic, Predicate<T> predicate, Behaviour<A> delegate)
    {
        this.topic = topic;

        this.predicate = predicate;

        this.delegate = delegate;
    }

    private void test(T t)
    {
        if (actor == null || predicate.test(t) == false)
        {
            return;

        }

        delegate.setUp(actor);

    }

    @Override
    public void setUp(A actor)
    {
        if(actor==null)
        {
            return;

        }

        Scene scene = actor.getScene();

        this.actor =actor;

        if(actor !=null && scene !=null)
        {
            scene.getMessageBus().subscribe(topic,this::test);

        }

    }



}
