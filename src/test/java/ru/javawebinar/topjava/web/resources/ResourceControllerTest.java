package ru.javawebinar.topjava.web.resources;

import org.junit.Test;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by Jayton on 26.10.2016.
 */
public class ResourceControllerTest extends AbstractControllerTest {
    @Test
    public void testStyle() throws Exception {
        mockMvc.perform(get("style.css"))
                        .andDo(print());
    }
}
