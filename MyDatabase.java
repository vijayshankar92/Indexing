import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class MyDatabase {

	public static void main(String[] args) {
		//System.out.println("Main() entry");
		Scanner scanner;
		try {
			int choice1, choice2;
			readDatabase();
			scanner = new Scanner(System.in);
			System.out.println("Enter your choice of opertaions:");
			System.out
					.println(" 1.Select\n 2.Insert\n 3.Delete\n 4.Modify\n 5.Count\n 6.Exit\n");
			choice1 = scanner.nextInt();
			do {
				switch (choice1) {
				case 1:
					selectOprn();
					break;
				case 2:
					insertOprn();
					break;
				case 3:
					deleteOprn();
					break;
				case 4:
					modifyOprn();
					break;
				case 5:
					countOprn();
					break;
				default:
					System.out.println("Enter valid input");
					break;
				}
				System.out.println("Enter your choice of opertaions:");
				System.out
				.println(" 1.Select\n 2.Insert\n 3.Delete\n 4.Modify\n 5.Count\n 6.Exit\n");
				choice1 = scanner.nextInt();
			} while (choice1 != 6);
		} catch (Exception e) {
			System.err.println("Exception in Main() method " + e);
			e.printStackTrace();
		}
		//System.out.println("Main() exit");
	}

	private static void selectOprn() {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		try {
			int choice1;
			//readDatabase();
			
			System.out.println("Enter your choice of select:");
			System.out
					.println(" 1.Id\n 2.Last Name\n 3.City\n 4.Main Menu\n");
			choice1 = Integer.parseInt(br.readLine());
			do {
				switch (choice1) {
				case 1:
					System.out.println("Enter Id:");
					int id=Integer.parseInt(br.readLine());
					selectid(id);
					break;
				case 2:
					System.out.println("Enter Last Name:");
					String lname=br.readLine();
					selectlname(lname);
					break;
				case 3:
					System.out.println("Enter City:");
					String city=br.readLine();
					selectcity(city);
					break;
				default:
					System.out.println("Enter valid input");
					break;
				}
				System.out.println("Enter your choice of select:");
				System.out
						.println(" 1.Id\n 2.Last Name\n 3.City\n 4.Main Menu\n");
				choice1 = Integer.parseInt(br.readLine());
			} while (choice1 != 4);
		} catch (Exception e) {
			System.err.println("Exception in selectOpern() method " + e);
			e.printStackTrace();
		}
		
		
	}

	private static void selectcity(String city) {
		// TODO Auto-generated method stub
		HashMap<String, ArrayList<Long>> recityMap = generateNC("city.ndx");
		try
		{
			Iterator<Entry<String, ArrayList<Long>>> it5 = recityMap.entrySet().iterator();
			int count=0;
			while (it5.hasNext()) {
				Entry<String, ArrayList<Long>> entry = it5.next();
				
				//System.out.println(entry.getKey() + ","+ entry.getValue()+"\n");
			String tempStr=entry.getKey();
		if(tempStr.equalsIgnoreCase(city))	
		{	
		    ArrayList<Long> tempArr=recityMap.get(tempStr);
			for(int i=0;i<tempArr.size();i++)
			{
				long offset=tempArr.get(i);

				RandomAccessFile r2 = new RandomAccessFile(
						"data.db",
						"rw");
				r2.seek(offset);
				StringBuffer temp = new StringBuffer();
				Character tempChar = (char) r2.readByte();

				while (tempChar != '!') {
					temp.append(tempChar);
					tempChar = (char) r2.readByte();
				}
				System.out.println(temp);
			}
			
		}
		else
		{
			count++;
		}
		}
			if(count==recityMap.size())
			{
				System.out.println("Invalid City");
			}
		}
		catch(Exception e)
		{
			
		}
	}

	private static void selectlname(String lname) {
		// TODO Auto-generated method stub
		HashMap<String, ArrayList<Long>> renameMap = generateNC("lname.ndx");
		try
		{
			Iterator<Entry<String, ArrayList<Long>>> it5 = renameMap.entrySet().iterator();
			int count=0;
			while (it5.hasNext()) {
				Entry<String, ArrayList<Long>> entry = it5.next();
				
				//System.out.println(entry.getKey() + ","+ entry.getValue()+"\n");
			String tempStr=entry.getKey();
		if(tempStr.equalsIgnoreCase(lname))	
		{   
			
			ArrayList<Long> tempArr=renameMap.get(tempStr);
			for(int i=0;i<tempArr.size();i++)
			{
				long offset=tempArr.get(i);

				RandomAccessFile r2 = new RandomAccessFile(
						"data.db",
						"rw");
				r2.seek(offset);
				StringBuffer temp = new StringBuffer();
				Character tempChar = (char) r2.readByte();

				while (tempChar != '!') {
					temp.append(tempChar);
					tempChar = (char) r2.readByte();
				}
				System.out.println(temp);
			}
			
		}
		else
		{
			count++;
		}
		}
			if(count==renameMap.size())
			{
				System.out.println("Invalid Last Name");
			}
		}
		catch(Exception e)
		{
			
		}
		
	}

	private static void selectid(int id) {
		// TODO Auto-generated method stub
		HashMap<Integer, Long> reidMap=generateId("id.ndx");
		try {
			
		if(reidMap.containsKey(id))
		{
			long offset=reidMap.get(id);
		
				RandomAccessFile r2 = new RandomAccessFile(
						"data.db",
						"rw");
			r2.seek(offset);
			StringBuffer temp = new StringBuffer();
			Character tempChar = (char) r2.readByte();

			while (tempChar != '!') {
				temp.append(tempChar);
				tempChar = (char) r2.readByte();
			}
			System.out.println(temp);
			
		}
		else
		{
			System.out.println("Invalid ID");
		}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	private static void insertOprn() {
		System.out.println("Enter the String to be inserted into the database:\n");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		try {
			String str=br.readLine();
			HashMap<Integer, Long> reidMap=generateId("id.ndx");
			HashMap<String, ArrayList<Long>> renameMap = generateNC("lname.ndx");
			HashMap<String, ArrayList<Long>> recityMap = generateNC("city.ndx");
			String temp=str.replaceAll("\',\'", "~");
			temp=temp.replaceAll("\"\'","");
			String[] check=temp.split("~");
			str=str.replaceAll("\"\'","\"");
			str=str.replaceAll("\',\'","\",\"");
			int id=Integer.parseInt(check[0]);
			String lname=check[2];
			String city=check[5];
			String emailid=check[11];
			//System.out.println(emailid);
			StringBuffer result=new StringBuffer();
			for(int j=0;j<check.length;j++)
			{ /* System.out.println(j);*/
				if(j==0)
				{
					result.append("\"");
					result.append(check[j]);
					result.append("\"");
				}
				else if(j==check.length-1)
				{   
					result.append(",\"");
					result.append(check[j].substring(0,check[j].length()-1));
					result.append("\"");
				}
				else if(j==8)
				{
					result.append(",");
					result.append(check[j]);
					
				}
				else
				{   
					result.append(",\"");
					result.append(check[j]);
					result.append("\"");
				}
				
			}
			Iterator<Entry<Integer,Long>> it5 = reidMap.entrySet().iterator();
			HashMap<Integer, String> email=new HashMap<Integer,String>();
			int count=0;
			while (it5.hasNext()) {
				Entry<Integer,Long> entry = it5.next();
				
				long offset=entry.getValue();
				RandomAccessFile r2 = new RandomAccessFile("data.db",
						"rw");
				
					r2.seek(offset);

					StringBuffer buffer = new StringBuffer();
					Character tempChar = (char) r2.readByte();

					while (tempChar != '!') {
						buffer.append(tempChar);
						tempChar = (char) r2.readByte();
					}
			
					String checkar = buffer.toString().replaceAll("\",\"", "~");
					
					checkar=checkar.replaceAll("\",", "~");
					checkar=checkar.replaceAll(",\"", "~");
					checkar= checkar.replaceAll("\"", "");
					String[] tempStr = checkar.toString().split("~");
				   email.put(count, tempStr[11]);
				   count++;
					//System.out.println(tempStr[11]);
				
			}
			
			if(check.length!=13)
			{  
				System.out.println("Format of String is wrong");
			}
			else if(reidMap.containsKey(Integer.parseInt(check[0])))
			{
				System.out.println("Id is already present");
			}
			else if(email.containsValue(emailid))
			{
				System.out.println("Email Id already present");
			}
			else if(check.length==13)
			{
				
					RandomAccessFile r = new RandomAccessFile("data.db", "rw");
					long offset = (long) r.length();
					FileWriter fw = new FileWriter("data.db", true);
					fw.write(result.toString());
					fw.write("\n!");
					fw.close();
					System.out.println("Inserted Successfully");
					reidMap.put(id, offset);
					
					if (renameMap.containsKey(lname)) {
						renameMap.get(lname).add(offset);
						// System.out.println(nameMap.get(tempStr[2]).size());
					} else {
						ArrayList sampleList = new ArrayList<Long>();
						sampleList.add(offset);
						renameMap.put(lname, sampleList);
					}
					
					if (recityMap.containsKey(city)) {
						recityMap.get(city).add(offset);
						// System.out.println(nameMap.get(tempStr[2]).size());
					} else {
						ArrayList sampleList = new ArrayList<Long>();
						sampleList.add(offset);
						recityMap.put(city, sampleList);
					}
					
					indexID(reidMap);
					indexName(renameMap);
					indexCity(recityMap);
			}
			else
			{
				System.out.println("Invalid Format");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void deleteOprn() {
		System.out.println("Enter id to be removed");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		HashMap<Integer, Long> reidMap=generateId("id.ndx");
		HashMap<String, ArrayList<Long>> renameMap = generateNC("lname.ndx");
		HashMap<String, ArrayList<Long>> recityMap = generateNC("city.ndx");
		try {
			int id=Integer.parseInt(br.readLine());
			if(reidMap.containsKey(id))
			{
		
				long offset=reidMap.get(id);
				StringBuffer bu=new StringBuffer();
				RandomAccessFile r2 = new RandomAccessFile(
						"data.db",
						"rw");
	
			r2.seek(offset);
			StringBuffer tempBuff=new StringBuffer();
			Character tempChar = (char) r2.readByte();
			int coun=0;
			while (tempChar != '!') {
				coun++;
			 if(tempChar!='\"' && tempChar!=',')
			 {	 
			 bu.append("0");
			 }
			 else
			 {
				 bu.append(tempChar);
			 }
			 tempBuff.append(tempChar);
			 tempChar = (char) r2.readByte();
			}
	        String temp= bu.substring(0,bu.length()-1);
			temp+="\n!";
			r2.seek(offset);
			r2.write(temp.getBytes());
			String check = tempBuff.toString().replaceAll("\",\"", "~");
			check = check.replaceAll("\"", "");
			check=check.replaceAll("\",", "~");
			check=check.replaceAll(",\"", "~");
			String[] tempStr = check.toString().split("~");
			//System.out.println(check+"\n"+tempStr);
			String lname=tempStr[2];
			String city=tempStr[5];
			reidMap.remove(id);
			recityMap.get(city).remove(recityMap.get(city).indexOf(offset));
			renameMap.get(lname).remove(renameMap.get(lname).indexOf(offset));
			
			indexID(reidMap);
			indexName(renameMap);
			indexCity(recityMap);
	
			System.out.println("Record is removed from the database");
			}
			else
			{
				System.out.println("ID not present in database");
			}
		}
		catch(Exception e)
		{  System.out.println(e);
			e.printStackTrace();
		}
	}

	private static void modifyOprn() {

		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		try {
			int choice1;
			//readDatabase();
			System.out.println("Enter the id to modify:");
			int id=Integer.parseInt(br.readLine());
			System.out.println("Select the column to modify:");
			System.out.println(" 1.First Name\n 2.Last Name\n 3.Company Name\n 4.Address\n 5.City\n 6.County\n 7.State\n 8.Zip\n 9.Phone1\n 10.Phone2\n 11.Email\n 12.Web \n 13.Main Menu\n");
			choice1 = Integer.parseInt(br.readLine());
			do {
				switch (choice1) {
				case 1:
					System.out.println("Enter First Name:");
					String fname=br.readLine();
					modify(fname,1,id);
					break;
				case 2:
					System.out.println("Enter Last Name:");
					String lname=br.readLine();
					modify(lname,2,id);
					break;
				case 3:
					System.out.println("Enter Company Name:");
					String company=br.readLine();
					modify(company,3,id);
					break;
				case 4:
					System.out.println("Enter Address:");
					String address=br.readLine();
					modify(address,4,id);
					break;
				case 5:
					System.out.println("Enter City:");
					String city=br.readLine();
					modify(city,5,id);
					break;
				case 6:
					System.out.println("Enter County:");
					String county=br.readLine();
					modify(county,6,id);
					break;
				case 7:
					System.out.println("Enter State:");
					String state=br.readLine();
					modify(state,7,id);
					break;
				case 8:
					System.out.println("Enter Zip:");
					String zip=br.readLine();
					modify(new String(zip+""),8,id);
					break;
				case 9:
					System.out.println("Enter Phone1:");
					String phone1=br.readLine();
					modify(phone1,9,id);
					break;
				case 10:
					System.out.println("Enter Phone2:");
					String phone2=br.readLine();
					modify(phone2,10,id);
					break;
				case 11:
					System.out.println("Enter Email:");
					String email=br.readLine();
					modify(email,11,id);
					break;
				case 12:
					System.out.println("Enter Web:");
					String web=br.readLine();
					modify(web,12,id);
					break;
				default:
					System.out.println("Enter valid input");
					break;
				}
				System.out.println("Select the column to modify:");
				System.out.println(" 1.First Name\n 2.Last Name\n 3.Company Name\n 4.Address\n 5.City\n 6.County\n 7.State\n 8.Zip\n 9.Phone1\n 10.Phone2\n 11.Email\n 12.Web \n 13.Main Menu\n");
				choice1 = Integer.parseInt(br.readLine());
			} while (choice1 != 13);
		} catch (Exception e) {
			System.err.println("Exception in selectOpern() method " + e);
			e.printStackTrace();
		}
	
	}

	private static void modify(String sample, int i,int id) {
		// TODO Auto-generated method stub
		HashMap<Integer, Long> reidMap=generateId("id.ndx");
		HashMap<String, ArrayList<Long>> renameMap = generateNC("lname.ndx");
		HashMap<String, ArrayList<Long>> recityMap = generateNC("city.ndx");
		Iterator<Entry<Integer,Long>> it5 = reidMap.entrySet().iterator();
		HashMap<Integer, String> email=new HashMap<Integer,String>();
		int counter=0;
		while (it5.hasNext()) {
			Entry<Integer,Long> entry = it5.next();
			
			long offset=entry.getValue();
			RandomAccessFile r2;
			try {
				r2 = new RandomAccessFile("data.db",
						"rw");
		
			
				r2.seek(offset);

				StringBuffer buffer = new StringBuffer();
				Character tempChar = (char) r2.readByte();

				while (tempChar != '!') {
					buffer.append(tempChar);
					tempChar = (char) r2.readByte();
				}
		
				String checkar = buffer.toString().replaceAll("\",\"", "~");
				
				checkar=checkar.replaceAll("\",", "~");
				checkar=checkar.replaceAll(",\"", "~");
				checkar= checkar.replaceAll("\"", "");
				String[] tempStr = checkar.toString().split("~");
			   email.put(counter, tempStr[11]);
			   counter++;
				//System.out.println(tempStr[11]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		if(reidMap.containsKey(id))
		{   if(i==11 && email.containsValue(sample))
		   {
		System.out.println("Email ID already present");	
	    	}
		else {
			try {
				long offset=reidMap.get(id);
				StringBuffer bu=new StringBuffer();
				RandomAccessFile r2 = new RandomAccessFile(
							"data.db",
							"rw");
				r2.seek(offset);
				StringBuffer tempBuff=new StringBuffer();
				Character tempChar = (char) r2.readByte();
				while (tempChar != '!') {
				 tempBuff.append(tempChar);
				 tempChar = (char) r2.readByte();
				}
				//System.out.println(tempBuff);
				String check = tempBuff.toString().replaceAll("\",\"", "~");
				
				check=check.replaceAll("\",", "~");
				check=check.replaceAll(",\"", "~");
				check = check.replaceAll("\"", "");
				
				//System.out.println(check+"kkj");
				String[] tempStr = check.toString().split("~");
				tempStr[i]=sample;
				StringBuffer result=new StringBuffer();
				//System.out.println("Len"+tempStr.length);
				for(int j=0;j<tempStr.length;j++)
				{ /* System.out.println(j);*/
					if(j==0)
					{
						result.append("\"");
						result.append(tempStr[j]);
						result.append("\"");
					}
					else if(j==tempStr.length-1)
					{   
						result.append(",\"");
						result.append(tempStr[j].substring(0,tempStr[j].length()-1));
						result.append("\"");
					}
					else if(j==8)
					{
						result.append(",");
						result.append(tempStr[j]);
						
					}
					else
					{   
						result.append(",\"");
						result.append(tempStr[j]);
						result.append("\"");
					}
					
				}
				result.append("\n!");
				//System.out.println(result);
				
				
				StringBuffer buffer=new StringBuffer();
				for (int k = 0; k < r2.length(); k++) {
					r2.seek(k);
					Character eachChar = (char) r2.readByte();
                    
					
					
						buffer.append(eachChar);
				
                    }
						
                StringBuffer temp=new StringBuffer();
				String arr[]=buffer.toString().split("!");
				
				for(int c=0;c<arr.length;c++)
				{ 
					if(arr[c].startsWith("\""+id+"\""))
					{  
						temp.append(result);
					}
					else
						
					{
						temp.append(arr[c]+"\n!");
					}
				}
				
				
				File data = new File(
						"data.db");
				FileOutputStream fos = new FileOutputStream(data);
				fos.write(temp.toString().getBytes());
				fos.close();
				RandomAccessFile r1 = new RandomAccessFile(
						"data.db",
						"rw");
				long value[]=new long[510];
				int count = 0;
				for (int n = 0; n < r1.length(); n++) {
					r1.seek(n);
					Character eachChar = (char) r1.readByte();
					if (eachChar == '!') 
					{
						value[count] = r1.getFilePointer();
						count++;
						
					}
				}
				
				HashMap<Integer, Long> idMap = new HashMap<Integer, Long>();
				HashMap<String, ArrayList<Long>> nameMap = new HashMap<String, ArrayList<Long>>();
				HashMap<String, ArrayList<Long>> cityMap = new HashMap<String, ArrayList<Long>>();
				
		
				
				RandomAccessFile r3 = new RandomAccessFile(
						"data.db",
						"rw");
				ArrayList<Long> sampleList;
				
				for (int s = 0; s < count-1; s++) {
					r3.seek(value[s]);

					StringBuffer te = new StringBuffer();
					Character teChar = (char) r3.readByte();

					while (teChar != '!') {
						te.append(teChar);
						teChar = (char) r3.readByte();
					}
			
					String checkStr = te.toString().replaceAll("\",\"", "~");
					
					checkStr=checkStr.replaceAll("\",", "~");
					checkStr=checkStr.replaceAll(",\"", "~");
					checkStr = checkStr.replaceAll("\"", "");
					String[] teStr = checkStr.toString().split("~");
					// name Hashmap Begins
					/*System.out.println(checkStr);
					System.out.println(teStr); */
					int countOfStr=0;
					for(int m=0;m<teStr[1].length();m++)
					{
						if(teStr[1].charAt(m)=='0')
						{
							countOfStr++;
						}
					}
					if(countOfStr!=teStr[1].length())
					{		
					if (nameMap.isEmpty()) {
						sampleList = new ArrayList<Long>();
						sampleList.add(value[s]);
						nameMap.put(teStr[2], sampleList);
					} else {
						if (nameMap.containsKey(teStr[2])) {
							nameMap.get(teStr[2]).add(value[s]);
							// System.out.println(nameMap.get(teStr[2]).size());
						} else {
							sampleList = new ArrayList<Long>();
							sampleList.add(value[s]);
							nameMap.put(teStr[2], sampleList);
						}
					}
					// name Hashmap Ends

					// city Hashmap Begins

					if (cityMap.isEmpty()) {
						sampleList = new ArrayList<Long>();
						sampleList.add(value[s]);
						cityMap.put(teStr[5], sampleList);
					} else {
						if (cityMap.containsKey(teStr[5])) {
							cityMap.get(teStr[5]).add(value[s]);
							
						} else {
							sampleList = new ArrayList<Long>();
							sampleList.add(value[s]);
							cityMap.put(teStr[5], sampleList);
						}
					}
					// city HashMap Ends
					idMap.put(Integer.parseInt(teStr[0]), value[s]);
					}
				}
				
				indexID(idMap);
				indexName(nameMap);
				indexCity(cityMap);
				
			    System.out.println("Value Modified to Database");
			
				}
			catch (Exception e) {
				System.err.println(e);
				e.printStackTrace();
			}
		}
		}
		else
		{
			System.out.println("Id is not present in database");
		}
		
	}

	private static void countOprn() {
		HashMap<Integer, Long> reidMap=generateId("id.ndx");
		System.out.println("Total number of records in table = "+reidMap.size());
	}

	private static void readDatabase() throws IOException {

		long offset[] = new long[501];
		HashMap<Integer, Long> idMap = new HashMap<Integer, Long>();
		HashMap<String, ArrayList<Long>> nameMap = new HashMap<String, ArrayList<Long>>();
		HashMap<String, ArrayList<Long>> cityMap = new HashMap<String, ArrayList<Long>>();
		try {
			RandomAccessFile r1 = new RandomAccessFile(
					"us-500.csv",
					"rw");
			StringBuffer str = new StringBuffer();
			File data = new File("data.db");
			FileOutputStream fos = new FileOutputStream(data);
			int count = 0;
			for (int i = 0; i < r1.length(); i++) {
				r1.seek(i);
				Character eachChar = (char) r1.readByte();

				if (eachChar == '\n') {

					offset[count] = r1.getFilePointer();
					count++;
					str.append("!");
					
				}
				if (eachChar != '\n') {
					str.append(eachChar);
				}

			}
         
			String s = new String(str);

			fos.write(s.getBytes());

			RandomAccessFile r2 = new RandomAccessFile("data.db",
					"rw");
			ArrayList<Long> sampleList;
			
			for (int i = 0; i < count-1; i++) {
				r2.seek(offset[i]);

				StringBuffer temp = new StringBuffer();
				Character tempChar = (char) r2.readByte();

				while (tempChar != '!') {
					temp.append(tempChar);
					tempChar = (char) r2.readByte();
				}
		
				String check = temp.toString().replaceAll("\",\"", "~");
				
				check=check.replaceAll("\",", "~");
				check=check.replaceAll(",\"", "~");
				check = check.replaceAll("\"", "");
				check = check.replaceAll("\'", "");
				String[] tempStr = check.toString().split("~");
				// name Hashmap Begins
				if (nameMap.isEmpty()) {
					sampleList = new ArrayList<Long>();
					sampleList.add(offset[i]);
					nameMap.put(tempStr[2], sampleList);
				} else {
					if (nameMap.containsKey(tempStr[2])) {
						nameMap.get(tempStr[2]).add(offset[i]);
						// System.out.println(nameMap.get(tempStr[2]).size());
					} else {
						sampleList = new ArrayList<Long>();
						sampleList.add(offset[i]);
						nameMap.put(tempStr[2], sampleList);
					}
				}
				// name Hashmap Ends

				// city Hashmap Begins

				if (cityMap.isEmpty()) {
					sampleList = new ArrayList<Long>();
					sampleList.add(offset[i]);
					cityMap.put(tempStr[5], sampleList);
				} else {
					if (cityMap.containsKey(tempStr[5])) {
						cityMap.get(tempStr[5]).add(offset[i]);
						
					} else {
						sampleList = new ArrayList<Long>();
						sampleList.add(offset[i]);
						cityMap.put(tempStr[5], sampleList);
					}
				}
				// city HashMap Ends
				idMap.put(Integer.parseInt(tempStr[0]), offset[i]);
			}
			
			indexID(idMap);
			indexName(nameMap);
			indexCity(cityMap);
	
			
	
			
		
			
		
	/*///	System.out.println(reidMap.size());
			Iterator<Entry<String, ArrayList<Long>>> it5 = recityMap.entrySet().iterator();
			while (it5.hasNext()) {
				Entry<String, ArrayList<Long>> entry = it5.next();
				
				System.out.println(entry.getKey() + ","+ entry.getValue()+"\n");
			}
		*/
			
		} catch (Exception e) {
			System.err.println("exception in readDatabase()"+e);
			e.printStackTrace();
		}
	}

	private static void indexCity(HashMap<String, ArrayList<Long>> cityMap) {
		// TODO Auto-generated method stub
		File cityFile = new File("city.ndx");
		FileWriter fw3;
		try {
			fw3 = new FileWriter(cityFile);
		
		Iterator<Entry<String, ArrayList<Long>>> it3 = cityMap.entrySet().iterator();
		while (it3.hasNext()) {
			Entry<String, ArrayList<Long>> entry = it3.next();
			
            fw3.write(entry.getKey() + ","+entry.getValue()+"\n");
		}
		fw3.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void indexName(HashMap<String, ArrayList<Long>> nameMap) {
		// TODO Auto-generated method stub
		File nameFile = new File("lname.ndx");
		FileWriter fw2;
		try {
			fw2 = new FileWriter(nameFile);
		
		Iterator<Entry<String, ArrayList<Long>>> it2 = nameMap.entrySet().iterator();
		while (it2.hasNext()) {
			Entry<String, ArrayList<Long>> entry = it2.next();
			
            fw2.write(entry.getKey() + ","+entry.getValue()+"\n");
		}
		fw2.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void indexID(HashMap<Integer, Long> idMap) {
		// TODO Auto-generated method stub
		File idFile = new File("id.ndx");
		FileWriter fw1;
		try {
			fw1 = new FileWriter(idFile);
	
		
		Iterator<Entry<Integer, Long>> it1 = idMap.entrySet().iterator();
		while (it1.hasNext()) {
			Entry<Integer, Long> entry = it1.next();
			
            fw1.write(entry.getKey() + ","+entry.getValue()+"\n");
		}
		fw1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static HashMap<String, ArrayList<Long>> generateNC(String string) {
		// TODO Auto-generated method stub
		File tempFile = new File(string);
		HashMap<String, ArrayList<Long>>result=new HashMap<String, ArrayList<Long>>();
		try {
			Scanner scanner=new Scanner(tempFile);
			scanner.useDelimiter("\n");
			while(scanner.hasNext())
			{
				ArrayList<Long> temp=new ArrayList<Long>();
				String str=scanner.next();
				str=str.replace("[", "");
				str=str.replace("]", "");
			    //System.out.println(str);
				String arr[]=str.split(",");
				for(int i=1;i<arr.length;i++)
				{   
					
					temp.add(Long.parseLong(arr[i].trim()));
					
				}
				result.put(arr[0], temp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return result;
	}

	private static HashMap<Integer, Long> generateId(String string) {
		// TODO Auto-generated method stub
		File idFile = new File(string);
		HashMap<Integer, Long> result=new HashMap<Integer, Long>();
		try {
			Scanner scanner=new Scanner(idFile);
			while(scanner.hasNext())
			{
				String str=scanner.next();
				String[] arr=str.split(",");
			    result.put(Integer.parseInt(arr[0]), Long.parseLong(arr[1])); 
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * private static HashMap<String,ArrayList<Long>> createHash(String key,Long
	 * offset, ) {
	 * 
	 * }
	 */
}
