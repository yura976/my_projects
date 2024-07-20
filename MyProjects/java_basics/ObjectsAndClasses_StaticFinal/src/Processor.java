public record Processor(double frequency, int countCores, String producer, double weight) {
    public Processor setFrequency(double frequency) {
        return new Processor(frequency, countCores, producer, weight);
    }
    public Processor setCountCores(int countCores) {
        return new Processor(frequency, countCores, producer, weight);
    }
    public Processor setProducer(String producer) {
        return new Processor(frequency, countCores, producer, weight);
    }
    public Processor setWeight(double weight) {
        return new Processor(frequency, countCores, producer, weight);
    }
    @Override
    public String toString() {
        return "\n\tПроцессор:\n"
                + "\t\t- частота: " + frequency + "\n"
                + "\t\t- количество ядер: " + countCores + "\n"
                + "\t\t- производитель: " + producer + '\n'
                + "\t\t- вес: " + weight + ".";
    }
}
