package service;

import dao.DealDao;
import domain.Account;
import domain.Deal;

import java.sql.SQLException;
import java.util.Collection;

public final class DealService {
    private static DealDao dealDao = DealDao.getInstance();
    private static DealService dealService = new DealService();

    private DealService() {
    }

    public static DealService getInstance() {
        return dealService;
    }

    //定义方法根据外键id查询
    public Collection<Deal> findAllByAcctId(int acctId) throws SQLException, ClassNotFoundException {
        return dealDao.findAllByAcctId(acctId);
    }

    //定义相关所需的方法(增删改查)
    public Collection<Deal> findAll() {
        return dealDao.findAll();
    }


    public boolean add(Deal deal) throws SQLException, ClassNotFoundException {
        return dealDao.add(deal);
    }

    public Collection<Deal> findAllAccruately(int acctId, String beginTime, String endTime) throws SQLException, ClassNotFoundException {
        return dealDao.findAllAccruately(acctId, beginTime, endTime);
    }

    public Collection<Deal> findAllByDate(String beginTime, String endTime) throws SQLException, ClassNotFoundException {
        return dealDao.findAllByDate(beginTime, endTime);
    }


}

