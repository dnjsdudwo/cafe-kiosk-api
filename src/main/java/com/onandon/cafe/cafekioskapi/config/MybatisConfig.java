package com.onandon.cafe.cafekioskapi.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// bean을 사용하기위해 사용되는 클래스 어노테이션
@Configuration
//인터페이스들의 패키지 위치를 선언하는 설정
@MapperScan(basePackages = "com.onandon.cafe.cafekioskapi.**.mapper")
public class MybatisConfig {

    @Bean
    // *.yml에있는 property들을 자바클래스에 값을 가져와서 사용할 수 있게해주는 어노테이션
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource dataSource() { return new HikariDataSource(); }

    @Bean
    public SqlSessionFactory sqlSessionFactory(
            @Qualifier("dataSource") DataSource dataSource,
            ApplicationContext applicationContext
    ) throws Exception{
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage("com.onandon.cafe.cafekioskapi.config.mybatis.typeAlias");
        sessionFactory.setMapperLocations(applicationContext.getResources("classpath*:mybatis/mapper/**/*.xml"));
        SqlSessionFactory sqlSessionFactory = sessionFactory.getObject(); //sql문을 실행하는 객체생성
        sqlSessionFactory.getConfiguration().setCallSettersOnNulls(true); //데이터 전체가 null이어도 컬럼별 null 데이터 생성
        sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL); //insert, update null 처리, 부적합한 열 유형 처리방법
        sqlSessionFactory.getConfiguration().setReturnInstanceForEmptyRow(true); //모든 컬럼이 null일때  null 대신에 모든 값이 null인 인스턴스를 반환
        return sqlSessionFactory;
    }

    @Bean
    // SQL 실행이나 Transaction 관리를 실행
    public SqlSessionTemplate sqlSession (SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
