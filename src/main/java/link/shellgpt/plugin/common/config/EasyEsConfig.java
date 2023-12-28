package link.shellgpt.plugin.common.config;

import cn.easyes.common.utils.ExceptionUtils;
import cn.easyes.common.utils.RestHighLevelClientBuilder;
import cn.easyes.common.utils.StringUtils;
import cn.easyes.starter.config.EasyEsConfigProperties;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static cn.easyes.common.constants.BaseEsConstants.COLON;
import static cn.easyes.common.constants.BaseEsConstants.DEFAULT_SCHEMA;

@Configuration
public class EasyEsConfig {

    private static final Log log = LogFactory.get();


    /*@Bean
    @Primary
    public RestHighLevelClient restHighLevelClient() {

        EasyEsConfigProperties easyEsConfigProperties = new EasyEsConfigProperties();
        // 处理地址
        easyEsConfigProperties.setAddress(address);
        easyEsConfigProperties.setUsername("elastic");
        easyEsConfigProperties.setPassword("YourElasticPassword");
        if (StringUtils.isEmpty(address)) {
            throw ExceptionUtils.eee("please config the es address");
        }
        if (!address.contains(COLON)) {
            throw ExceptionUtils.eee("the address must contains port and separate by ':'");
        }
        String schema = StringUtils.isEmpty(easyEsConfigProperties.getSchema())
                ? DEFAULT_SCHEMA : easyEsConfigProperties.getSchema();
        List<HttpHost> hostList = new ArrayList<>();
        Arrays.stream(easyEsConfigProperties.getAddress().split(","))
                .forEach(item -> hostList.add(new HttpHost(item.split(":")[0],
                        Integer.parseInt(item.split(":")[1]), schema)));

        // 转换成 HttpHost 数组
        HttpHost[] httpHost = hostList.toArray(new HttpHost[]{});

        // 构建连接对象
        RestClientBuilder builder = RestClient.builder(httpHost);
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            // 设置心跳时间,最大连接数,最大连接路由
            Optional.ofNullable(easyEsConfigProperties.getKeepAliveMillis()).ifPresent(p -> httpClientBuilder.setKeepAliveStrategy((response, context) -> p));
            Optional.ofNullable(easyEsConfigProperties.getMaxConnTotal()).ifPresent(httpClientBuilder::setMaxConnTotal);
            Optional.ofNullable(easyEsConfigProperties.getMaxConnPerRoute()).ifPresent(httpClientBuilder::setMaxConnPerRoute);

            // 设置账号密码
            String username = easyEsConfigProperties.getUsername();
            String password = easyEsConfigProperties.getPassword();
            if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
                final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                credentialsProvider.setCredentials(AuthScope.ANY,
                        new UsernamePasswordCredentials(username, password));
                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }
            return httpClientBuilder;
        });

        // 设置超时时间之类的
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            Optional.ofNullable(easyEsConfigProperties.getConnectTimeout()).ifPresent(requestConfigBuilder::setConnectTimeout);
            Optional.ofNullable(easyEsConfigProperties.getSocketTimeout()).ifPresent(requestConfigBuilder::setSocketTimeout);
            Optional.ofNullable(easyEsConfigProperties.getConnectionRequestTimeout())
                    .ifPresent(requestConfigBuilder::setConnectionRequestTimeout);
            return requestConfigBuilder;
        });

        return RestHighLevelClientBuilder.build(builder);
    }*/

}