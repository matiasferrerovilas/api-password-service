package api.passwordService.config

import jakarta.annotation.PostConstruct
import liquibase.Contexts
import liquibase.Liquibase
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.FileSystemResourceAccessor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration

import javax.sql.DataSource
import java.sql.Connection

@TestConfiguration
class LiquibaseConfig {

    @Autowired
    DataSource dataSource

    @PostConstruct
    def initializeDatabase() {
        Connection connection = dataSource.getConnection();
        Liquibase liquibase = new Liquibase(
                "build.migrations/db/changelog.xml",
                new FileSystemResourceAccessor(new File(".")),
                new JdbcConnection(connection)
        );
        liquibase.dropAll()
        liquibase.update(new Contexts())
    }
}
