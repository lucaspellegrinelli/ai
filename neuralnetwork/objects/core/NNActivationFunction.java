package lucaspellegrinelli.ai.neuralnetwork.objects.core;

public interface NNActivationFunction {   
    public static final NNActivationFunction ARCTAN = new NNActivationFunction() {
        @Override
        public double activationFunction(double x) {
            return Math.atan(x);
        }

        @Override
        public double derivativeActivationFunction(double x) {
            return 1 / (Math.pow(x, 2.0) + 1.0);
        }
    };
    
    public static final NNActivationFunction BENT_IDENTITY = new NNActivationFunction() {
        @Override
        public double activationFunction(double x) {
            return (Math.sqrt(Math.pow(x, 2.0) + 1.0) - 1.0) / 2.0;
        }

        @Override
        public double derivativeActivationFunction(double x) {
            return x / (2.0 * Math.sqrt(Math.pow(x, 2.0) + 1.0)) + 1.0;
        }
    };
    
    public static final NNActivationFunction GAUSSIAN = new NNActivationFunction() {        
        @Override
        public double activationFunction(double x) {
            return Math.pow(Math.E, -Math.pow(x, 2.0));
        }

        @Override
        public double derivativeActivationFunction(double x) {
            return -2.0 * x * Math.pow(Math.E, -Math.pow(x, 2.0));
        }
    };
    
    public static final NNActivationFunction IDENTITY = new NNActivationFunction() {
        @Override
        public double activationFunction(double x) {
            return x;
        }

        @Override
        public double derivativeActivationFunction(double x) {
            return 1.0;
        }
    };
    
    public static final NNActivationFunction RELU = new NNActivationFunction() {
        @Override
        public double activationFunction(double x) {
            if(x >= 0.0){
                return x;
            }else{
                return 0.0;
            }
        }

        @Override
        public double derivativeActivationFunction(double x) {
            if(x >= 0.0){
                return 1.0;
            }else{
                return 0.0;
            }
        }
    };
    
    public static final NNActivationFunction SINC = new NNActivationFunction() {
        @Override
        public double activationFunction(double x) {
            if(x == 0.0){
                return 1.0;
            }else{
                return Math.sin(x) / x;
            }
        }

        @Override
        public double derivativeActivationFunction(double x) {
            if(x == 0.0){
                return 0.0;
            }else{
                return (Math.cos(x) / x) - (Math.sin(x) / Math.pow(x, 2));
            }
        }
    };
    
    public static final NNActivationFunction SINUSOID = new NNActivationFunction() {
        @Override
        public double activationFunction(double x) {
            return Math.sin(x);
        }

        @Override
        public double derivativeActivationFunction(double x) {
            return Math.cos(x);
        }
    };
    
    public static final NNActivationFunction SOFTPLUS = new NNActivationFunction() {
        @Override
        public double activationFunction(double x) {
            return Math.log(1.0 + Math.pow(Math.E, x));
        }

        @Override
        public double derivativeActivationFunction(double x) {
            return 1.0 / (1.0 + Math.pow(Math.E, -x));
        }
    };
    
    public static final NNActivationFunction SOFTSIGN = new NNActivationFunction() {
        @Override
        public double activationFunction(double x) {
            return x / (1.0 + Math.abs(x));
        }

        @Override
        public double derivativeActivationFunction(double x) {
            return 1.0 / Math.pow((1.0 + Math.abs(x)), 2.0);
        }
    };
    
    public static final NNActivationFunction SOFT_STEP = new NNActivationFunction() {
        @Override
        public double activationFunction(double x) {
            return 1.0 / (1.0 + Math.pow(Math.E, -x));
        }

        @Override
        public double derivativeActivationFunction(double x) {
            return activationFunction(x) * (1.0 - activationFunction(x));
        }
    };
    
    public static final NNActivationFunction TANH = new NNActivationFunction() {
        @Override
        public double activationFunction(double x) {
            return (2.0 / (1.0 + Math.pow(Math.E, -2 * x))) - 1;
        }

        @Override
        public double derivativeActivationFunction(double x) {
            return 1.0 - Math.pow(activationFunction(x), 2);
        }
    };

    double activationFunction(double x);
    double derivativeActivationFunction(double x);
}
