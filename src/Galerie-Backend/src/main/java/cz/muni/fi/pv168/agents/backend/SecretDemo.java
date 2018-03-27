package cz.muni.fi.pv168.agents.backend;


import org.apache.derby.jdbc.ClientDataSource;

import javax.sql.DataSource;

public class SecretDemo {

    private DataSource ds;
    private SecretManager secretManager;
    private MissionManager missionManager;
    private AgentManager agentManager;

    public static void main(String[] args) {
        ClientDataSource ds = new ClientDataSource();
        ds.setServerName("localhost");
        ds.setPortNumber(1527);
        ds.setDatabaseName("user-test");
        SecretDemo demo = new SecretDemo(ds);
        demo.show();
    }

    private SecretDemo(DataSource ds) {
        this.ds = ds;
        secretManager = new SecretManagerImpl(ds);
        agentManager = new AgentManagerImpl(ds);
        missionManager = new MissionManagerImpl(ds);
    }

    private void show() {

    }





}
