package cz.muni.fi.pv168.web;

import cz.muni.fi.pv168.agents.backend.Mission;
import cz.muni.fi.pv168.agents.backend.MissionManager;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Servlet for managing missions.
 *
 * @author Dovganiuk
 */
@WebServlet(MissionServlet.URL_MAPPING + "/*")
public class MissionServlet extends HttpServlet {

    private static final String LIST_JSP = "/mission-list.jsp";
    private static final String EDIT_JSP = "/mission-edit.jsp";
    public static final String URL_MAPPING = "/missions";

    //  private final static Logger //log = LoggerFactory.getLogger(AgentsServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //log.debug("GET ...");
        showMissionList(request, response);
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

                Mission mission = getMissionFromRequest(request);

               /* //form data validity check
                if (name == null || name.length() == 0 || author == null || author.length() == 0) {
                    request.setAttribute("chyba", "Je nutné vyplnit všechny hodnoty !");
                    //log.debug("form data invalid");
                    showAgentsList(request, response);
                    return;
                }*/
                //form data processing - storing to database
                try {

                    getMissionManager().createMission(mission);
                    //redirect-after-POST protects from multiple submission
                    //log.debug("redirecting after POST");
                    response.sendRedirect(request.getContextPath() + URL_MAPPING);
                    return;
                } catch (Exception e) {
                    //log.error("Cannot add mission", e);
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }

            case "/editSave":
                try {
                    Mission mission1 = getMissionFromRequest(request);
                    getMissionManager().updateMission(mission1.getId(), mission1);
                    response.sendRedirect(request.getContextPath() + URL_MAPPING);
                    return;
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }
            case "/delete":
                try {
                    Long longId = Long.valueOf(request.getParameter("id"));
                    getMissionManager().deleteMission(longId);
                    //log.debug("redirecting after POST");
                    response.sendRedirect(request.getContextPath() + URL_MAPPING);
                    return;
                } catch (Exception e) {
                    //log.error("Cannot delete mission", e);
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                    return;
                }
            case "/update":
                id = request.getParameter("id");
                request.setAttribute("mission", getMissionManager().getMission(Long.parseLong(id)));
                request.getRequestDispatcher(EDIT_JSP).forward(request, response);
                return;
            default:
                //log.error("Unknown action " + action);
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown action " + action);
        }
    }


    private Mission getMissionFromRequest(HttpServletRequest request) {
        String name = request.getParameter("codeName");
        String location = request.getParameter("location");
        String id = request.getParameter("id");
        String description = request.getParameter("description");
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        return new Mission((id != null) ? Long.parseLong(id) : null, name,LocalDate.parse(start),LocalDate.parse(end),
                location, description);
    }

    /**
     * Gets AgentManager from ServletContext, where it was stored by {@link StartListener}.
     *
     * @return AgentManager instance
     */
    private MissionManager getMissionManager() {
        return (MissionManager) getServletContext().getAttribute("missionManager");
    }

    /**
     * Stores the list of missions to request attribute "missions" and forwards to the JSP to display it.
     */
    private void showMissionList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //log.debug("showing table of missions");
            request.setAttribute("missions", getMissionManager().getMissions());
            request.getRequestDispatcher(LIST_JSP).forward(request, response);
        } catch (Exception e) {
            //log.error("Cannot show missions", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
