package fr.myTube.core.infra.shared;

import fr.myTube.core.core.shared.Event;

public interface EventHandler<T extends Event> {
  public void handle(T event);
}
