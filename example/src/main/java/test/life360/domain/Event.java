package test.life360.domain;

import test.life360.loggable.BinaryLoggable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Event implements BinaryLoggable, Serializable {

    private int eventId;
    private long eventTime;
    private String eventUUID;

    public Event() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventUUID() {
        return eventUUID;
    }

    public void setEventUUID(String eventUUID) {
        this.eventUUID = eventUUID;
    }

    @Override
    public byte[] toBytes() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(this);
        return out.toByteArray();
    }

    @Override
    public void fromBytes(byte[] rawBytes) throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(rawBytes))) {
            Event event = (Event) in.readObject();
            this.eventId = event.eventId;
            this.eventTime = event.eventTime;
            this.eventUUID = event.eventUUID;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return String.format("name: %s, time: %s, uuid: %s", eventId, eventTime, eventUUID);
    }
}
