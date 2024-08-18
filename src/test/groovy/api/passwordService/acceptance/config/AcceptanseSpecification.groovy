package api.passwordService.acceptance.config

import api.passwordService.config.LiquibaseConfig
import org.mockserver.integration.ClientAndServer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification


@SpringBootTest(classes = [LiquibaseConfig])
@AutoConfigureMockMvc
class AcceptanseSpecification extends Specification {

    @Autowired
    MockMvc mockMvc

    @Shared
    protected ClientAndServer mockServer

    def setupSpec() {
        mockServer = ClientAndServer.startClientAndServer(5000)
    }

    def cleanup() {
        mockServer.reset()
    }

    def cleanupSpec() {
        mockServer?.stop()
    }


}
