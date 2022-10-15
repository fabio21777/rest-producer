package sample.camel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import sample.camel.NumberTest;

import java.util.Random;

public class NumberTestProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        Random random = new Random();
        Integer number = random.nextInt(10000);
        NumberTest test;
        test = new NumberTest(number);
        exchange.getIn().setBody(test);
        System.out.println("NumberTestProcessor: " + test.getNumber());
    }
}


