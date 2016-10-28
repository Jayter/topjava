package ru.javawebinar.topjava.web.resources;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Jayton on 26.10.2016.
 */
public class ResourceControllerTest extends AbstractControllerTest {
    @Test
    public void testStyle() throws Exception {
        mockMvc.perform(get("/resources/css/style.css"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/css")));
    }
}
