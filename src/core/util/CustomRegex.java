package core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomRegex {

	public boolean matches(Pattern pat, String teks)
	{
		return pat.matcher(teks).matches();
	}
	
	
	public String getFirstOccur(String pat, String teks)
	{
		Pattern p = Pattern.compile(pat);
		Matcher matcher = p.matcher(teks);
		if (matcher.find())
		    return matcher.group();
		
		return null;
	}
}
