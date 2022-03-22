package vn.gpay.gsmart.core.api.timesheet_absence;

import java.util.Date;

public final class OffWorkRequest {
    private long orgIdLink;
    private Date fromDate;
    private Date toDate;

    public OffWorkRequest(long orgIdLink, Date fromDate, Date toDate) {
        this.orgIdLink = orgIdLink;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public long getOrgIdLink() {
        return orgIdLink;
    }

    public void setOrgIdLink(long orgIdLink) {
        this.orgIdLink = orgIdLink;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }
}
