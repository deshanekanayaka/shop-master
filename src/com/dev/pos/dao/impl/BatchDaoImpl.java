package com.dev.pos.dao.impl;

import com.dev.pos.Entity.Batch;
import com.dev.pos.dao.CrudUtil;
import com.dev.pos.dao.custom.BatchDao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class BatchDaoImpl implements BatchDao{
    @Override
    public boolean save(Batch batch) throws SQLException, ClassNotFoundException {
        String sql = "insert into batch values(?,?,?,?,?,?,?,?)";
        return CrudUtil.execute(sql,
                batch.getCode(),
                batch.getBarcode(),
                batch.getQtyOnHand(),
                batch.getSellingPrice(),
                batch.isAvailable(),
                batch.getShowPrice(),
                batch.getBuyingPrice(),
                batch.getProductCode()

        );
    }

    @Override
    public boolean update(Batch batch) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Batch find(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Batch> findAll() throws SQLException, ClassNotFoundException {
        return Collections.emptyList();
    }

    @Override
    public List<Batch> search(String s) throws SQLException, ClassNotFoundException {
        return Collections.emptyList();
    }
}
