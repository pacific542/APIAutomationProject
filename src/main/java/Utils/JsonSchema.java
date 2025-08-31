package Utils;

import java.io.File;

public class JsonSchema {

	public static File getSchema(String filename)
	{
		File schema=new File("Resource/Schema/"+filename);
		return schema;
	}
	public static File getBody(String filename)
	{
		File body=new File("Resource/TestData/"+filename);
		return body;
	}
}
