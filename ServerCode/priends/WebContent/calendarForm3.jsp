<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="priends.work.*"%>
<%@ page import="java.util.*"%>
<%
// JSP Calendar:--
// Created by Jason Benassi
// jbenassi@lime-light.com
// http://www.wakeboardutah.com
// 7-2002
%>
<%
// Global Vars
int action = 0;  // incoming request for moving calendar up(1) down(0) for month
int currYear = 0; // if it is not retrieved from incoming URL (month=) then it is set to current year
int currMonth = 0; // same as year
String boxSize = "70";  // how big to make the box for the calendar

//build 2 calendars

Calendar c = Calendar.getInstance();
Calendar cal = Calendar.getInstance();

	if (request.getParameter("action") == null) // Check to see if we should set the year and month to the current
	{
		currMonth = c.get(c.MONTH);
		currYear = c.get(c.YEAR);
		cal.set(currYear, currMonth,1);
	}

	else
	{
		if (!(request.getParameter("action") == null)) // Hove the calendar up or down in this if block
		{
			currMonth = Integer.parseInt(request.getParameter("month"));
			currYear = Integer.parseInt(request.getParameter("year"));

				if (Integer.parseInt( request.getParameter("action")) == 1 )
				{
					cal.set(currYear, currMonth, 1);
					cal.add(cal.MONTH, 1);
					currMonth = cal.get(cal.MONTH);
					currYear = cal.get(cal.YEAR);
				}
				else
				{
					cal.set(currYear, currMonth ,1);
					cal.add(cal.MONTH, -1);
					currMonth = cal.get(cal.MONTH);
					currYear = cal.get(cal.YEAR);
				}
		}
	}
%>

<%!
    public boolean isDate(int m, int d, int y) // This method is used to check for a VALID date
    {
        m -= 1;
        Calendar c = Calendar.getInstance();
        c.setLenient(false);

        try
        {
                c.set(y,m,d);
                Date dt = c.getTime();
        }
          catch (IllegalArgumentException e)
        {
                return false;

        }
                return true;
    }
%>
<%!
   public String getDateName (int monthNumber) // This method is used to quickly return the proper name of a month
   {
		String strReturn = "";
		switch (monthNumber)
		{
	case 0:
		strReturn = "1월";
		break;
	case 1:
		strReturn = "2월";
		break;
	case 2:
		strReturn = "3월";
		break;
	case 3:
		strReturn = "4월";
		break;
	case 4:
		strReturn = "5월";
		break;
	case 5:
		strReturn = "6월";
		break;
	case 6:
		strReturn = "7월";
		break;
	case 7:
		strReturn = "8월";
		break;
	case 8:
		strReturn = "9월";
		break;
	case 9:
		strReturn = "10월";
		break;
	case 10:
		strReturn = "11월";
		break;
	case 11:
		strReturn = "12월";
		break;
	}
	return strReturn;
    }
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
</head>
</body class="type11"  align="center" bgcolor='white'>

<form name="listForm" action="/priends/WebContent/calendarProc.jsp" method="get"><p>
<div class="form-group">
            <label class="col-sm-3 control-label" for="inputName">불가능한 날짜로 변경 -> ' , ' 로 구분 (ex, 1,15,16) </label>
          <div class="col-sm-6">
            <input name="select_day" class="form-control" id="inputName" type="text" placeholder=" , , , ">
            <input type= "hidden" name="dotype" value="doNo">
  			<input type= "submit" value="Save">
			</p></form>
<form name="listForm" action="/priends/WebContent/calendarProc.jsp" method="get"><p>
<div class="form-group">
            <label class="col-sm-3 control-label" for="inputName">가능한 날짜로 변경 -> ' , ' 로 구분 (ex, 1,15,16) </label>
          <div class="col-sm-6">
            <input name="select_day" class="form-control" id="inputName" type="text" placeholder=" , , , ">
            <input type= "hidden" name="dotype" value="doYes">
  			<input type= "submit" value="Save">
			</p></form>
			
