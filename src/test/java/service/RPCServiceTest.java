package service;

import config.RootConfig;
import config.WebConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by yh on 2019-01-30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class})
public class RPCServiceTest {
    @Autowired
    SpitterService spitterService;

    @Test
    public void hessianRPC() {
        System.out.println(spitterService.helloSpitter());
    }


}
