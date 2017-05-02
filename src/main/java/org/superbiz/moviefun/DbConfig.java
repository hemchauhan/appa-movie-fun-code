package org.superbiz.moviefun;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by pivotal on 5/2/17.
 */
@Configuration
public class DbConfig {

    @Configuration
    @ConfigurationProperties("moviefun.datasources.movies")
    public class MovieConfig extends HikariConfig {

        @Bean
        public DataSource movieDatasource() {
            return new HikariDataSource(this);
        }
    }

    @Configuration
    @ConfigurationProperties("moviefun.datasources.albums")
    public class AlbumConfig extends HikariConfig {

        @Bean
        public DataSource albumDatasource() {
            return new HikariDataSource(this);
        }
    }

}
