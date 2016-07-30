package alexberemart.jHattrick.model.vo.transfers_team;

public class TransfersTeamTransfersTransferSeller {

    protected Integer sellerTeamId;
    protected String sellerTeamName;

    public Integer getSellerTeamId() {
        return sellerTeamId;
    }

    public String getSellerTeamName() {
        return sellerTeamName;
    }

    public void setSellerTeamName(String sellerTeamName) {
        this.sellerTeamName = sellerTeamName;
    }

    public void setSellerTeamId(Integer sellerTeamId) {
        this.sellerTeamId = sellerTeamId;
        
    }
}
