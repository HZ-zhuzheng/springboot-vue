package com.frame.data.source.config;

import java.sql.SQLException;

import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 配置数据源
 * @Primary 标识为主数据源；主数据源只能有一个
 * @author 
 *
 */
@Configuration  
@MapperScan(basePackages = "com.frame.*.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DatasourceConfigure {
	@Primary
	@Bean
	public DataSource datasource(DataBaseConfigure config) throws SQLException {
		DruidDataSource dataSource=new DruidDataSource();
		dataSource.setDriverClassName(config.getDriverClassName());
		dataSource.setUrl(config.getUrl());
		dataSource.setUsername(config.getUsername());
        dataSource.setPassword(config.getPassword());
        //配置最大连接
        dataSource.setMaxActive(config.getMaxActive());
        //配置初始连接
        dataSource.setInitialSize(config.getInitialSize());
        //配置最小连接
        dataSource.setMinIdle(config.getMinIdle());
        //连接等待超时时间
        dataSource.setMaxWait(config.getMaxWait());
        //间隔多久进行检测,关闭空闲连接
        dataSource.setTimeBetweenEvictionRunsMillis(config.getTimeBetweenEvictionRunsMillis());
        //一个连接最小生存时间
        dataSource.setMinEvictableIdleTimeMillis(config.getMinEvictableIdleTimeMillis());
        //连接等待超时时间 单位为毫秒 缺省启用公平锁，
        //并发效率会有所下降， 如果需要可以通过配置useUnfairLock属性为true使用非公平锁
        dataSource.setUseUnfairLock(config.isUseUnfairLock());
        //用来检测是否有效的sql
        dataSource.setValidationQuery(config.getValidationQuery());
        dataSource.setTestWhileIdle(true);
        //申请连接时执行validationQuery检测连接是否有效，配置为true会降低性能
        dataSource.setTestOnBorrow(config.isTestOnBorrow());
        //归还连接时执行validationQuery检测连接是否有效，配置为true会降低性能
        dataSource.setTestOnReturn(config.isTestOnReturn());
        //打开PSCache,并指定每个连接的PSCache大小启用poolPreparedStatements后，
        //PreparedStatements 和CallableStatements 都会被缓存起来复用，
        //即相同逻辑的SQL可以复用一个游标，这样可以减少创建游标的数量。
        dataSource.setPoolPreparedStatements(config.isPoolPreparedStatements());
        dataSource.setMaxOpenPreparedStatements(config.getMaxOpenPreparedStatements());
        //配置sql监控的filter
        dataSource.setFilters(config.getFilters());
        try {
            dataSource.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
	}

	
	/**
	 * 配置 mybatis对xml和实体类 扫描的路径
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	@Primary
	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("datasource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		//对应我们的实体类所在的包,多个package之间可以用逗号或者分号等来进行分隔。包全名
		bean.setTypeAliasesPackage("com.along.entity");
		//Mapper.xml 配置文件路径
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().
				getResources("classpath:mybatis/mapper/*Mapper.xml"));
		return bean.getObject();
	}

	/**
	 * 配置数据库事务管理器
	 * @param sqlSessionFactory
	 * @return
	 * @throws Exception
	 */
	@Primary
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(
			@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	
	/**
	 * 配置跨域拦截
	 * @return
	 */
	private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1 设置访问源地址
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法
        return corsConfiguration;
    }

    /**
     * 将跨域配置加入到bean
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4 对接口配置跨域设置
        return new CorsFilter(source);
    }

}
