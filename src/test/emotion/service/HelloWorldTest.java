package emotion.service;

import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

import static org.junit.Assert.*;

/**
 * Created by lifengshuang on 21/04/2017.
 */
public class HelloWorldTest {

    private static final QName SERVICE_NAME
            = new QName("http://service.emotion/", "HelloWorld");
    private static final QName PORT_NAME
            = new QName("http://service.emotion/", "HelloWorldPort");

    @Test
    public void testHelloWorld() {
        Service service = Service.create(SERVICE_NAME);
        String endpointAddress = "http://localhost:8080/emotion/services/hello_world";
        service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        HelloWorld hw = service.getPort(HelloWorld.class);
        assertEquals(hw.hello("Yeats.Mar"), "Hello, Yeats.Mar!");
    }
}