package sample.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import sample.camel.NumberTest;

public class cloneNumberTest implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        NumberTest body = exchange.getIn().getBody(NumberTest.class);
        NumberTest clone = body.deepClone();
        exchange.getIn().setBody(clone);
    }
}
