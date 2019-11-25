package ai.dongsheng.model.vo;


public class SyncKey {
    private String dataModule;
    private long syncKey;

    public String getDataModule() {
        return dataModule;
    }

    public void setDataModule(String dataModule) {
        this.dataModule = dataModule;
    }

    public long getSyncKey() {
        return syncKey;
    }

    public void setSyncKey(long syncKey) {
        this.syncKey = syncKey;
    }
}
