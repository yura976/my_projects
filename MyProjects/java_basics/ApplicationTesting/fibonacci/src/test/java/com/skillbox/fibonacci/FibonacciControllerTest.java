package com.skillbox.fibonacci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class FibonacciControllerTest extends PostgresTestContainerInitializer {

    @Autowired
    private MockMvc mockMvc;
    private final FibonacciService service = Mockito.mock(FibonacciService.class);

    private final FibonacciController controller = new FibonacciController(service);

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    @Test
    @DisplayName("Test get number")
    public void testGetNumber() throws Exception {
        int index = 10;
        FibonacciNumber number = new FibonacciNumber(10, 55);
        when(service.fibonacciNumber(index)).thenReturn(number);
        mockMvc.perform(get("/fibonacci/{index}", index))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.index").value(10))
                .andExpect(jsonPath("$.value").value(55));
        verify(service, times(1)).fibonacciNumber(index);
    }

    @Test
    @DisplayName("Test get exception")
    public void testGetException() throws Exception {
        int index = 0;
        when(service.fibonacciNumber(index)).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(get("/fibonacci/{index}", index))
                .andExpect(status().isBadRequest()).andReturn();
        verify(service, times(1)).fibonacciNumber(index);
    }
}
