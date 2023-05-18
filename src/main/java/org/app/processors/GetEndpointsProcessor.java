package org.app.processors;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.app.Utils;
import org.app.routing.Endpoint;

public class GetEndpointsProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        String route = exchange.getIn().getHeader("Route", String.class);
        //  get from json  route and endpoints
        String queues = "";

        for (Endpoint endpoint: Utils.getRoutes(route).getEndpoints())
        {
            queues+=endpoint.getQueue()+"," ;
        }

        exchange.getIn().setHeader("receivers", Utils.removeLastChar(queues));

    }
}
