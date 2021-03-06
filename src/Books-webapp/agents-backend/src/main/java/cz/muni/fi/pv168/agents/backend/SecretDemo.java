package cz.muni.fi.pv168.agents.backend;


import org.apache.derby.jdbc.ClientDataSource;

import javax.sql.DataSource;
import java.time.LocalDate;

public class SecretDemo {

    private static ClientDataSource ds;
    private static SecretManager secretManager;
    private static MissionManager missionManager;
    private static AgentManager agentManager;

    public static void main(String[] args) {

        SecretDemo demo = new SecretDemo(getDataSource());
        demo.show();
        agent_demo();
        //test1();
        //mis_demo();
    
        
    }

    public static ClientDataSource getDataSource(){
        if(ds == null) {
            ds = new ClientDataSource();
            ds.setServerName("localhost");
            ds.setPortNumber(1527);
            ds.setDatabaseName("user-test");
        }
        return ds;
    }
    
    private static void test1(){
        Agent agent = new Agent(null, LocalDate.now(), "00", "James Bond");
        System.out.println(agent.toString());
        agentManager.create(agent);
        System.out.println(agent.toString());
    }
    
    private static void mis_demo(){
        System.out.println("Mission demo \n");
        System.out.println(missionManager.createMission(new Mission(null,"codename",LocalDate.now(),null,"location","description")));
        
    }
    
    private static void agent_demo(){
        System.out.println("Agent demo \n");
        System.out.println("adding agents");
        Long jamesID=null, tomID = null, chuckID=null;
        try{
            jamesID=agentManager.create(new Agent(Long.MIN_VALUE, LocalDate.now(), "00", "James Bond"));
            tomID=agentManager.create(new Agent(Long.MIN_VALUE, LocalDate.now(), "15", "Tom Ford"));
            chuckID=agentManager.create(new Agent(Long.MIN_VALUE, LocalDate.now(), "15", "Chuck Norris"));
        }catch (Exception e){ e.printStackTrace(); }
        
        System.out.println("Find all agents");
        try{
            for (Agent ag : agentManager.findAllAgents())
                System.out.println(ag.toString());
        }catch (Exception e){ e.printStackTrace(); }
        
        System.out.println("Finding  agents by id");
        try{
            System.out.println(agentManager.findAgentById(tomID).toString());
        }catch (Exception e){ e.printStackTrace(); }
        
        System.out.println("Updating");
        try{
            System.out.println(agentManager.findAgentById(jamesID).toString());
            agentManager.update(jamesID, new Agent(Long.MIN_VALUE, LocalDate.now(), "00", "Bond, James Bond"));
            System.out.println(agentManager.findAgentById(jamesID).toString());
        }catch (Exception e){ e.printStackTrace(); }
        
        System.out.println("Delete agents");
        try{
            
            agentManager.deleteAgentById(jamesID);
            System.out.println("delete James - OK");
        }catch (Exception e){ e.printStackTrace(); }
        
        System.out.println("Find all agents");
        try{
            for (Agent ag : agentManager.findAllAgents())
                System.out.println(ag.toString());
        }catch (Exception e){ e.printStackTrace(); }
        
        System.out.println("Delete all agents");
        try{
            
            //agentManager.deleteAllAgents();
        }catch (Exception e){ e.printStackTrace(); }
        
        System.out.println("Find all agents");
        try{
            for (Agent ag : agentManager.findAllAgents())
                System.out.println(ag.toString());
        }catch (Exception e){ e.printStackTrace(); }
    }

    private SecretDemo(ClientDataSource ds) {
        this.ds = ds;
        secretManager = new SecretManagerImpl(ds);
        agentManager = new AgentManagerImpl(ds);
        missionManager = new MissionManagerImpl(ds);
    }

    private void show() {

    }





}
