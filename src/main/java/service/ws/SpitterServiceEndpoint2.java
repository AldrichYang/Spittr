package service.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.Spitter3Service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by yh on 2019-02-28.
 */
@Component
@WebService(serviceName = "Spitter3Service")
public class SpitterServiceEndpoint2 {

    @Autowired
    private Spitter3Service spitter3Service;

    @WebMethod
    public String spitterInfo() {
        return spitter3Service.hello3Spitter();
    }

}
