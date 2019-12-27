package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.DealDao;
import domain.Deal;
import domain.Deal;
import service.DealService;
import service.DealService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/deal.ctl")
public class DealController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //读取参数id
        String acctId_str = request.getParameter("acctId");
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
//        System.out.println(acctId_str);
//        System.out.println(beginTime);
//        System.out.println(endTime);
        //System.out.println(acct_str);
        //创建JSON对象message，以便往前端响应信息
        JSONObject message = new JSONObject();
        try {
            if (acctId_str != null) {
                if (beginTime == null && endTime == null) {
                    int acctId = Integer.parseInt(acctId_str);
                    responseDealsByAcctId(acctId, response);
                    //System.out.println(39);
                } else {
                    int acctId = Integer.parseInt(acctId_str);
                    responseDealsAccurately(acctId, beginTime, endTime, response);
                    //System.out.println(43);
                }
            } else {
                if (beginTime == null && endTime == null) {
                    responseDeals(response);
                    //System.out.println(48);
                } else {
                    responseDealsByDate(beginTime, endTime, response);
                    //System.out.println(52);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message.put("message", "数据库操作异常");
            //响应message到前端
            response.getWriter().println(message);
        } catch (Exception e) {
            message.put("message", "网络异常");
            e.printStackTrace();
            //响应message到前端
            response.getWriter().println(message);
        }
    }


    private void responseDealsAccurately(int acctId, String beginTime, String endTime, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        //获得所有
        Collection<Deal> deals = DealService.getInstance().findAllAccruately(acctId, beginTime, endTime);
        String deals_json = JSON.toJSONString(deals);
        //控制台打印结果
        //System.out.println(deals_json);
        //浏览器展示结果
        response.getWriter().println(deals_json);
    }

    private void responseDeals(HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //获得所有
        Collection<Deal> deals = DealService.getInstance().findAll();
        String deals_json = JSON.toJSONString(deals);
        //控制台打印结果
        //System.out.println(deals_json);
        //浏览器展示结果
        response.getWriter().println(deals_json);
    }

    private void responseDealsByAcctId(int acctId, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        Collection<Deal> deals = DealService.getInstance().findAllByAcctId(acctId);
        String deals_json = JSON.toJSONString(deals);
        //控制台打印结果
        //System.out.println(deals_json);
        //浏览器展示结果
        response.getWriter().println(deals_json);
    }

    private void responseDealsByDate(String beginTime, String endTime, HttpServletResponse response) throws SQLException, ClassNotFoundException, IOException {
        Collection<Deal> deals = DealService.getInstance().findAllByDate(beginTime, endTime);
        String deals_json = JSON.toJSONString(deals);
        //控制台打印结果
        //System.out.println(deals_json);
        //浏览器展示结果
        response.getWriter().println(deals_json);
    }
}
