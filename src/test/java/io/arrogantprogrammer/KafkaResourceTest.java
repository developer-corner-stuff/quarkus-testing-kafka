package io.arrogantprogrammer;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;
import io.smallrye.reactive.messaging.memory.InMemorySink;
import io.smallrye.reactive.messaging.memory.InMemorySource;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest @QuarkusTestResource(KafkaResourceTestResource.class)
public class KafkaResourceTest {

    @Inject
    @Any
    InMemoryConnector connector;
    @Test
    public void testHelloMessage() {

        InMemorySource<String> helloIn = connector.source("hello-in");
        InMemorySink<String> helloOut = connector.sink("hello-out");

        helloIn.send("Hi, there!");
        assertEquals("HI, THERE!", helloOut.received().get(0).getPayload());
    }
}
