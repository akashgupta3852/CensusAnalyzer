package censusanalyser;

public class CSVBuilderException extends Exception {
	private static final long serialVersionUID = 1L;

	enum ExceptionType {
		UNABLE_TO_PARSE
	}

	ExceptionType type;

	public CSVBuilderException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
}
