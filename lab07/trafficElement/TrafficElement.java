package trafficElement;
import java.util.UUID;
import enums.Status;

public class TrafficElement {
  private static UUID id = UUID.randomUUID();
  private Status status;

  public TrafficElement() {}

  public UUID getId() {
    return id;
  }

  public Status getStatus() {
    return status;
  }

  public void updateStatus(Status newStatus) {
    status = newStatus;
  }
}
