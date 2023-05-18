package org.app.documents;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.xquery.XQueryBuilder;
import org.app.Utils;
import org.app.processors.GetEndpointsProcessor;

public class RequestForESB  extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // TODO Auto-generated method stub
//===================   Контракт   ===================================================================================
        from("direct:contract").routeId("RequestContractForESB")
                .split(xpath("//Contract/docs/doc")).process(new GetEndpointsProcessor()).
                transform(XQueryBuilder.xquery(Utils.getFileInputStream("C:/Users/work/Downloads/apache-karaf-4.3.9/deploy/transform/TEST_KIS/contract_xquery.txt"))).
                recipientList(header("receivers"));
//===================   ЗНП  ============================================================================
        from("direct:znp").routeId("RequestZnpForESB").split(xpath("//Znp/values/value")).process(new GetEndpointsProcessor()).
                transform(XQueryBuilder.xquery(Utils.getFileInputStream("C:/Users/work/Downloads/apache-karaf-4.3.9/deploy/transform/TEST_KIS/znp_xquery.txt"))).
                recipientList(header("receivers"));
//===================   МТР   ============================================================================
        from("direct:mtr").routeId("RequestMtrForESB").process(new GetEndpointsProcessor()).
                transform(XQueryBuilder.xquery(Utils.getFileInputStream("C:/Users/work/Downloads/apache-karaf-4.3.9/deploy/transform/TEST_KIS/mtr_xquery.txt"))).
                recipientList(header("receivers"));
//===================   Неизвестный   ==================================================================================
        from("direct:unknown").routeId("RequestUnknownForESB").log("-= send to backout =-").to("jms:backout.queue");
    }
}
