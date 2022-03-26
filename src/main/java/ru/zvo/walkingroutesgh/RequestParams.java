package ru.zvo.walkingroutesgh;

import java.util.List;

public class RequestParams {

    private static volatile RequestParams instance;
    private double ecoFactor;
    private List<Double> weights;

    public static RequestParams getInstance() {
        if (instance == null) {
            synchronized (RequestParams.class) {
                if (instance == null) {
                    instance = new RequestParams();
                }
            }
        }
        return instance;
    }

    public double getEcoFactor() {
        return ecoFactor;
    }

    public void setEcoFactor(double ecoFactor) {
        this.ecoFactor = ecoFactor;
    }

    public List<Double> getWeights() {
        return weights;
    }

    public void setWeights(List<Double> weights) {
        this.weights = weights;
    }
}
