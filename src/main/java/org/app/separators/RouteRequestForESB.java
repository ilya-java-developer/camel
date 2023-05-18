package org.app.separators;

import org.apache.camel.builder.RouteBuilder;

public class RouteRequestForESB extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jms:kis.req.in.queue").routeId("SeparatorRequestsForESB").setHeader("Route").xquery("/local-name(/*)", String.class)
        .choice()
                .when(simple("${header.Route} == 'Contract'"))
                .to("direct:contract")
                .when(simple("${header.Route} == 'Znp'"))
                .to("direct:znp")
                .when(simple("${header.Router} == 'MTR'"))
                .to("direct:mtr")
                .otherwise()
                .to("direct:unknown");
    }
}
