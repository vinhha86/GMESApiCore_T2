package vn.gpay.gsmart.core.api.Stockout_order;

import java.util.List;

import vn.gpay.gsmart.core.base.ResponseBase;
import vn.gpay.gsmart.core.stockout_order.Stockout_order_pkl;

public class get_pkl_by_detail_response extends ResponseBase{
	public List<Stockout_order_pkl> data;
}
