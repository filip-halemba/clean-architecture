package io.halemba.subscription.rest;

import io.halemba.BaseIntegrationTest;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ChangeSubscriptionRestControllerIntegrationTest extends BaseIntegrationTest {

    @Test
    @SneakyThrows
    void getProposal() {
        // given
        stubFor(get(urlEqualTo("/generate-proposal"))
                        .willReturn(aResponse().withBody("PROMOTION"))
        );

        // when
        this.mockMvc.perform(executeGet("/api/1/subscription-change-processes/proposals/STANDARD"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()").value(1))
                    .andExpect(jsonPath("$[0]").value("STANDARD_PROMOTION"));
    }

}