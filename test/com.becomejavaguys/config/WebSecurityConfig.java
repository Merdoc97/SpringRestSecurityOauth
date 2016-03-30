package config;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.web.FilterChainProxy;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author I.Artyomov
 */
public class WebSecurityConfig extends WebAppConfig {
    @Qualifier("filtername")
    @Resource
    private FilterChainProxy springSecurityFilterChain;

    @Autowired
    protected MockHttpSession session;

    @Before
    public void before() {
        this.mockMvc = webAppContextSetup(this.wac)
                .addFilters(springSecurityFilterChain).build();
    }
}
