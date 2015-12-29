package org.springframework.cloud.cassandra;

import com.datastax.driver.auth.DseAuthProvider;
import com.datastax.driver.core.Cluster;
import org.springframework.cloud.service.AbstractServiceConnectorCreator;
import org.springframework.cloud.service.ServiceConnectorConfig;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.util.StringUtils;

public class CassandraServiceConnectorCreator extends AbstractServiceConnectorCreator<CassandraSessionFactoryBean, CassandraServiceInfo> {
    @Override
    public CassandraSessionFactoryBean create(CassandraServiceInfo serviceInfo, ServiceConnectorConfig serviceConnectorConfig) {
        CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
        session.setCluster(cluster(serviceInfo));
        session.setKeyspaceName(serviceInfo.getKeyspaceName());
        session.setConverter(converter());
        session.setSchemaAction(SchemaAction.NONE);
        return session;
    }

    private Cluster cluster(CassandraServiceInfo serviceInfo) {
        String[] contactPoints = StringUtils.toStringArray(serviceInfo.getNodeIps());
        return Cluster.builder().addContactPoints(contactPoints)
                .withPort(serviceInfo.getCqlPort())
                .withAuthProvider(new DseAuthProvider())
                .withCredentials(serviceInfo.getUsername(), serviceInfo.getPassword()).build();
    }

    private CassandraMappingContext mappingContext() {
        return new BasicCassandraMappingContext();
    }

    private CassandraConverter converter() {
        return new MappingCassandraConverter(mappingContext());
    }
}
