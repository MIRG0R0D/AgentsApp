package cz.muni.fi.pv168.web;

import cz.muni.fi.pv168.agents.backend.AgentManagerImpl;

import cz.muni.fi.pv168.agents.backend.MissionManagerImpl;
import cz.muni.fi.pv168.agents.backend.SecretDemo;
import cz.muni.fi.pv168.agents.backend.SecretManagerImpl;
import org.apache.derby.jdbc.ClientDataSource;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class StartListener implements ServletContextListener {

    //private final static Logger log = LoggerFactory.getLogger(StartListener.class);

    @Override
    public void contextInitialized(ServletContextEvent ev) {
        //log.info("webová aplikace inicializována");
        ServletContext servletContext = ev.getServletContext();
        ClientDataSource dataSource = SecretDemo.getDataSource();
        servletContext.setAttribute("agentManager", new AgentManagerImpl(dataSource));
        servletContext.setAttribute("missionManager", new MissionManagerImpl(dataSource));
        servletContext.setAttribute("secretManager", new SecretManagerImpl(dataSource));
        //log.info("vytvořeny manažery a uloženy do atributů servletContextu");
    }

    @Override
    public void contextDestroyed(ServletContextEvent ev) {
        //log.info("aplikace končí");
    }

}
