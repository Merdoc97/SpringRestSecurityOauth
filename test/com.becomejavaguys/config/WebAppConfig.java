package config;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author I.Artyomov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
        "classpath:spring-security.xml","classpath:mvc-dispatcher-servlet.xml"
})
public class WebAppConfig {
    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;
    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

}
