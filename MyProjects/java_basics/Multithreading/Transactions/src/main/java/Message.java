public class Message {
    private String data;
    private MessageType messageType;
    public Message(String data, MessageType messageType) {
        this.data = data;
        this.messageType = messageType;
    }
    public String getData() {
        return data;
    }
    public MessageType getMessageType() {
        return messageType;
    }

    @Override
    public String toString() {
        return data;
    }
}
