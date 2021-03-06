package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class StateCodeCSV {
	@CsvBindByName(column = "SrNo", required = true)
	public int srNo;

	@CsvBindByName(column = "State Name", required = true)
	public String stateName;

	@CsvBindByName(column = "TIN", required = true)
	public int tin;

	@CsvBindByName(column = "StateCode", required = true)
	public String stateCode;
}
