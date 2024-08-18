package api.passwordService.acceptance.config

import api.passwordService.enums.HeaderEnum
import api.passwordService.repositories.PasswordRepository
import api.passwordService.repositories.SiteRepository
import groovy.json.JsonSlurper
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders


import java.nio.charset.StandardCharsets

class ControllerSpecification extends AcceptanseSpecification {

    @Autowired
    PasswordRepository passwordRepository

    @Autowired
    SiteRepository siteRepository

    def cleanup(){
        passwordRepository.deleteAll()
        siteRepository.deleteAll()
    }
    JsonSlurper jsonSlurper = new JsonSlurper()

    Map get(String path) {
        def request = MockMvcRequestBuilders.get(path)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
        addDefaultHeaders(request)
        def response = mockMvc.perform(request).andReturn().response
        return responseToMap(response)
    }

    Map post(String path, String body) {
        def request = MockMvcRequestBuilders.post(path)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(body)
        addDefaultHeaders(request)
        def response = mockMvc.perform(request).andReturn().response
        return responseToMap(response)
    }

    def responseToMap(MockHttpServletResponse response) {
        def contentAsString = response.getContentAsString(StandardCharsets.UTF_8)
        def responseParsed = StringUtils.isBlank(contentAsString) ? null : jsonSlurper.parseText(contentAsString)
        [body  : responseParsed,
         status: response.status,
         raw   : response.contentAsString]
    }

    private static addDefaultHeaders(request) {
        def headers = new HashMap<>()
        headers.put("X-Origen", HeaderEnum.API_PASSWORD.name())
        headers.forEach({ key, value -> request.header(key, value) })
    }
}
