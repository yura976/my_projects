public class Main {
    public static void main(String[] args) {
        Processor processor = new Processor(2.3, 2, "Intel", 0.023);
        Memory memory = new Memory(MemoryType.DDR4, 8, 0.03);
        InformationStorageDevice device = new InformationStorageDevice(InformationStorageDeviceType.HDD,
                1000, 0.38);
        Screen screen = new Screen(24, ScreenType.IPS, 1);
        Keyboard keyboard = new Keyboard(KeyboardType.MEMBRANE, HasBacklight.YES, 0.2);
        Computer computer = new Computer("China", "Мой КОМП",
                processor, memory, device, screen, keyboard);
        System.out.println(computer + "\n");
        Screen screen1 = screen.setScreenType(ScreenType.VA);
        computer.setScreen(screen1);
        System.out.println(computer);
    }
}
