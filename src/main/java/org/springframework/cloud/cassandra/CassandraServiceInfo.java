package org.springframework.cloud.cassandra;

import org.springframework.cloud.service.BaseServiceInfo;

import java.util.List;

public class CassandraServiceInfo extends BaseServiceInfo {

    private List<String> nodeIps;
    private int thriftPort;
    private int cqlPort;
    private String keyspaceName;
    private String username;
    private String password;

    public CassandraServiceInfo(String id, List<String> nodeIps, int thriftPort, int cqlPort, String keyspaceName, String username, String password) {
        super(id);
        this.nodeIps = nodeIps;
        this.thriftPort = thriftPort;
        this.cqlPort = cqlPort;
        this.keyspaceName = keyspaceName;
        this.username = username;
        this.password = password;
    }

    @ServiceProperty
    public List<String> getNodeIps() {
        return nodeIps;
    }

    @ServiceProperty
    public int getThriftPort() {
        return thriftPort;
    }

    @ServiceProperty
    public int getCqlPort() {
        return cqlPort;
    }

    @ServiceProperty
    public String getKeyspaceName() {
        return keyspaceName;
    }

    @ServiceProperty
    public String getUsername() {
        return username;
    }

    @ServiceProperty
    public String getPassword() {
        return password;
    }
}
