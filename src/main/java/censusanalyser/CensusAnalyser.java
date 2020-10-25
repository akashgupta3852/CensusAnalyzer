package censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import censusbuilder.*;

public class CensusAnalyser {

	@SuppressWarnings("unchecked")
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		checkFileType(csvFilePath);
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder<IndiaCensusCSV> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<IndiaCensusCSV> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, IndiaCensusCSV.class);
			return this.getCount(censusCSVIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (CSVBuilderException e) {
			throw new CensusAnalyserException(e.getMessage(), e.type.name());
		}
	}

	@SuppressWarnings("unchecked")
	public int loadIndiaStateCode(String csvFilePath) throws CensusAnalyserException {
		checkFileType(csvFilePath);
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder<StateCodeCSV> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<StateCodeCSV> codeCSVIterator = csvBuilder.getCSVFileIterator(reader, StateCodeCSV.class);
			return this.getCount(codeCSVIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CODE_FILE_PROBLEM);
		} catch (CSVBuilderException e) {
			throw new CensusAnalyserException(e.getMessage(), e.type.name());
		}
	}

	public <E> int getCount(Iterator<E> csvIterator) {
		Iterable<E> censusCSVIterable = () -> csvIterator;
		return (int) StreamSupport.stream(censusCSVIterable.spliterator(), false).count();
	}
	
	public void checkFileType(String csvFilePath) throws CensusAnalyserException {
		if(!csvFilePath.endsWith(".csv"))
			throw new CensusAnalyserException("Incorrect file type",
					CensusAnalyserException.ExceptionType.SOME_FILE_ISSUE);
	}
	
}
