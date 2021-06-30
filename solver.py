#!/usr/bin/python
# -*- coding: utf-8 -*-

from collections import namedtuple
import math
import subprocess  
import sys
Point = namedtuple("Point", ['x', 'y'])
Facility = namedtuple("Facility", ['index', 'setup_cost', 'capacity', 'location'])
Customer = namedtuple("Customer", ['index', 'demand', 'location'])
COMMAND = "C:; cd 'C:/Users/aleksejs/coursera/examples/sources'; & 'c:\\Users\\aleksejs\\.vscode\\extensions\\vscjava.vscode-java-debug-0.31.0\\scripts\\launcher.bat' 'C:\\Program Files\\AdoptOpenJDK\\jdk-11.0.10.9-hotspot\\bin\\java.exe' '-Dfile.encoding=UTF-8' '@C:\\Users\\aleksejs\\AppData\\Local\\Temp\\cp_5a9lkk2z92tfn1stlyhv0ojcq.argfile' 'org.optaplanner.examples.vehiclerouting.app.MyCTry'"

def run(cmd):
    completed = subprocess.run(["powershell", "-Command", cmd], capture_output=True)
    return completed


def length(point1, point2):
    return math.sqrt((point1.x - point2.x)**2 + (point1.y - point2.y)**2)

def solve_it(input_data):
    res = run(COMMAND + " \'" + input_data[7:] + "\'")
    return res.stdout.decode('utf-8')

if __name__ == '__main__':
 
 
    if len(sys.argv) > 1:
        file_location = sys.argv[1].strip()
        print(solve_it(file_location))
    else:
        print('This test requires an input file.  Please select one from the data directory. (i.e. python solver.py ./data/fl_16_2)')

