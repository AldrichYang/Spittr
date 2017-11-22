package contorller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by yh on 17/11/22.
 */
public class HomeControllerTest {
    @Test
    public void testHomePage() {
        HomeController homeController = new HomeController();
        assertEquals("home", homeController.home());
    }

    //Spring现在包含了一种mock Spring MVC并针对控制器执行HTTP请求的机制。
    // 这样的话,在测试控制器的时候,就没有必要再启动Web服务器和Web浏览器了
    @Test
    public void testHomePageWeb() throws Exception {
        HomeController homeController = new HomeController();
        // 搭建MockMvc
        MockMvc mockMvc = standaloneSetup(homeController).build();
        // 对"/"执行get请求，预期得到home视图
        mockMvc.perform(get("/")).andExpect(view().name("home"));
        mockMvc.perform(get("/homepage")).andExpect(view().name("home"));
    }
}
