package im.castor.AddressBook;

/**
 * @author Maochen Guan, N19993999
 * @version Version 1.0.1
 */

import java.io.*;
import java.util.*;

/**
 * @author Maochen Guan
 */
class LoadFailException extends Exception{
	private static final long serialVersionUID = -5750060651063863053L;
	public LoadFailException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
}

/**
 * A simple Address Book API that provides add, remove, search, load from file and save to file operation. The data is stored in plain mode with any extension in the filename.
 * @author Maochen Guan
 */
public class AddressBook {
	private enum SearchType{
		BY_NAME,BY_ADDRESS,BY_PHONE_NUMBER,BY_EMAIL, BY_NOTE
	}
	private ArrayList<Record> records; //the vector that store all data.
	private Record parse(String buffer){	//parse the string come by load function to Record. Affiliated to Load Function
		String[] fields=buffer.split("\\|");

		if(fields.length==5){
			Record rec1=new Record(fields[0],fields[1],fields[2],fields[3],fields[4]);
			return rec1;
		}
		else{
			throw new RuntimeException("Can not parse: "+buffer);
		}

	}
	private ArrayList<Record> searchTraverser(String criteria,SearchType searchType) {	//Generic search Traverser
		ArrayList<Record> result= new ArrayList<Record>();
		String currentRecordField;

		for(int i = 0; i < records.size(); i ++) {
			Record currentRecord = records.get(i);
			
			switch (searchType){
				case BY_NAME:currentRecordField=currentRecord.getName();break;
				case BY_PHONE_NUMBER:currentRecordField=currentRecord.getPhoneNo();break;
				case BY_ADDRESS:currentRecordField=currentRecord.getAddress();break;
				case BY_EMAIL:currentRecordField=currentRecord.getEmail();break;
				case BY_NOTE:currentRecordField=currentRecord.getNote();break;
				default:currentRecordField=currentRecord.getName();break;
			}
			
			if(criteria.equals(currentRecordField)) {
				result.add(currentRecord);
				continue;
			}

		}

		return result; 

	}
	
	/**
	 * Default constructor of AddressBook, generate an array list called records.
	 */
	public AddressBook() {
		records= new ArrayList<Record>();
	}

	/**
	 * @param name is the name of record. Null Value prohibited.
	 * @param phone is the phone number of record.
	 * @param address is the address of record.
	 * @param email is the email of record.
	 * @param note is the note of record.
	 * @return True if added successfully.
	 */
	public boolean add(String name, String phone, String address, String email, String note){
			Record record=new Record(name,phone,address,email,note);
			return add(record);
	}
	
	/**
	 * @param record record to be added with valid data check
	 * @return True if added successfully, else throw an exception message.
	 */
	public boolean add(Record record){
		if(record.isValid()){	//Only import those records with non-null name
			records.add(record);
			return true;
		}else{
			throw new RuntimeException("can not add: name is empty");
		}

	}
	/**
	 * @param name	Search by a given name.
	 * @return returns an ArrayList of matched records.
	 */
	public ArrayList<Record> searchByName(String name){
		return searchTraverser(name,SearchType.BY_NAME);
	}
	
	/**
	 * @param phone Search by a given Phone number.
	 * @return returns an ArrayList of matched records.
	 */
	public ArrayList<Record> searchByPhone(String phone){
		return searchTraverser(phone,SearchType.BY_PHONE_NUMBER);
	}
	
	/**
	 * @param address Search by a given Address.
	 * @return returns an ArrayList of matched records.
	 */
	public ArrayList<Record> searchByAddress(String address){
		return searchTraverser(address,SearchType.BY_ADDRESS);
	}
	
	/**
	 * @param email Search by a given Email.
	 * @return returns an ArrayList of matched records.
	 */
	public ArrayList<Record> searchByEmail(String email){
		return searchTraverser(email,SearchType.BY_EMAIL);
	}
	
	/**
	 * @param note Search by a given Note.
	 * @return returns an ArrayList of matched records.
	 */
	public ArrayList<Record> searchByNote(String note){
		return searchTraverser(note,SearchType.BY_NOTE);
	}
	

	/**
	 * @param record Delete one specific record in ArrayList by this field
	 * @return True, if the record is find. False, none of the records in ArrayList matches the incoming argument record.
	 */
	public boolean delete(Record record){
		int index=this.records.indexOf(record);
		if (index == -1){
			return false;
		}
		this.records.remove(index);
		return true;
	}

	/**
	 * @return All of the Records in the list will be returned packed into ArrayList.
	 */
	public ArrayList<Record> getRecords() {
		return records;
	}

	/**
	 * @param filename An argument specific the save location.
	 * @throws IOException Exception caused by file write error.
	 */
	public void save(String filename) throws IOException	{
		String writeFormat;

		File f = new File(filename);
		if (f.exists()==true){
			f.delete();
		}


		FileWriter fileWriter=new FileWriter(f);	//A new FileWriter object that put records to file
		BufferedWriter out = new BufferedWriter(fileWriter);
		try{
			for(Record record: records){
				writeFormat= record.getName() + "|" + record.getPhoneNo() + "|" + record.getAddress() + "|" + record.getEmail() + "|" + record.getNote();
				out.write(writeFormat);
				out.newLine();
			}
		}finally{
			out.close();
			fileWriter.close();
		}


	}

	/**
	 * @param filename Accept the Input File Name
	 * @param append When True, newly imported records will append to the last of previous records, otherwise, False, previous records will be overrided.
	 * @throws FileNotFoundException will be thrown out if the file does not exists.
	 */
	public void load(String filename,boolean append) throws LoadFailException{	//append=true that will add the records in the file to the end of the previous pVec
		try {
			File f = new File(filename);
			if (f.exists()==false){
				throw new FileNotFoundException(filename);
			}

			if(!append){	//clear all of the previous record in pVec if not in append mode.
				records.clear();
			}

			ArrayList<Record> newRecords=new ArrayList<Record>();

			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			String bufferline;
			while ((bufferline = br.readLine()) != null) {

				Record record=parse(bufferline);
				newRecords.add(record);
			}

			this.records.addAll(newRecords);
			
			br.close();
		}catch (Exception e) {
			throw new LoadFailException("[File Load Error]"+e.toString());

		}
	}

}
