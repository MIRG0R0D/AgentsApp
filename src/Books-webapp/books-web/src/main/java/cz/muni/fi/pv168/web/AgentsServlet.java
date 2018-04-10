package cz.muni.fi.pv168.web;

import cz.muni.fi.pv168.agents.backend.Agent;
import cz.muni.fi.pv168.agents.backend.AgentManager;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Servlet for managing agents.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
@WebServlet(AgentsServlet.URL_MAPPING + "/*")
public class AgentsServlet extends HttpServlet {

    private static final String LIST_JSP = "/agent-list.jsp";
    private static final String EDIT_JSP = "/agent-edit.jsp";
    public static final String URL_MAPPING = "/agents";

    //  private final static Logger //log = LoggerFactory.getLogger(AgentsServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //log.debug("GET ...");
        showMissionsList(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name;
        String born;
        String id;
        String level;

        //support non-ASCII characters in form
        request.setCharacterEncoding("utf-8");
        //action specified by pathInfo
        String action = request.getPathInfo();
        //log.debug("POST ... {}",action);
        switch (action) {
            case "/add":
                //getting POST parameters from form

                Agent agent = getAgentFromRequest(request);

               /* //form data validity check
                if (name == null || name.length() == 0 || author == null || author.length() == 0) {
                    request.setAttribute("chyba", "Je nutné vyplnit všechny hodnoty !");
                    //log.debug("form data invalid");
                    showAgentsList(request, response);
                    return;
                }*/
                //form data processing - storing to database
                try {

                    getAgentManager().create(agent);
                    //redirect-after-POST protects from multiple submission
                    //log.debug("redirecting after POST");
                    response.sendRedirect(request.getContextPath() + URL_MAPPING);
                    return;
                } catch (Exception e) {
                    //log.error("Cannot add agent", e);
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }

            case "/editSave":
                try {
                    Agent agent1 = getAgentFromRequest(request);
                    getAgentManager().update(agent1.getId(), agent1);
                    response.sendRedirect(request.getContextPath() + URL_MAPPING);
                    return;
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }
            case "/delete":
                try {
                    Long longId = Long.valueOf(request.getParameter("id"));
                    getAgentManager().deleteAgentById(longId);
                    //log.debug("redirecting after POST");
                    response.sendRedirect(request.getContextPath() + URL_MAPPING);
                    return;
                } catch (Exception e) {
                    //log.error("Cannot delete agent", e);
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }
            case "/update":
                id = request.getParameter("id");
                request.setAttribute("agent", getAgentManager().findAgentById(Long.parseLong(id)));
                request.getRequestDispatcher(EDIT_JSP).forward(request, response);
                return;
            default:
                //log.error("Unknown action " + action);
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown action " + action);
        }
    }


    private Agent getAgentFromRequest(HttpServletRequest request) {
        String name = request.getParameter("name");
        String born = request.getParameter("born");
        String id = request.getParameter("id");
        String level = request.getParameter("level");
        return new Agent((id != null) ? Long.parseLong(id) : null, LocalDate.parse(born), level, name);
    }

    /**
     * Gets AgentManager from ServletContext, where it was stored by {@link StartListener}.
     *
     * @return AgentManager instance
     */
    private AgentManager getAgentManager() {
        return (AgentManager) getServletContext().getAttribute("agentManager");
    }

    /**
     * Stores the list of agents to request attribute "agents" and forwards to the JSP to display it.
     */
    private void showMissionsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //log.debug("showing table of agents");
            request.setAttribute("agents", getAgentManager().findAllAgents());
            request.getRequestDispatcher(LIST_JSP).forward(request, response);
        } catch (Exception e) {
            //log.error("Cannot show agents", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
