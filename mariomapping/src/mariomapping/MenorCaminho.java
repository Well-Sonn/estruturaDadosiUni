package mariomapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node {
    private String fase;
    private List<Connection> connections;
    private int distance;

    public Node(String fase) {
        this.fase = fase;
        this.connections = new ArrayList<>();
        this.distance = Integer.MAX_VALUE;
    }

    public String getFase() {
        return fase;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void addConnection(Node destination, int distance) {
        connections.add(new Connection(destination, distance));
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}

class Connection {
    private Node destination;
    private int distance;

    public Connection(Node destination, int distance) {
        this.destination = destination;
        this.distance = distance;
    }

    public Node getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }
}

public class MenorCaminho {
    private static List<Node> nodes = new ArrayList<>();

    public static void main(String[] args) {
        initializeNodes();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o numero da fase de destino:");
        int destinoFase = scanner.nextInt() - 1;
        scanner.close();

        int origemFase = 0;

        List<String> caminho = graph(origemFase, destinoFase);

        if (caminho.isEmpty()) {
            System.out.println("Nao ha caminho possivel ate a fase especificada.");
        } else {
            System.out.println("O caminho mais curto ate a fase " + (destinoFase + 1) + " e:");
            System.out.println(caminho );
        }
    }

private static void initializeNodes() {
    	
    	for (int i = 0; i <= 32; i++) {
            nodes.add(new Node("Fase " + i));
        }

	    	nodes.get(0).addConnection(nodes.get(1), 10);
	        nodes.get(1).addConnection(nodes.get(2), 15);
	        nodes.get(2).addConnection(nodes.get(3), 10);
	        nodes.get(3).addConnection(nodes.get(4), 5);
	        nodes.get(4).addConnection(nodes.get(5), 10);
	        nodes.get(5).addConnection(nodes.get(6), 5);
	        nodes.get(5).addConnection(nodes.get(9), 5);
	        nodes.get(6).addConnection(nodes.get(7), 15);
	        nodes.get(6).addConnection(nodes.get(10), 1);
	        nodes.get(7).addConnection(nodes.get(8), 2);
	        nodes.get(8).addConnection(nodes.get(9), 2);
	        nodes.get(9).addConnection(nodes.get(10), 10);
	        nodes.get(10).addConnection(nodes.get(11), 10);
	        nodes.get(11).addConnection(nodes.get(12), 15);
	        nodes.get(12).addConnection(nodes.get(27), 2);
	        nodes.get(12).addConnection(nodes.get(13), 1);
	        nodes.get(13).addConnection(nodes.get(27), 2);
	        nodes.get(13).addConnection(nodes.get(14), 1);
	        nodes.get(14).addConnection(nodes.get(15), 10);
	        nodes.get(15).addConnection(nodes.get(16), 10);
	        nodes.get(16).addConnection(nodes.get(17), 1);
	        nodes.get(17).addConnection(nodes.get(27), 15);
	        nodes.get(17).addConnection(nodes.get(18), 2);
	        nodes.get(18).addConnection(nodes.get(19), 1);
	        nodes.get(18).addConnection(nodes.get(20), 15);
	        nodes.get(19).addConnection(nodes.get(20), 5);
	        nodes.get(20).addConnection(nodes.get(21), 5);
	        nodes.get(21).addConnection(nodes.get(22), 10);
	        nodes.get(22).addConnection(nodes.get(23), 15);
	        nodes.get(23).addConnection(nodes.get(24), 1);
	        nodes.get(24).addConnection(nodes.get(25), 2);
	        nodes.get(25).addConnection(nodes.get(26), 2);
	        nodes.get(25).addConnection(nodes.get(30), 5);
	        nodes.get(26).addConnection(nodes.get(27), 5);
	        nodes.get(27).addConnection(nodes.get(28), 10);
	        nodes.get(27).addConnection(nodes.get(29), 1);
	        nodes.get(28).addConnection(nodes.get(29), 1);
	        nodes.get(29).addConnection(nodes.get(30), 1);

    }
    

    private static List<String> graph(int startNodeIndex, int destinationNodeIndex) {
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> n1.getDistance() - n2.getDistance());

        Node startNode = nodes.get(startNodeIndex);
        startNode.setDistance(0);
        queue.add(startNode);

        Map<Node, Node> predecessors = new HashMap<>();

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.getFase().equals(nodes.get(destinationNodeIndex).getFase())) {
                List<String> path = new ArrayList<>();
                Node node = nodes.get(destinationNodeIndex);
                while (node != null) {
                    path.add(node.getFase());
                    node = predecessors.get(node);
                }
                Collections.reverse(path);
                return path;
            }

            for (Connection connection : currentNode.getConnections()) {
                Node adjacentNode = connection.getDestination();
                int newDistance = currentNode.getDistance() + connection.getDistance();

                if (newDistance < adjacentNode.getDistance()) {
                    adjacentNode.setDistance(newDistance);
                    queue.add(adjacentNode);
                    predecessors.put(adjacentNode, currentNode);
                }
            }
        }

        return new ArrayList<>();
    }

}