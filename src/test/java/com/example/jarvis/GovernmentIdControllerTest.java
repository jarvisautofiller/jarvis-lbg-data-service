// package com.example.jarvis;

// import com.example.jarvis.controller.GovernmentIdController;
// import com.example.jarvis.model.IdRequest;
// import com.example.jarvis.service.UserService;
// import com.example.jarvis.entity.AadharData;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(GovernmentIdController.class)
// public class GovernmentIdControllerTest{

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private UserService userService;

//     private static final ObjectMapper objectMapper = new ObjectMapper();

//     @Test
//     void testGetUserByIdAndType_Success() throws Exception {
//         // Mock AadharData object
//         AadharData mockAadhar = new AadharData();
//         mockAadhar.setIdNumber("123456789012");
//         mockAadhar.setFirstName("John");
//         mockAadhar.setLastName("Doe");
//         mockAadhar.setAddress("123 Main St, City");
//         mockAadhar.setDob("1990-01-01");
//         mockAadhar.setGender("Male");
//         mockAadhar.setPhoneNumber("9876543210");

//         // Mock request
//         IdRequest request = new IdRequest();
//         request.setIdNumber("123456789012");
//         request.setIdType("aadhar");

//         when(userService.getUserDataByIdAndType("123456789012", "aadhar"))
//                 .thenReturn(mockAadhar);

//         mockMvc.perform(post("/userDetails")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(request)))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.idNumber").value("123456789012"))
//                 .andExpect(jsonPath("$.firstName").value("John"))
//                 .andExpect(jsonPath("$.phoneNumber").value("9876543210"));
//     }

//     @Test
//     void testGetUserByIdAndType_NotFound() throws Exception {
//         IdRequest request = new IdRequest();
//         request.setIdNumber("999999999999");
//         request.setIdType("aadhar");

//         when(userService.getUserDataByIdAndType("999999999999", "aadhar"))
//                 .thenReturn(null);

//         mockMvc.perform(post("/userDetails")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(request)))
//                 .andExpect(status().isNotFound())
//                 .andExpect(content().string("No user with that ID:999999999999"));
//     }

//     @Test
//     void testGetUserByIdAndType_BadRequest() throws Exception {
//         IdRequest request = new IdRequest();
//         request.setIdNumber("invalid");
//         request.setIdType("unsupported_type");

//         when(userService.getUserDataByIdAndType("invalid", "unsupported_type"))
//                 .thenThrow(new IllegalArgumentException("Unsupported ID type"));

//         mockMvc.perform(post("/userDetails")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(request)))
//                 .andExpect(status().isBadRequest())
//                 .andExpect(content().string("Unsupported ID type"));
//     }
// }

