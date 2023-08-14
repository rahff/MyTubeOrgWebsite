package fr.myTube.website.core.shared;

public interface EventEmitter {

  <T> void emit(Event<T> event);
}
