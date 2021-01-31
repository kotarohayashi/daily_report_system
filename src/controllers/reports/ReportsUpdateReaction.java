package controllers.reports;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Reaction;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class UpdateReaction
 */
@WebServlet("/reports/update_reaction")
public class ReportsUpdateReaction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsUpdateReaction() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String _token = (String) request.getParameter("_token");
        // if(_token != null && _token.equals(request.getSession().getId())) {
        EntityManager em = DBUtil.createEntityManager();

        Reaction react = new Reaction();
        Report r = em.find(Report.class, (Integer)(request.getSession().getAttribute("report_id")));

        Employee e = (Employee) request.getSession().getAttribute("login_employee");
        Integer employee_id = e.getId();
        Integer report_id = (Integer) request.getSession().getAttribute("report_id");

        react.setEmp_id(employee_id);
        react.setRep_id(report_id);

        em.getTransaction().begin();
        em.persist(react);
        em.getTransaction().commit();

        List<Reaction> reactions = em.createNamedQuery("getAllReactions", Reaction.class).getResultList();

        // 全件数を取得
        long reactions_count = (long)em.createNamedQuery("getReactionsCount", Long.class)
                                           .setParameter("rep_id", report_id)
                                           .getSingleResult();

        // 自動採番されたIDの値を表示
        response.getWriter().append(Integer.valueOf(reactions.size()).toString());
        em.close();

        request.setAttribute("report", r);
        request.setAttribute("reactions_count", reactions_count);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show_reaction.jsp");
        rd.forward(request, response);
        //  } else {
        /**   em.getTransaction().begin();
        em.getTransaction().commit();
        //    request.getSession().setAttribute("flush", "更新が完了しました。");
        em.close();

        request.getSession().removeAttribute("report_id");
        request.getSession().removeAttribute("reaction_id");

        response.sendRedirect(request.getContextPath() + "/reports/index");
        }*/
    }
}

// }
