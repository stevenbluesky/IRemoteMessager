package cn.com.isurpass.iremotemessager.vo;

public class ProcessClass
{
	private String targetdecisionclass;
	private String methoddecisionclass;
	//private String[] parseclass = new String[5];
	private String[] sendclass = new String[5];
	private String[][] messagetemplate = new String[5][];

	public String getTargetdecisionclass()
	{
		return targetdecisionclass;
	}

	public void setTargetdecisionclass(String targetdecisionclass)
	{
		this.targetdecisionclass = targetdecisionclass;
	}

	public String getMethoddecisionclass()
	{
		return methoddecisionclass;
	}

	public void setMethoddecisionclass(String methoddecisionclass)
	{
		this.methoddecisionclass = methoddecisionclass;
	}

//	public String[] getParseclass()
//	{
//		return parseclass;
//	}
//
//	public void setParseclass(String[] parseclass)
//	{
//		this.parseclass = parseclass;
//	}

	public String[] getSendclass()
	{
		return sendclass;
	}

	public void setSendclass(String[] sendclass)
	{
		this.sendclass = sendclass;
	}

	public String[][] getMessagetemplate()
	{
		return messagetemplate;
	}

	public void setMessagetemplate(String[][] messagetemplate)
	{
		this.messagetemplate = messagetemplate;
	}

}
