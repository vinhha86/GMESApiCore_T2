package vn.gpay.gsmart.core.api.timesheet_absence;

import vn.gpay.gsmart.core.base.ResponseBase;

import java.util.List;

public final class OffWorkResponse extends ResponseBase {
    private List<OffWorkDTO> data;

    public OffWorkResponse() {
    }

    public OffWorkResponse(List<OffWorkDTO> data) {
        this.data = data;
    }

    public List<OffWorkDTO> getData() {
        return data;
    }

    public void setData(List<OffWorkDTO> data) {
        this.data = data;
    }
}
