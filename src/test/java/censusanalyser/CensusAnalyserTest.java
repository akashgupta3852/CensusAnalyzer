package censusanalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

	private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
	private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
	private static final String INCORRECT_TYPE_INDIA_CENSUS_FILE = "./src/test/resources/IndiaStateCensusData.txt";
	private static final String WRONG_DELIMITER_IN_INDIA_CENSUS_CSV_FILE = "./src/test/resources/WrongDelimiter_IndiaStateCensusData.csv";
	private static final String WITHOUT_HEADER_IN_INDIA_CENSUS_CSV_FILE = "./src/test/resources/WithoutHeader_IndiaStateCensusData.csv";
	private static final String INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
	private static final String INCORRECT_TYPE_STATE_CODE_FILE = "./src/test/resources/IndiaStateCode.txt";
	private static final String WRONG_DELIMITER_IN_STATE_CODE_FILE = "./src/test/resources/WrongDelimiter_IndiaStateCode.csv";
	private static final String WITHOUT_HEADER_IN_STATE_CODE_FILE = "./src/test/resources/WithoutHeader_IndiaStateCode.csv";

	@Test
	public void givenIndianCensusCSVFile_ReturnsCorrectRecords() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
			Assert.assertEquals(29, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaCensusData_WithWrongFileType_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(INCORRECT_TYPE_INDIA_CENSUS_FILE);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.ERROR_FROM_CSV_FILE, e.type);
		}
	}

	@Test
	public void givenIndiaCensusData_WithIncorrectDelimiter_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(WRONG_DELIMITER_IN_INDIA_CENSUS_CSV_FILE);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.ERROR_FROM_CSV_FILE, e.type);
		}
	}

	@Test
	public void givenIndiaCensusData_WithIncorrectHeader_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaCensusData(WITHOUT_HEADER_IN_INDIA_CENSUS_CSV_FILE);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.ERROR_FROM_CSV_FILE, e.type);
		}
	}

	@Test
	public void givenIndiaStateCodeCSVFile_ReturnsCorrectRecords() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			int numOfRecords = censusAnalyser.loadIndiaStateCode(INDIA_STATE_CODE_CSV_FILE_PATH);
			Assert.assertEquals(37, numOfRecords);
		} catch (CensusAnalyserException e) {
		}
	}

	@Test
	public void givenIndiaStateCode_WithWrongFile_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaStateCode(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenIndiaStateCode_WithWrongFileType_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaStateCode(INCORRECT_TYPE_STATE_CODE_FILE);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.ERROR_FROM_CSV_FILE, e.type);
		}
	}

	@Test
	public void givenIndiaStateCode_WithIncorrectDelimiter_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaStateCode(WRONG_DELIMITER_IN_STATE_CODE_FILE);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.ERROR_FROM_CSV_FILE, e.type);
		}
	}

	@Test
	public void givenIndiaStateCode_WithIncorrectHeader_ShouldThrowException() {
		try {
			CensusAnalyser censusAnalyser = new CensusAnalyser();
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(CensusAnalyserException.class);
			censusAnalyser.loadIndiaStateCode(WITHOUT_HEADER_IN_STATE_CODE_FILE);
		} catch (CensusAnalyserException e) {
			Assert.assertEquals(CensusAnalyserException.ExceptionType.ERROR_FROM_CSV_FILE, e.type);
		}
	}
}
