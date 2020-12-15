package ru.guap.nn;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.BackpropType;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.LSTM;
import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.AdaGrad;
import org.nd4j.linalg.learning.config.IUpdater;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class Classifier {

    private static final int seed = 12345;
    private static final int INPUT_PERIOD = 10;
    private static final int OUTCOME_PERIOD = 1;
    private static final int PERIODS = INPUT_PERIOD + OUTCOME_PERIOD;
    private static final int lstmLayer1Size = PERIODS * 2;
    private static final int lstmLayer2Size = PERIODS;
    private static final int truncatedBPTTLength = 250;
    private static final double dropoutRatio = 0.8;
    private static final int nIn = 10;
    private static final int nOut = 1;


    public static MultiLayerConfiguration getConfiguration() {
        IUpdater updater = new AdaGrad();
        return new NeuralNetConfiguration.Builder().seed(seed)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .weightInit(WeightInit.XAVIER).updater(updater)
                .updater(updater)  // RMSPROP or ADAGRAD
                .l2(1e-2)
                .list()
                .layer(0, new LSTM.Builder()
                        .nIn(nIn)
                        .nOut(lstmLayer1Size)
                        .gateActivationFunction(Activation.SOFTSIGN)
                        .dropOut(dropoutRatio)
                        .build())
                .layer(1, new LSTM.Builder()
                        .nIn(lstmLayer1Size)
                        .nOut(lstmLayer2Size)
                        .gateActivationFunction(Activation.SOFTSIGN)
                        .dropOut(dropoutRatio)
                        .build())
                .layer(2, new RnnOutputLayer.Builder()
                        .nIn(lstmLayer2Size)
                        .nOut(nOut)
                        .activation(Activation.IDENTITY)
                        .lossFunction(LossFunctions.LossFunction.MSE)
                        .build())
                .backpropType(BackpropType.TruncatedBPTT)
                .tBPTTForwardLength(truncatedBPTTLength)
                .tBPTTBackwardLength(truncatedBPTTLength)
                .build();
    }

    public static MultiLayerNetwork getNetwork() {

        MultiLayerNetwork network = new MultiLayerNetwork(Classifier.getConfiguration());
        network.init();
        return network;
    }

    public static void fitNetwork(MultiLayerNetwork network) {

    }
}
