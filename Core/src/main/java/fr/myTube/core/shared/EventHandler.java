package fr.myTube.core.shared;

public interface EventHandler<T extends Event> {

  void handle(Event event);
}
