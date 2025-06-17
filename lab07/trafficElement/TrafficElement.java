package trafficElement;
import java.util.UUID;
import enums.Status;

public class TrafficElement {
  protected UUID id;
  private Status status;

  public TrafficElement() {
    this.id = UUID.randomUUID();
  }

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
