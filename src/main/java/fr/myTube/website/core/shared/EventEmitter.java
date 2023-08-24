package fr.myTube.website.core.shared;

import fr.myTube.website.infra.shared.EventHandler;

public abstract class EventEmitter<T extends Event> {

  protected EventHandler<T> eventHandler;

  public EventEmitter(EventHandler<T> eventHandler){
    this.eventHandler = eventHandler;
  }
  public void emit(T event){
    new Thread(() -> this.eventHandler.handle(event)).start();
  }

}
