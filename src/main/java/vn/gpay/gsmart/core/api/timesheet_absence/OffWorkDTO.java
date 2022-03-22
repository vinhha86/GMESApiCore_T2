package vn.gpay.gsmart.core.api.timesheet_absence;

public final class OffWorkDTO {
    private final String date;
    private final int work;
    private final int offWork;

    public OffWorkDTO(String date, int work, int offWork) {
        this.date = date;
        this.work = work;
        this.offWork = offWork;
    }

    public String getDate() {
        return date;
    }

    public int getWork() {
        return work;
    }

    public int getOffWork() {
        return offWork;
    }
}
