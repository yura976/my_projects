public record InformationStorageDevice(InformationStorageDeviceType deviceType, int volumeMemory, double weight) {
    public InformationStorageDevice setDeviceType(InformationStorageDeviceType deviceType) {
        return new InformationStorageDevice(deviceType, volumeMemory, weight);
    }
    public InformationStorageDevice setVolumeMemory(int volumeMemory) {
        return new InformationStorageDevice(deviceType, volumeMemory, weight);
    }
    public InformationStorageDevice setWeight(double weight) {
        return new InformationStorageDevice(deviceType, volumeMemory, weight);
    }
    @Override
    public String toString() {
        return "\n\tНакопитель информации:\n"
                + "\t\t- тип: " + deviceType + "\n"
                + "\t\t- объем памяти: " + volumeMemory + "\n"
                + "\t\t- вес: " + weight + ".";
    }
}
