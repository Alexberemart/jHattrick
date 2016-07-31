package com.alexberemart.jHattrick.model.vo.transfers_team;

import java.util.Date;

public class TransfersTeamTransfersTransfer {

    protected Integer transferId;
    protected Integer price;
    protected Date deadLine;
    protected TransfersTeamTransfersTransferPlayer transfersTeamTransfersTransferPlayer;
    protected TransfersTeamTransfersTransferBuyer transfersTeamTransfersTransferBuyer;
    protected TransfersTeamTransfersTransferSeller transfersTeamTransfersTransferSeller;

    public Integer getTransferId() {
        return transferId;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }

    public TransfersTeamTransfersTransferPlayer getTransfersTeamTransfersTransferPlayer() {
        return transfersTeamTransfersTransferPlayer;
    }

    public void setTransfersTeamTransfersTransferPlayer(TransfersTeamTransfersTransferPlayer transfersTeamTransfersTransferPlayer) {
        this.transfersTeamTransfersTransferPlayer = transfersTeamTransfersTransferPlayer;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public TransfersTeamTransfersTransferBuyer getTransfersTeamTransfersTransferBuyer() {
        return transfersTeamTransfersTransferBuyer;
    }

    public void setTransfersTeamTransfersTransferBuyer(TransfersTeamTransfersTransferBuyer transfersTeamTransfersTransferBuyer) {
        this.transfersTeamTransfersTransferBuyer = transfersTeamTransfersTransferBuyer;
    }

    public TransfersTeamTransfersTransferSeller getTransfersTeamTransfersTransferSeller() {
        return transfersTeamTransfersTransferSeller;
    }

    public void setTransfersTeamTransfersTransferSeller(TransfersTeamTransfersTransferSeller transfersTeamTransfersTransferSeller) {
        this.transfersTeamTransfersTransferSeller = transfersTeamTransfersTransferSeller;
    }
}
