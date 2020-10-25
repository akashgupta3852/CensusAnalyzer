package censusanalyser;

public class CensusAnalyserException extends Exception {
	private static final long serialVersionUID = 1L;

	enum ExceptionType {
		CENSUS_FILE_PROBLEM, CODE_FILE_PROBLEM, SOME_FILE_ISSUE, NO_CENSUS_DATA, NO_STATE_CODE_DATA
	}

	ExceptionType type;
	String exceptionType;

	public CensusAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

	public CensusAnalyserException(String message, ExceptionType type, Throwable cause) {
		super(message, cause);
		this.type = type;
	}

	public CensusAnalyserException(String message, String exceptionType) {
		super(message);
		this.exceptionType = exceptionType;
	}
}
