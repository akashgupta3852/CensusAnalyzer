package censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.google.gson.Gson;

import censusbuilder.*;

public class CensusAnalyser {
	List<StateCodeCSV> codeCSVList = null;
	List<IndiaCensusCSV> censusCSVList = null;

	@SuppressWarnings("unchecked")
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		checkFileType(csvFilePath);
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder<IndiaCensusCSV> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			censusCSVList = csvBuilder.getCSVFileList(reader, IndiaCensusCSV.class);
			return censusCSVList.size();
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
			codeCSVList = csvBuilder.getCSVFileList(reader, StateCodeCSV.class);
			return codeCSVList.size();
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
		if (!csvFilePath.endsWith(".csv"))
			throw new CensusAnalyserException("Incorrect file type",
					CensusAnalyserException.ExceptionType.SOME_FILE_ISSUE);
	}

	@SuppressWarnings("unchecked")
	public int loadIndiaCensusDataUsingCommon(String csvFilePath) throws CensusAnalyserException {
		checkFileType(csvFilePath);
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder<IndiaCensusCSV> csvBuilder = CSVBuilderFactory.createCommonsCSVBuilder();
			censusCSVList = csvBuilder.getCSVFileList(reader, IndiaCensusCSV.class);
			return censusCSVList.size();
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(),
					CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
		} catch (CSVBuilderException e) {
			throw new CensusAnalyserException(e.getMessage(), e.type.name());
		}
	}

	@SuppressWarnings("unchecked")
	public int loadIndiaStateCodeUsingCommon(String csvFilePath) throws CensusAnalyserException {
		checkFileType(csvFilePath);
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder<StateCodeCSV> csvBuilder = CSVBuilderFactory.createCommonsCSVBuilder();
			codeCSVList = csvBuilder.getCSVFileList(reader, StateCodeCSV.class);
			return codeCSVList.size();
		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CODE_FILE_PROBLEM);
		} catch (CSVBuilderException e) {
			throw new CensusAnalyserException(e.getMessage(), e.type.name());
		}
	}

	public String getStateWiseSortedCensusData() throws CensusAnalyserException {
		checkCSVListNullOrEmpty(censusCSVList);
		Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing((census -> census.state));
		this.sort(censusComparator, censusCSVList);
		String sortedStateCensusJson = new Gson().toJson(censusCSVList);
		return sortedStateCensusJson;
	}

	public String getStateCodeWiseSortedStateCodeData() throws CensusAnalyserException {
		checkCSVListNullOrEmpty(codeCSVList);
		Comparator<StateCodeCSV> stateCodeComparator = Comparator.comparing((stateCode -> stateCode.stateCode));
		this.sort(stateCodeComparator, codeCSVList);
		String sortedStateCodeJson = new Gson().toJson(codeCSVList);
		return sortedStateCodeJson;
	}

	private <E> void sort(Comparator<E> comparator, List<E> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = 0; j < list.size() - i - 1; j++) {
				E census1 = list.get(j);
				E census2 = list.get(j + 1);
				if (comparator.compare(census1, census2) > 0) {
					list.set(j, census2);
					list.set(j + 1, census1);
				}
			}
		}
	}

	public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
		checkCSVListNullOrEmpty(censusCSVList);
		Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing((census -> census.population));
		this.descendingOrderSort(censusComparator, censusCSVList);
		String sortedPopulationCensusJson = new Gson().toJson(censusCSVList);
		return sortedPopulationCensusJson;
	}

	private <E> void descendingOrderSort(Comparator<E> comparator, List<E> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = 0; j < list.size() - i - 1; j++) {
				E census1 = list.get(j);
				E census2 = list.get(j + 1);
				if (comparator.compare(census1, census2) < 0) {
					list.set(j, census2);
					list.set(j + 1, census1);
				}
			}
		}
	}

	public void checkCSVListNullOrEmpty(List csvList) throws CensusAnalyserException {
		if (censusCSVList == null || censusCSVList.size() == 0)
			throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
	}

	public String getPopulationDensityWiseSortedCensusData() throws CensusAnalyserException {
		checkCSVListNullOrEmpty(censusCSVList);
		Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing((census -> census.densityPerSqKm));
		this.descendingOrderSort(censusComparator, censusCSVList);
		String sortedPopulationDensityCensusJson = new Gson().toJson(censusCSVList);
		return sortedPopulationDensityCensusJson;
	}
}
