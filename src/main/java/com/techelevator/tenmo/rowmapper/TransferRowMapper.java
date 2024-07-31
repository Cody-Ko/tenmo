package com.techelevator.tenmo.rowmapper;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferRowMapper implements RowMapper<Transfer> {
    @Override
    public Transfer mapRow(ResultSet resultSet, int i) throws SQLException {
        Transfer transfer = new Transfer();
        transfer.setTransferId(resultSet.getInt("transfer_id"));
        transfer.setTransferTypeId(resultSet.getInt("transfer_type_id"));
        transfer.setTransferStatusId(resultSet.getInt("transfer_status_id"));
        transfer.setAccountFrom(resultSet.getInt("account_from"));
        transfer.setAccountTo(resultSet.getInt("account_to"));
        transfer.setAmount(resultSet.getDouble("amount"));
        return transfer;
    }
}
