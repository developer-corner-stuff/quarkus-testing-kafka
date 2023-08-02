package io.arrogantprogrammer;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

@ApplicationScoped
public class HelloKafkaResource {

    private static final Logger LOGGER = getLogger(HelloKafkaResource.class);

    @Incoming("hello-in")
    @Outgoing("hello-out")
    public String onHelloIn(String helloMessage) {
        LOGGER.debug("{}", helloMessage);
        return helloMessage.toUpperCase();
    }
}
