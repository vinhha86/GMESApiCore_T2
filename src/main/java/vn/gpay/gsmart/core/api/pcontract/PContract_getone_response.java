package vn.gpay.gsmart.core.api.pcontract;

import vn.gpay.gsmart.core.base.ResponseBase;
import vn.gpay.gsmart.core.pcontract.PContract;

import java.util.List;

public class PContract_getone_response extends ResponseBase {
	public PContract data;
	public List<Long> market;
}
