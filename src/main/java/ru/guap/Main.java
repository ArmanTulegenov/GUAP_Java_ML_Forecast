package ru.guap;

import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.TensorFlow;
import ru.guap.model.Building;
import ru.guap.model.PanelMadeBuilding;

public class Main {

    public static void main(String[] args) {
        try (Graph g = new Graph()) {
            final String value = "Hello from " + TensorFlow.version();

            System.out.println(value);
        }

    }
}
