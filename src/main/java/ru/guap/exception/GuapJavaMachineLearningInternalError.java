package ru.guap.exception;

public class GuapJavaMachineLearningInternalError extends RuntimeException {

    public GuapJavaMachineLearningInternalError() {
    }

    public GuapJavaMachineLearningInternalError(String message) {
        super(message);
    }

    public GuapJavaMachineLearningInternalError(String message, Throwable cause) {
        super(message, cause);
    }

    public GuapJavaMachineLearningInternalError(Throwable cause) {
        super(cause);
    }

    public GuapJavaMachineLearningInternalError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
