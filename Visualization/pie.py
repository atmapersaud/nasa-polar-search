import json
from pprint import pprint

def getResponse(js_string):
    data = json.loads(js_string)

    idlength=len(data["response"]["docs"])

    dc=[0,0,0,0,0]
    for i in range(idlength):
        if 'links' not in data["response"]["docs"][i]:
            continue
        count=len(data["response"]["docs"][i]["links"])
        #print(data["response"]["docs"][i]["id"]+" "+count)
        if count>0 and count <50:
            dc[0]=dc[0]+1
        elif count>=50 and count<100:
            dc[1]=dc[1]+1 
        elif count>=100 and count<150:
            dc[2]=dc[2]+1
        elif count>=150 and count<200:
            dc[3]=dc[3]+1
        else:
            dc[4]=dc[4]+1

    #Writing to csv format
    response = "no_of_links,number_of_documents\n"
    for index,item in enumerate(dc):
        if index==0:
            string="0-49,"+str(item)+"\n"
            response += string
        if index==1:
            string="50-99,"+str(item)+"\n"
            response += string
        if index==2:
            string="100-149,"+str(item)+"\n"
            response += string
        if index==3:
            string="150-199,"+str(item)+"\n"
            response += string
        if index==4:
            string=">=200,"+str(item)+"\n"
            response += string
    return response

def main():
    with open('../data/query5.json') as data_file:    
        data = json.load(data_file)

    idlength=len(data["response"]["docs"])

    dict=[0,0,0,0,0]
    #print "id,links"
    for i in range(idlength):
        if 'links' not in data["response"]["docs"][i]:
            continue
        count=len(data["response"]["docs"][i]["links"])
        #print data["response"]["docs"][i]["id"]," ",count
        #print
        if count>0 and count <50:
            dict[0]=dict[0]+1
        elif count>=50 and count<100:
            dict[1]=dict[1]+1 
        elif count>=100 and count<150:
            dict[2]=dict[2]+1
        elif count>=150 and count<200:
            dict[3]=dict[3]+1
        else:
            dict[4]=dict[4]+1

    #Writing to csv format
    cswrite=open("pie_input.csv","wb")
    cswrite.write("no_of_links,number_of_documents\n")
    for index,item in enumerate(dict):
        if index==0:
            string="0-49,"+str(item)+"\n"
            cswrite.write(string)
        if index==1:
            string="50-99,"+str(item)+"\n"
            cswrite.write(string)
        if index==2:
            string="100-149,"+str(item)+"\n"
            cswrite.write(string)
        if index==3:
            string="150-199,"+str(item)+"\n"
            cswrite.write(string)
        if index==4:
            string=">=200,"+str(item)+"\n"
            cswrite.write(string)

if __name__ == '__main__':
    main()
