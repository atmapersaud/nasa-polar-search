import json
from pprint import pprint

def getResponse(js_string):
    data = json.loads(js_string)

    idlength=len(data["response"]["docs"])
    #print "idlength: "+str(idlength)

    dict=[0,0,0,0,0]
    for i in range(idlength):
        if 'keywords' not in data["response"]["docs"][i]:
            #print(data['response']['docs'][i])
            continue
        count=len(data["response"]["docs"][i]["keywords"].split(","))
        if count>0 and count <10:
            dict[0]=dict[0]+1
        elif count>=10 and count<20:
            dict[1]=dict[1]+1 
        elif count>=20 and count<30:
            dict[2]=dict[2]+1
        elif count>=30 and count<40:
            dict[3]=dict[3]+1
        else:
            dict[4]=dict[4]+1         
            
    result = "no_of_keywords,number_of_documents\n"
    for index,item in enumerate(dict):
        if index==0:
            string="0-9,"+str(item)+"\n"
            result += string
        if index==1:
            string="10-19,"+str(item)+"\n"
            result += string
        if index==2:
            string="20-29,"+str(item)+"\n"
            result += string
        if index==3:
            string="30-39,"+str(item)+"\n"
            result += string
        if index==4:
            string=">=40,"+str(item)+"\n"
            result += string
    return result

#Please write to file in the format that is needed
#for eg, bar chart needs tsv format

# tsvwrite=open("barchartinput.tsv","wb")
# tsvwrite.write("id\tscore\n")
# for i in range(idlength):
#     string=data["response"]["docs"][i]["id"]+"\t"+str(data["response"]["docs"][i]["score"])+"\n"
#     tsvwrite.write(string)
# tsvwrite.close()
