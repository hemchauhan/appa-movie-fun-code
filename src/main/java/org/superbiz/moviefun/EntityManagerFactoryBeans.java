package org.superbiz.moviefun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by pivotal on 5/2/17.
 */
@Configuration
public class EntityManagerFactoryBeans {
    @Autowired
    private DbConfig dataSource;

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");

        return hibernateJpaVendorAdapter;
    }

    @Bean ("entityManagerFactoryAlbum")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryAlbum(@Qualifier("albumDatasource") DataSource datasource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(datasource);
        em.setPackagesToScan("org.superbiz.moviefun");
        em.setPersistenceUnitName("myPersistenceAlbumUnit");

        em.setJpaVendorAdapter(jpaVendorAdapter());

        return em;
    }

    @Bean ("entityManagerFactoryMovie")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryMovie(@Qualifier("movieDatasource") DataSource datasource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(datasource);
        em.setPackagesToScan("org.superbiz.moviefun");
        em.setPersistenceUnitName("myPersistenceMovieUnit");

        em.setJpaVendorAdapter(jpaVendorAdapter());

        return em;
    }

    @Bean ("platformTransactionManagerMovie")
    public PlatformTransactionManager platformTransactionManagerMovie(@Qualifier("entityManagerFactoryMovie") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {

        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
    }

    @Bean ("platformTransactionManagerAlbum")
    public PlatformTransactionManager platformTransactionManagerAlbum(@Qualifier("entityManagerFactoryAlbum") LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {

        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
    }
}
