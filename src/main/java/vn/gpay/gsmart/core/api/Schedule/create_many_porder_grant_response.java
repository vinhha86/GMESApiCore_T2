package vn.gpay.gsmart.core.api.Schedule;

import java.util.List;

import vn.gpay.gsmart.core.Schedule.Schedule_porder;
import vn.gpay.gsmart.core.base.ResponseBase;

public class create_many_porder_grant_response extends ResponseBase{
	public List<Schedule_porder> data;
	public List<Long> remove;
	public Long orgid_link;
	public Long orggrantid_link;
	
}
