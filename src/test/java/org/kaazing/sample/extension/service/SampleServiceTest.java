/**
 * Copyright 2007-2016, Kaazing Corporation. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kaazing.sample.extension.service;

import org.junit.Rule;
import org.junit.Test;
import org.kaazing.gateway.server.test.GatewayRule;
import org.kaazing.gateway.server.test.config.GatewayConfiguration;
import org.kaazing.gateway.server.test.config.builder.GatewayConfigurationBuilder;

public class SampleServiceTest {

    @Rule
    public GatewayRule gateway = new GatewayRule() {
        {
            // @formatter:off
            GatewayConfiguration configuration = new GatewayConfigurationBuilder()
                .service()
                    .name("warrior promotion service")
                    .type("sample.service-extension")
                    .property("what-to-say", "Go Warriors")
                    // for this we will use websocket, see implementation of doMessageRecieve
                    // in SampleServiceHandler where we assume it is websocket
                    .accept("ws://localhost:8000")
                    .crossOrigin()
                         // for testing purpose allow any cross origin
                        .allowOrigin("*")
                    .done()
                .done()
            .done();
            // @formatter:on
            init(configuration);
        }
    };

    @Test
    public void testViaEchoWebSocketOrg() throws InterruptedException{
        System.out.println("Go to http://www.websocket.org/echo.html");
        System.out.println("Change url to ws://localhost:8000/");
        System.out.println("Write a message");
        System.out.println("Root for the warriors in the 2016 playoffs");
        Thread.sleep(60000);
    }
}