<table class="type11" align="center">
<table class="type11" align="center" border='1' width='519' celpadding='0' cellspacing='0'>
  <tr>
	<td width='150' align='right' valign='middle'><a href="cal.jsp?month=<%=currMonth%>&year=<%=currYear%>&action=0"><font size="1">before</font></a></td>
	<td width='260' align='center' valign='middle'><b><%=getDateName (cal.get(cal.MONTH)) + " " + cal.get(cal.YEAR)%></b></td>
	<td width='173' align='left' valign='middle'><a href="cal.jsp?month=<%=currMonth%>&year=<%=currYear%>&action=1"><font size="1">next</font></a></td>
  </tr>
	</table>
<table align="center" border="0" width="520" bordercolorlight="#C0C0C0" bordercolordark="#808080" style="border-collapse: collapse" bordercolor="#111111" cellpadding="0" cellspacing="0">
  <td width="100%">
    <table border="2" width="519" bordercolorlight="#C0C0C0" bordercolordark="#000000" style="border-collapse: collapse" bordercolor="#000000" cellpadding="0" cellspacing="0" bgcolor="#DFDCD8">
		<thead>
		<tr>
    		<th scope="cols" width="<%=boxSize%>" align="center" nowrap bordercolor="#666666" bgcolor="#666666">
				일</b></th>
    		<th scope="cols" width="<%=boxSize%>" align="center" nowrap bordercolor="#666666" bgcolor="#666666">
    		월</th>
    		<th scope="cols" width="<%=boxSize%>" align="center" nowrap bordercolor="#666666" bgcolor="#666666">
    		화</th>
    		<th scope="cols" width="<%=boxSize%>" align="center" nowrap bordercolor="#666666" bgcolor="#666666">
   			수</th>
    		<th scope="cols" width="<%=boxSize%>" align="center" nowrap bordercolor="#666666" bgcolor="#666666">
    		목</th>
    		<th scope="cols" width="<%=boxSize%>" align="center" nowrap bordercolor="#666666" bgcolor="#666666">
    		금</th>
    		<th scope="cols" width="<%=boxSize%>" align="center" nowrap bordercolor="#666666" bgcolor="#666666">
    		토</th>
  	</tr>
		</thead>
<%

//'Calendar loop


	int currDay;
	String todayColor;
	int count = 1;
	int dispDay = 1;

	Object id = session.getAttribute("memId");
	String UserID = (String)id;

	CalenderDAO calen = new CalenderDAO();

	String [] calender = calen.make_Calender(UserID);

	for (int w = 1; w < 6; w++)
	{
%>
  	<tr>
<%
  		for (int d = 1; d < 8; d++)
		{
			if (! (count >= cal.get(c.DAY_OF_WEEK)))
			{

%>
		<td width="<%=boxSize%>" height="<%=boxSize%>" valign="top" align="left">&nbsp;</td>
<%
				count += 1;
			}
			else
			{

				if (isDate ( currMonth + 1, dispDay, currYear) ) // use the isDate method
				{
					if(calender[dispDay].equals("N")){
						todayColor = "#444444";
					}

					else if ( dispDay == c.get(c.DAY_OF_MONTH) && c.get(c.MONTH) == cal.get(cal.MONTH) && c.get(c.YEAR) == cal.get(cal.YEAR)) // Here we check to see if the current day is today
        				{
							todayColor = "#6C7EAA";
						}
						else
						{
							todayColor = "#ffffff";
						}
					if ( dispDay == c.get(c.DAY_OF_MONTH) && c.get(c.MONTH) == cal.get(cal.MONTH) && c.get(c.YEAR) == cal.get(cal.YEAR)) // Here we check to see if the current day is today
    				{
						todayColor = "#6C7EAA";
					}


%>
		<td bgcolor ="<%=todayColor%>" width="<%=boxSize%>" align="left" height="<%=boxSize%>" valign="top">
		<a>
		<%=dispDay%>
		</a>
		<br>
		</td>
<%
					count += 1;
					dispDay += 1;
				}
				else
				{
%>

<%
				}
			}

       }
%>
  	</tr>
<%
}
%>

</table>
</td>
<tr><td>
</table>
</table>


</body>
</html>
