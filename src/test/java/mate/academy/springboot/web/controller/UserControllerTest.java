package mate.academy.springboot.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.stream.Stream;
import com.fasterxml.jackson.databind.ObjectMapper;
import mate.academy.springboot.web.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * UserControllerTest is a test class designed to perform unit tests on the UserController class.
 * It uses the Spring Boot Test framework to create a mock MVC environment for testing the REST API.
 */
@WebMvcTest(UserController.class)
class UserControllerTest {

    // Injecting the MockMvc object, which simulates the behavior of the MVC framework for testing purposes.
    @Autowired
    private MockMvc mockMvc;

    // Injecting the ObjectMapper object, which is used for converting objects to JSON format and vice versa.
    @Autowired
    private ObjectMapper objectMapper;

    // Parameters for the parameterized test.
    // Each entry in the list represents a set of parameters for a single test run.
    static Stream<Arguments> userProvider() {
        return Stream.of(
                Arguments.of(1L, "james@i.ua"),
                Arguments.of(2L, "kane@i.ua"),
                Arguments.of(3L, "charlie@i.ua"),
                Arguments.of(4L, "dave@i.ua"),
                Arguments.of(5L, "bill@gmail.com")
        );
    }

    /**
     * This test case verifies that the getAll() method in UserController returns the expected list of users.
     *
     * @throws Exception if any error occurs during the test.
     */
    @Test
    void shouldGetAllUsers() throws Exception {
        // Perform a GET request to the /users endpoint.
        mockMvc.perform(get("/users"))
                // Expect the HTTP status to be 200 OK.
                .andExpect(status().isOk())
                // Expect the JSON response to match the specified array of user objects.
                .andExpect(content().json("[{'id': 1, 'email': 'bob@i.ua'}, {'id': 2, 'email': 'alice@i.ua'}]"));
    }

    /**
     * This parameterized test verifies that the create() method in UserController successfully creates a user.
     *
     * @param id    The ID of the user.
     * @param email The email address of the user.
     * @throws Exception if any error occurs during the test.
     */
    @ParameterizedTest
    @MethodSource("userProvider")
    void shouldCreateUser(Long id, String email) throws Exception {
        // Create a new user object.
        User user = new User(id, email);
        // Convert the user object to a JSON string.
        String userJson = objectMapper.writeValueAsString(user);

        // Perform a POST request to the /users endpoint with the user data.
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                // Expect the HTTP status to be 200 OK.
                .andExpect(status().isOk())
                // Expect the response content to match the specified string.
                .andExpect(content().string(String.format("User created. Id: %s, email: %s", id, email)));
    }
}
