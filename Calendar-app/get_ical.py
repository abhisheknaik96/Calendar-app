import requests
import getpass
import sys

userName_cmd = sys.argv[1]								# takes command line arguments : Username Password outFileName
password_cmd = sys.argv[2]
outFileName = sys.argv[3]

with requests.Session() as s:
	url = "http://www.cse.iitm.ac.in/moodle/login/index.php"		
	#user_name = raw_input("Username: ")
	#PassWord = getpass.getpass()
	user_name = userName_cmd
	PassWord = password_cmd
	s.get(url)
	
	login_data = dict(username = user_name, password = PassWord)
	s.post(url,data = login_data,headers = {"Referer" : "http://www.cse.iitm.ac.in/moodle/"})
	get_authtoken = s.get("http://www.cse.iitm.ac.in/moodle/calendar/export.php?course=0")
	a = get_authtoken.content
	
	for i in range(len(a)):
		if a[i:i+10]=="authtoken=":
			j=i+10
			while a[j]!='"':
				j+=1
			authtoken =  a[i+10:j]
			
	page = s.get("http://www.cse.iitm.ac.in/moodle/calendar/export_execute.php?reset_what=all&preset_time=recentupcoming&username="+user_name + "&authtoken=" + authtoken)
	
	fo = open(outFileName, "w")
	fo.write(page.content)
	fo.close()
	
	print 1
