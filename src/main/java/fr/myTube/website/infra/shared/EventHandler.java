package fr.myTube.website.infra.shared;

import fr.myTube.website.core.shared.Event;

public interface EventHandler<T extends Event> {
  public void handle(T event);
}
