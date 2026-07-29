/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gk.adv.linnk.vn.share.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.Session;

/**
 *
 * @author TUANPLA
 */
public class SessionSv extends HttpServlet {

//Context 1 : Sending Servlet Add below
//So this is AuthSuccess - Within The master context doing authentication
//Given examples of vectors sessions and request from
//All the information now allows you to put those new
// provider and providerName session values back into AuthSuccess
//that is executed on other Context -
//In theory once it authenticates you can just store the output i.e.
//email/display/logged in and put it into other context - or...
//as it is process list etc on other context
//Vector example
    ArrayList roles = new ArrayList();
//roles.addElement("COOOGOOO");
    //Redirect url
    String redir = "http://mydomain.com/solutions2/AuthSuccess";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            HttpSession session = request.getSession(true);
            session.setAttribute("provider2", "provider_session_info");
            session.setAttribute("providerName2", "providerName");
            //Start new shared servlet context
            ServletContext myContext = getServletContext();

            //Get session id
            String sessionid = session.getId();

            // objects,string,sessions,whatever that matches other end
            myContext.setAttribute("MYSHAREDSESSION", sessionid);
            myContext.setAttribute("GOOFY", roles);

            //Send session directly
            myContext.setAttribute("SharedSession", session);

            //send HttpRequest
            myContext.setAttribute("SharedRequest", request);

            //Redirect to new context/domain/subdomain
            //            Redirect(out, red, response);
            //Shared sessioname is obvious and it sends the session id followed by:
            // objects,string,sessions,whatever that matches other end
            myContext.setAttribute("MYSHAREDSESSION", sessionid);
            myContext.setAttribute("GOOFY", roles);

            //Send session directly
            myContext.setAttribute("SharedSession", session);

            //send HttpRequest
            myContext.setAttribute("SharedRequest", request);

            //Redirect to new context/domain/subdomain
            //            Redirect(out, red, response);
            //-------------------------------------------------------------
            // Now within ther servlets of solution2 within 
            // AuthSuccess call back the session info
            // and process as per normal
            //Add this to new context path 
            //So it looks in the first context now
            ServletContext firstOne = getServletContext().getContext("/servlets");

            //returns previous session id
            String jsessionid = (String) firstOne.getAttribute("MYSHAREDSESSION");

            //Returns Session as was
            Session ProviderName = (Session) firstOne.getAttribute("SharedSession");
            //Returns session strings we need
            //            String g1 = (String) ProviderName.getValue("provider2");
            //            String g2 = (String) ProviderName.getValue("providerName2");
            //            pout += "--- " + g1 + "" + g2;

            //Grab previous request to do req processing if required
            HttpServletRequest nrequest = (HttpServletRequest) firstOne.getAttribute("SharedRequest");

            //retrieve vector
            ArrayList goo = (ArrayList) firstOne.getAttribute("MYVECTOR");
            if (goo.size() > 0) {
                for (Object goo1 : goo) {
                }
            }
            //Shared sessioname is obvious and it sends the session id followed by:

        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
