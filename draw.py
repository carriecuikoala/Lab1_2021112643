import networkx as nx
import matplotlib.pyplot as plt
#修改
G = nx.DiGraph()
file_path = "graph.txt" 

def jieXi(a):
    b = a.split(" ")
    return b[0],b[1],b[2]

try:
    with open(file_path, 'r') as file:
        lines = file.readlines()
except FileNotFoundError:
    print(f" '{file_path}' not found")
except Exception as e:
    print(f"read error:{e}")

for line in lines:
    a,b,c = jieXi(line)
    G.add_edge(a, b, weight = c)


pos = nx.spring_layout(G)  
nx.draw(G, pos, with_labels=True, node_color='skyblue', node_size=1500, edge_color='black', linewidths=1, font_size=15)  
labels = nx.get_edge_attributes(G, 'weight')  
nx.draw_networkx_edge_labels(G, pos, edge_labels=labels)

plt.show()