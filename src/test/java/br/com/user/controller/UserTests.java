package br.com.user.controller;
  
import org.aspectj.lang.annotation.Before;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.JsonPath;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import br.com.user.model.User;
import br.com.user.respository.UserRepository;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTests {

  @Autowired
  private MockMvc mocMvc;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  UserRepository userRepository;

  private User user;
 
  UserControllerTests(){
      this.user = new User();
      user.setName("User name");
      user.setEmail("email@empresa.com");
      user.setPassword("123123123");
  }


  @Test
  void expectedOkInUserEndPoint() throws Exception{
   this.mocMvc
       .perform(get("/user"))
       .andExpect(status().isOk());
  }

  @Test
  void expectedExceptionOnCreated() throws Exception{
   this.mocMvc
       .perform(post("/user")
       .contentType("application/json"))
       .andExpect(status().isBadRequest());       
  }


  @Test
  void expectedUserTobeCreated() throws Exception{
   this.mocMvc
       .perform(post("/user")
       .contentType("application/json")
       .content(this.objectMapper.writeValueAsString(this.user)))
       .andExpect(status().isCreated())
       .andExpect(jsonPath("name", Is.is(this.user.getName()) ));
  }

  @Test
  void expectedGetOneUserInUserEndPoint() throws Exception{
   this.mocMvc
       .perform(get("/user")
       .contentType("application/json"))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$", hasSize(1)));
  }

  @Test
  void expectedUserFromEndPointUsingUserId() throws Exception{
      String id = this.userRepository.findAll().get(0).getId();

      this.mocMvc
          .perform(get("/user/"+id)
          .contentType("application/json"))
          .andExpect(status().isOk());
  }

  @Test
  void expectedChangeNameFromUser() throws Exception{
      User user = this.userRepository.findAll().get(0);
      user.setName("new name");
      
      this.mocMvc
        .perform(put("/user/"+user.getId())
        .contentType("application/json")
        .content(this.objectMapper.writeValueAsString(user)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("name", Is.is(user.getName()) ));
  }

  @Test
  void expectedDeleteUser() throws Exception{
      this.user.setEmail("nmewemail@empresa.com");
      this.mocMvc
        .perform(post("/user")
        .contentType("application/json")
        .content(this.objectMapper.writeValueAsString(this.user)))
        .andExpect(status().isCreated());
      

      String id = this.userRepository.findAll().get(0).getId();
      
      this.mocMvc
        .perform(delete("/user/"+id)
        .contentType("application/json"))
        .andExpect(status().isOk());
  }
}
