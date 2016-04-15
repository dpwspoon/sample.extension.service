# sample.extension.service

Sample Extension Service for Kaazing Gateway

# Instructions

0. Build:

    `$ mvn clean install`

0. Put the service jar into an existing Gateway installation:

    `$ cp target/sample.extension.service-N.N.N-SNAPSHOT.jar GATEWAY_HOME/lib`
    
    where `GATEWAY_HOME` is the location of your Gateway installation.

0. Add a new service into your `GATEWAY_HOME/conf/gateway-config.xml` configuration:

    ```
    <service>
      <name>sample</name>
      <description>Sample service where you say what it says</description>
      <accept>ws://localhost:8000/sample</accept>

      <type>sample.service-extension</type>

      <properties>
        <what-to-say>Go Warriors!</what-to-say>
      </properties>

      <cross-site-constraint>
        <allow-origin>*</allow-origin>
      </cross-site-constraint>
    </service>
    ```
0. Restart your Gateway:

    `$ GATEWAY_HOME/bin/gateway.start`

0. Open the [websocket.org echo test](http://www.websocket.org/echo.html?location=ws://localhost:8000/sample) in a browser.

0. Make sure the **Location** field says `ws://localhost:8000/sample` (or matches whatever you put in your service `<accept>` earlier).

0. Connect and send a message. You will receive a message back which says "Go Warriors!", or whatever you specified in `<what-to-say>`.

