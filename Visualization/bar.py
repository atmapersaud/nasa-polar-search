import json
from pprint import pprint

def getResponse(json_string):
    data = json.loads(json_string)
    idlength=len(data["response"]["docs"])

    response = "id\tscore\ttitle\n"
    for i in range(idlength):
        if 'title' not in data["response"]["docs"][i]:
            continue
        response = response + data["response"]["docs"][i]["id"] + "\t" + str(data["response"]["docs"][i]["score"]) +"\t"+ str(data["response"]["docs"][i]["title"])+"\n"

    return response
    
def main():
    with open('query1.json') as data_file:    
        data = json.load(data_file)

    idlength=len(data["response"]["docs"])
    print("idlength: "+str(idlength))

    print("id,content_type")
    for i in range(idlength):
        print(data["response"]["docs"][i]["id"]," ",data["response"]["docs"][i]["score"])

    #Please write to file in the format that is needed
    #for eg, bar chart needs tsv format

    tsvwrite=open("barchartinput.tsv","wb")
    tsvwrite.write("id\tscore\ttitle\n")
    for i in range(idlength):
        if 'title' not in data["response"]["docs"][i]:
            continue
        string=data["response"]["docs"][i]["id"]+"\t"+str(data["response"]["docs"][i]["score"])+"\t"+str(data["response"]["docs"][i]["title"])+"\n"
        tsvwrite.write(string)
    tsvwrite.close()

if __name__ == '__main__':
    main()
