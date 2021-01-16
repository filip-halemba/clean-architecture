package io.halemba;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureWireMock(port = 0)
@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
public abstract class BaseIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;

    @SneakyThrows
    protected MockHttpServletRequestBuilder executeGet(String url){
        return get(url);
    }

}
