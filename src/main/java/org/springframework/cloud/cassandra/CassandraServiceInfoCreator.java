package org.springframework.cloud.cassandra;

import org.springframework.cloud.cloudfoundry.CloudFoundryServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;

import java.util.List;
import java.util.Map;

public class CassandraServiceInfoCreator extends CloudFoundryServiceInfoCreator<CassandraServiceInfo> {
    public CassandraServiceInfoCreator() {
        super(new Tags("pivotal", "cassandra"));
    }

    @Override
    public CassandraServiceInfo createServiceInfo(Map<String, Object> serviceData) {
        @SuppressWarnings("unchecked")
        Map<String, Object> credentials = (Map<String, Object>) serviceData.get("credentials");

        String id = (String) serviceData.get("name");
        List<String> nodeIps = (List<String>) credentials.get("node_ips");
        int thriftPort = (int) credentials.get("thrift_port");
        int cqlPort = (int) credentials.get("cql_port");
        String keyspaceName = (String) credentials.get("keyspace_name");
        String username = (String) credentials.get("username");
        String password = (String) credentials.get("password");

        return new CassandraServiceInfo(id, nodeIps, thriftPort, cqlPort, keyspaceName, username, password);
    }
}
