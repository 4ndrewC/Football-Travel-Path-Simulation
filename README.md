# Football-Travel-Path-Simulation

This model simulates the path of a football free kick

Files:
simulation.java: main simulation of the ball travel path

coordinates.out: the results of the simulation
- First column is the time passed since the start for each time increment
- Second to fourth column are the x, y, and z positions of the ball for each time increment respectively

grahing.py: creating a 3D visual depiction of the travel path of the ball, using results from coordinates.out to graph

2dgraph.py: separating each plane of the 3D graph into independent 2D graphs of x vs. y / y vs. z / x vs. z
