package censusbuilder;

public class CSVBuilderException extends Exception {
	private static final long serialVersionUID = 1L;

	public enum ExceptionType {
		UNABLE_TO_PARSE
	}

	public ExceptionType type;

	public CSVBuilderException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}
}