package sample.springboot.service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class has common utility methods
 *
 */
public class CaerusCommonUtility {
	
	/**
	 * This method is used to convert String into Date 
	 * @author PallaviD
	 *  @param dateString
	 * @param dateFormat
	 * @return Date
	 */
	public static Date stringToDate(String dateString,String dateFormat)
	{
		Date date = null;
		
		if(null != dateString)
		{
			try
	
			{
				SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
				formatter.applyPattern(dateFormat);
				date = formatter.parse(dateString);
			}
			catch(Exception e)
			{
			}
		}
		return date;
	}	
}
