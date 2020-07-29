package br.com.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserApplicationTests {

    @Autowired
    private MockMvc mocMvc;


	@Test
	void contextLoads() {
	}

  @Test
  void liseUserTest() throws Exception{
   this.mocMvc
       .perform(get("/user"))
       .andExpect(status().isOk());
  }

}
