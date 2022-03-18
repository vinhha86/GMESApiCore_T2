package vn.gpay.gsmart.core.api.balance;

import java.util.Date;
import java.util.List;

public class Balance_Request {
	public Long pcontractid_link;
	public Long pcontract_poid_link;
	public Long porderid_link;
	public Long pordergrantid_link;
	public String list_productid;
	public Long materialid_link = null;
	public String list_materialtypeid;
	public Integer balance_limit = 0; //0-Tinh het; 1-Chi tinh nguyen lieu; 2-Chi tinh phu lieu	
	public List<Date> date_list;
}
