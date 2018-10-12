package guru.springfamework.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractRestControllerTest {

    public static String asJsonString(Object object) {
        try{
            return new ObjectMapper().writeValueAsString(object);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
