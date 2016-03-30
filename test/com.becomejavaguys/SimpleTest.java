import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import config.WebSecurityConfig;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author I.Artyomov
 */
public class SimpleTest extends WebSecurityConfig {
    /**
     * negative test
     */
    @Test
    public void negativeWithoutTokenTest() throws Exception {
        mockMvc.perform(get("/api/users/")).andExpect(status().isUnauthorized());
    }

    @Test
    public void possitiveWithTokenTest() throws Exception {
        mockMvc.perform(get("/oauth/token?grant_type=password&client_id=restapp&client_secret=restapp&username=beingjavaguys&password=spring@java"))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.access_token", Matchers.notNullValue()))
                .andExpect(jsonPath("$.token_type",Matchers.is("bearer")))
                .andDo(print())
                .andReturn();
    }

    private String getToken() throws Exception {
        return mockMvc.perform(get("/oauth/token?grant_type=password&client_id=restapp&client_secret=restapp&username=beingjavaguys&password=spring@java"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$.access_token", Matchers.notNullValue()))
                .andExpect(jsonPath("$.token_type", Matchers.is("bearer")))
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    public void accessTokenTest() throws Exception {
        JsonParser parser=new JsonParser();
        JsonObject object= (JsonObject) parser.parse(getToken());
        String token=object.get("access_token").getAsString();
        mockMvc.perform(get("/api/users/").param("access_token",token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON.toString() + ";charset=UTF-8"))
                .andExpect(jsonPath("$", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].id",Matchers.is(1)))
                .andExpect(jsonPath("$[0].name",Matchers.is("user_a")))
                .andExpect(jsonPath("$",Matchers.hasSize(3)))
                .andReturn();
    }

}
