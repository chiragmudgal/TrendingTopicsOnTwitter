from pylab import *

#<----Passing the path of output file after running the map reduce job---->
fileHandle = open('/Users/chiragmudgal/Desktop/part-r-00000', 'r')

#<----List for displaying labels on X and Y axis---->
XLabels = []
YLabels = []

#<----Appending Data we got from map reduce output to the Python list---->
for line in fileHandle:
#<----Providng the delimiter---->
    fields = line.split('\t')

    XLabels.append(str(fields[0])) # prints the first fields value
    YLabels.append(int(fields[1])) # prints the second fields value

fileHandle.close()


figure(1, figsize=(6,6))
ax = axes([0.1, 0.1, 0.8, 0.8])

ax.set_axis_bgcolor((1, 0, 0))

#<----Setting the graph parameter values---->
labels = XLabels
fracs = YLabels
explode=(0, 0.05, 0, 0)

pie(fracs, explode=explode, labels=labels,
                autopct='%1.1f%%', shadow=True, startangle=90)

#<----Title for the Graph---->
title('Tech Giant\'s Mentions on Twitter', bbox={'facecolor':'0.8', 'pad':5})
show()


