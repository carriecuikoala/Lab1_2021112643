package org.example;
import java.util.*;
//这是一个git测试
public class Graph
{
    class Edge
    {
        int source,destination,weight;

        Edge(int source, int destination, int weight)
        {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public void addWeight()
        {
            this.weight ++;
        }
        public boolean equals(Object obj)
        {
            if (this == obj)
            {
                return true;
            }
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            Edge edge = (Edge) obj;
            return source == edge.source && destination == edge.destination;
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(source, destination);
        }
    }




    private String[] Nodes;
    private Map <String,Integer> Vector = new HashMap<>();
    private LinkedList<Edge> adj[];
    private int V;

    //v是节点数量，nodes是所有的projected单词,adj是存的边
    Graph(int v, String[] nodes)
    {
        this.V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++)
        {
            adj[i] = new LinkedList<>();
        }
        Nodes = nodes;

        //把字符串映射为数字
        for(int i = 0; i < nodes.length; i++)
        {
            Vector.put(nodes[i], i);
        }
    }

    private boolean hasEdge(int a,int b)
    {
        return adj[a].contains(new Edge(a,b,1));
    }

    public void addEdge(String A, String B)
    {
        int a = Vector.get(A);
        int b = Vector.get(B);
        if(hasEdge(a,b))
        {
            for(int i=0;i<adj[a].size();i++)
            {
                Edge e = adj[a].get(i);
                if(e.destination == b)
                {
                    e.addWeight();
                    adj[a].set(i,e);
                }
            }
        }
        else adj[a].add(new Edge(a,b,1));
    }







    public int getNumber(String node)
    {
        if(Vector.containsKey(node))  return Vector.get(node);
        else return -1;
    }

    public Integer[] getNextNeighbor(int node)
    {
        Set<Integer> set = new HashSet<>();
        for (Edge e : adj[node])
        {
            set.add(e.destination);
        }
        return set.toArray(new Integer[set.size()]);
    }

    public String[] getBridge(String input1, String input2)
    {
        int number1 = getNumber(input1);
        int number2 = getNumber(input2);
        String[] bridgeWords = new String[V];
        int cnt=0;
        if(number1 == -1 || number2 == -1)
        {
            return null;
        }
        Integer[] neighbors = getNextNeighbor(number1);
        for (int i = 0; i < neighbors.length; i++)
        {
            Integer[] neighbors2 = getNextNeighbor(neighbors[i]);
            for (int j = 0; j < neighbors2.length; j++)
            {
                if(neighbors2[j] == number2)
                {
                    bridgeWords[cnt++]=Nodes[neighbors[i]];
                    break;
                }
            }
        }
        return bridgeWords;
    }

    public String calcShortestPath(String word1, String word2)
    {
        //用于在优先队列中进行比较哪个点的距离最近
        class pair
        {
            int w,t;
            pair(int t, int w)
            {
                this.w = w;
                this.t = t;
            }
        }
        int number1 = getNumber(word1);
        int number2 = getNumber(word2);
        int source = number1;
        int destination = number2;
        PriorityQueue<pair> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.w));
        int[] distances = new int[V];
        int[] parents = new int[V];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(parents, -1);
        distances[source] = 0;
        pq.offer(new pair(source,0));//把source添加到优先队列里

        while (!pq.isEmpty())
        {
            pair current = pq.poll();//弹出最前（最小）元素
            int currentVertex = current.t;
            int currentWeight = current.w;

            if (currentWeight > distances[currentVertex])
            {
                continue;
            }

            for (Edge neighbor : adj[currentVertex])
            {
                int newDistance = distances[currentVertex] + neighbor.weight;

                if (newDistance < distances[neighbor.destination])
                {
                    distances[neighbor.destination] = newDistance;
                    parents[neighbor.destination] = currentVertex;
                    pq.offer(new pair(neighbor.destination, newDistance));
                }
            }
        }
        int dest = destination;
        if(distances[destination] == Integer.MAX_VALUE)
        {
            return null;
        }
        LinkedList<String> Path = new LinkedList<>();
        while(dest != source)
        {
            Path.add(Nodes[dest]);
            dest = parents[dest];
        }
        Path.add(Nodes[source]);
        StringBuilder lastPath = new StringBuilder();
        ListIterator<String> iterator = Path.listIterator(Path.size());
        while(iterator.hasPrevious())
        {
            String element = iterator.previous();
            lastPath.append(element);
            if(iterator.hasPrevious()) lastPath.append("->");
        }
        return lastPath.toString();
    }

    public int calcShortestDic(String word1, String word2)
    {
        //用于在优先队列中进行比较哪个点的距离最近
        class pair
        {
            int w,t;
            pair(int t, int w)
            {
                this.w = w;
                this.t = t;
            }
        }
        int number1 = getNumber(word1);
        int number2 = getNumber(word2);
        int source = number1;
        int destination = number2;
        PriorityQueue<pair> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.w));
        int[] distances = new int[V];
        int[] parents = new int[V];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(parents, -1);
        distances[source] = 0;
        pq.offer(new pair(source,0));//把source添加到优先队列里

        while (!pq.isEmpty())
        {
            pair current = pq.poll();//弹出最前（最小）元素
            int currentVertex = current.t;
            int currentWeight = current.w;

            if (currentWeight > distances[currentVertex])
            {
                continue;
            }

            for (Edge neighbor : adj[currentVertex])
            {
                int newDistance = distances[currentVertex] + neighbor.weight;

                if (newDistance < distances[neighbor.destination])
                {
                    distances[neighbor.destination] = newDistance;
                    parents[neighbor.destination] = currentVertex;
                    pq.offer(new pair(neighbor.destination, newDistance));
                }
            }
        }
        return distances[destination];
    }

    public String randomWalk()
    {
        Random rand = new Random();
        StringBuilder walkPath = new StringBuilder();
        int[] visited = new int[V];
        int randVertex = rand.nextInt(V);
        Arrays.fill(visited, 0);
        visited[randVertex] = 1;
        walkPath.append(Nodes[randVertex]+" ");
        while(true){
            int randEdge = rand.nextInt(adj[randVertex].toArray().length);
            Edge edge = adj[randVertex].get(randEdge);
            int to = edge.destination;
            if(visited[to] == 1){
                break;
            }
            walkPath.append(Nodes[to]+" ");
            visited[to] = 1;
            randVertex = to;
        }
        return walkPath.toString();
    }
    public String[] printEdge(){
        String[] saveEdge = new String[V*V];
        int cnt = 0;
        for(int i = 0; i < V; i++){
            for(Edge edge : adj[i]){
                String tmp = Nodes[edge.source]+" "+Nodes[edge.destination]+" "+edge.weight;
                saveEdge[cnt++] = tmp;
            }
        }
        return saveEdge;
    }
}

