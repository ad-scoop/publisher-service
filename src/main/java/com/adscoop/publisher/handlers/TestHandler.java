package com.adscoop.publisher.handlers;

import com.sun.security.ntlm.Server;
import org.apache.commons.collections4.iterators.ArrayListIterator;
import org.apache.commons.collections4.iterators.IteratorIterable;
import org.reactivestreams.Publisher;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.sse.ServerSentEvents;
import ratpack.stream.Streams;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class TestHandler implements Handler{
    @Override
    public void handle(Context ctx) throws Exception {

        Publisher<String> stringPublisher = Streams.publish(stringList());

        ServerSentEvents serverSentEvents = ServerSentEvents.serverSentEvents(stringPublisher, stringEvent -> stringEvent.id(Objects::toString).event("data").data(  s ->  s));

        ctx.render(serverSentEvents);


    }


    private Iterable<String> stringList()
    {

        List<String> strings = new ArrayList<>();

        strings.add("hello");
        strings.add("world");

        Iterator iterator = new ArrayListIterator(strings);
        Iterable iterable = new IteratorIterable(iterator);

        return  iterable;

    }

}
