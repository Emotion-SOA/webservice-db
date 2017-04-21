package emotion.impl;

import emotion.service.HelloWorld;

import javax.jws.WebService;

/**
 * Created by lifengshuang on 21/04/2017.
 */

@WebService(endpointInterface = "emotion.service.HelloWorld", serviceName = "HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    @Override
    public String hello(String name) {
        return "Hello, " + name + "!";
    }

}
