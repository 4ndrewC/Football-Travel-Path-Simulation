import matplotlib.pyplot as plt
from mpl_toolkits import mplot3d

fin = open('coordinates.out', 'r')


ax = plt.axes(projection='3d')
x = []
y = []
z = []
for i in range(309):
    row = list(map(float, fin.readline().split()))
    x.append(row[0])
    y.append(row[1])
    z.append(row[2])

graph = plt.plot(x,y,z)

# ax.axes.set_xlim3d(left=0, right=100) 
# ax.axes.set_ylim3d(bottom=-1000, top=0) 
# ax.axes.set_zlim3d(bottom=0, top=-1000) 
ax.plot(x, y, z, 'blue')
plt.show()