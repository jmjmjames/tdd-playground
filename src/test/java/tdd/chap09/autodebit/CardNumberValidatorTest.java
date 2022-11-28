package tdd.chap09.autodebit;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

public class CardNumberValidatorTest {
    private WireMockServer wireMockServer;

    @BeforeEach
    void setUp() {
        wireMockServer = new WireMockServer(options().port(8081));
        wireMockServer.start();
    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void valid() {
        wireMockServer.stubFor(post(urlEqualTo("/card"))
                .withRequestBody(equalTo("12345678"))
                .willReturn(aResponse()
                        .withHeader(HttpHeaders.CONTENT_TYPE, "text/plain")
                        .withBody("ok"))
        );

        CardNumberValidator validator = new CardNumberValidator("http://127.0.0.1:8081");
        CardValidity validity = validator.validate("12345678");
        assertThat(validity).isEqualTo(CardValidity.VALID);
    }

    @Test
    void timeout() {
        wireMockServer.stubFor(post(urlEqualTo("/card"))
                .willReturn(aResponse()
                        .withFixedDelay(5000))
        );

        CardNumberValidator validator = new CardNumberValidator("http://127.0.0.1:8081");
        CardValidity validity = validator.validate("12345678");
        assertThat(validity).isEqualTo(CardValidity.TIMEOUT);
    }
}
