from http.server import BaseHTTPRequestHandler, HTTPServer
from urllib.parse import urlparse, parse_qs, quote
import urllib.request
import time

import bar
import pie
import donut
import fdg
import worldmap
#import bubble


hostName = "localhost"
hostPort = 9000

d3func = {'bar': bar.getResponse,
          'pie': pie.getResponse,
          'donut': donut.getResponse,
          'fdg': fdg.getResponse,
          'worldmap': worldmap.getResponse}
          #'bubble': bubble.getResponse,


d3file = {'bar': 'bar_input.tsv',
          'pie': 'pie_input.csv',
          'donut': 'donut_input.csv',
          'fdg': 'fdg_input.json',
          'worldmap': 'worldmap_input.csv'}
          #'bubble': bubble.getResponse}

class MyServer(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header("Content-type", "text/html")
        self.end_headers()

        pq = parse_qs(urlparse(self.path).query)
        #print('query:' + pq['query'][0])
        #print('d3:' + pq['d3'][0])
        js_string = urllib.request.urlopen("http://localhost:8983/solr/collection1/select?q=" + quote(pq['query'][0]) + "&wt=json&indent=true&rows=50&fl=score%2cid%2ckeywords%2clinks%2ccontent_type%2ctitle%2clatlongs").read().decode('utf-8')

        #self.wfile.write(bytes(d3func[pq['d3'][0]](js_string), 'utf-8'))

        with open(d3file[pq['d3'][0]], 'w') as outfile:
            outfile.write(d3func[pq['d3'][0]](js_string))

        self.wfile.write(bytes("<html><head><title>REST API</title></head>", "utf-8"))
        self.wfile.write(bytes("<body><p>Successfully wrote d3file</p></body></html>", "utf-8"))

myServer = HTTPServer((hostName, hostPort), MyServer)
print(time.asctime(), "Server Starts - %s:%s" % (hostName, hostPort))

try:
    myServer.serve_forever()
except KeyboardInterrupt:
    pass

myServer.server_close()
print(time.asctime(), "Server Stops - %s:%s" % (hostName, hostPort))
