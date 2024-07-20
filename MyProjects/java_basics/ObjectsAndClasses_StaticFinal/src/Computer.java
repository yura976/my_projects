public class Computer {
    private final String vendor;
    private final String name;
    private Processor processor;
    private Memory memory;
    private InformationStorageDevice storageDevice;
    private Screen screen;
    private Keyboard keyboard;

    public Computer(String vendor, String name) {
        this.vendor = vendor;
        this.name = name;
    }

    public Computer(String vendor, String name,
                    Processor processor, Memory memory,
                    InformationStorageDevice storageDevice,
                    Screen screen, Keyboard keyboard) {
        this.vendor = vendor;
        this.name = name;
        this.processor = processor;
        this.memory = memory;
        this.storageDevice = storageDevice;
        this.screen = screen;
        this.keyboard = keyboard;
    }

    public String getVendor() {
        return vendor;
    }

    public String getName() {
        return name;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public InformationStorageDevice getStorageDevice() {
        return storageDevice;
    }

    public void setStorageDevice(InformationStorageDevice storageDevice) {
        this.storageDevice = storageDevice;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public double totalWeight() {
        return processor.weight() + memory.weight()
                + storageDevice.weight() + screen.weight() + keyboard.weight();
    }

    @Override
    public String toString() {
        return "Компьютер " + name + ":" + "\n\tПроизводитель: " + vendor +
                processor + memory + storageDevice + screen + keyboard +
                "\n\tОбщая масса компьютера: " + totalWeight();
    }
}