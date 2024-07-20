public record Memory(MemoryType memoryType, int volume, double weight) {
    public Memory setMemoryType(MemoryType memoryType) {
        return new Memory(memoryType, volume, weight);
    }
    public Memory setVolume(int volume) {
        return new Memory(memoryType, volume, weight);
    }
    public Memory setWeight(double weight) {
        return new Memory(memoryType, volume, weight);
    }
    @Override
    public String toString() {
        return "\n\tОперативная память:\n"
                + "\t\t- тип: " + memoryType + "\n"
                + "\t\t- объем: " + volume + "\n"
                + "\t\t- вес: " + weight + ".";
    }
}
