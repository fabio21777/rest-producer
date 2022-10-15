/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.camel.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;

import org.springframework.stereotype.Component;
import sample.camel.NumberTest;
import sample.camel.Timer;
import sample.camel.processors.NumberTestProcessor2;
import sample.camel.processors.cloneNumberTest;

import java.util.Random;

/**
 * A Camel route that calls the REST service using a timer
 * <p/>
 * Use <tt>@Component</tt> to make Camel auto detect this route when starting.
 */
@Component
public class RestRoute extends RouteBuilder {
	JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Timer.class);
	JacksonDataFormat jsonDataFormatNumber = new JacksonDataFormat(NumberTest.class);


	@Override
	public void configure() throws Exception {
		restConfiguration().host("localhost").port(8080);

		from("timer:rest?period=10000").setHeader("id",simple("${random(6,9)}" ))
				.to("rest:get:/random/teste/{id}")
				.unmarshal(jsonDataFormatNumber)
					.multicast().onPrepare(new cloneNumberTest())
							.to("direct:a", "direct:b")
				    .end();




		from("direct:a").log("aqui é o valor  ${body}");


		from ("direct:b").process(new NumberTestProcessor2()).marshal(jsonDataFormatNumber)

				.to("rest:post:evenOrOdd").log("${body}");


//		from("timer://timer?period=5000")
//				.process(new NumberTestProcessor()).marshal(jsonDataFormatNumber)
//				.to("rest:post:evenOrOdd")
//						.log("${body}");

//		from("timer:timer1?period={{timer.period}}").setHeader("id", simple("${random(6,9)}"))
//				.to("rest:get:random/{id}").log("${body}");
//
//		from("timer://timer2?period={{timer.period}}").setBody().simple("Current time is ${header.firedTime}")
//				.process(new TimerProcessor()).marshal(jsonDataFormat).to("rest:post:time").log("${body}");

	}

}
