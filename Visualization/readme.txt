The technolgies that we used:
Front end:
Bootstrap

Backend:
Python server

Banana:
angular.js

D3:
JavaScript 

FacetView and Banana are both query Solr server natively.
We solved the issue of CORS by enabling a plugin on browser which allows for CORS request. 

***Here is the list of files we have attached and thier functionalities.
1)Banana_index.html
2)d3_Main.html
3)facetview_index.html
These are the html files for the 3 different technologies which we have to display for the assignment.

bar.py, donut.py, pie.py, worldmap.py, fdg.py are the python files which takes input from SOLR and creates required input files for D3.
The input files created are: bar_input.tsv, pie_input.csv, donut_input.csv, worldmap-50m.json, bubble.json, fdg_input.json.

jquery.facetview, New Non-Time Series Dashboard-1430648390116, banana_config these are the config files for FacetView and Banana. 

 

**** To run the python server *****
python3 pyserver.py
we need the Solr server also running.


----------------------------------- LINK TO VIDEO -------------------------------------------
https://youtu.be/i9pZ0sLwzww
---------------------------------------------------------------------------------------------